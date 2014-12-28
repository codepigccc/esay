package com.esay.comm;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;


import android.os.AsyncTask;

/**
 * 异步任务管理�?
 */
public class AsyncTaskManager implements IAsyncTaskable
{
	public static final int MAX_SIZE = 50;
	private Map<String, TaskInfo> _asyncTasks;
	private Queue<TaskInfo> _waitQueue;
	private IAsyncTaskable _callback;
	private String _activeTaskId;
	
	public AsyncTaskManager(IAsyncTaskable callback)
	{
		_asyncTasks = Collections.synchronizedMap(new Hashtable<String, TaskInfo>());
		_waitQueue = new LinkedList<TaskInfo>();
		_callback = callback;
	}
	
	public void removeCallback()
	{
		_callback = null;
	}
	
	/**
	 * 执行异步任务
	 */
	public String executeAsyncTask()
	{
		return executeAsyncTask("");
	}
	
	/**
	 * 执行异步任务
	 * 
	 * @param taskName
	 *            任务�?
	 */
	public String executeAsyncTask(String taskName)
	{
		return executeAsyncTask(taskName, null);
	}
	
	/**
	 * 执行异步任务
	 * 
	 * @param taskName
	 *            任务�?
	 * @param params
	 *            参数
	 */
	public String executeAsyncTask(String taskName, Object[] params)
	{
		TaskInfo info = new TaskInfo();
		info.Id = UUID.randomUUID().toString();
		info.Name = taskName;
		info.Params = params;
		return executeAsyncTask(info);
	}
	
	/**
	 * 执行异步任务
	 * 
	 * @param info
	 *            任务详情
	 * @return
	 */
	private String executeAsyncTask(TaskInfo info)
	{
		if (info == null || info.Id == null)
		{
			return null;
		}
		
		if (_asyncTasks.size() < MAX_SIZE)
		{
		// 添加至运行列�?
			_activeTaskId = info.Id;
			_asyncTasks.put(info.Id, info);
			
			// 执行
			info.Status = ETaskStatus.Start;
			info.Worker = new BackgroundWorker();
			info.Worker.execute(info.Name, info.Params);
			return info.Id;
		}
		else
		{
		// 添加到等待队�?
			synchronized (_waitQueue)
			{
				if (!_waitQueue.contains(info))
				{
					_waitQueue.offer(info);
				}
			}
			return null;
		}
	}
	
	/**
	 * 执行等待的异步任�?
	 * 
	 * @param info
	 *            任务详情
	 * @return
	 */
	private String executeWaitAsyncTask()
	{
		synchronized (_waitQueue)
		{
			if (_waitQueue.size() > 0)
			{
				TaskInfo info = _waitQueue.poll();
				if (info != null)
				{
					return executeAsyncTask(info);
				}
			}
		}
		return null;
	}
	
	/**
	 * 取消�?���?��执行的异步任�?
	 */
	public void cancelAsyncTask()
	{
		if (_activeTaskId != null)
		{
			cancelAsyncTask(_activeTaskId);
		}
	}
	
	/**
	 * 取消异步任务
	 * 
	 * @param taskId
	 */
	public void cancelAsyncTask(String taskId)
	{
		if (_asyncTasks.containsKey(taskId))
		{
			TaskInfo info = _asyncTasks.get(taskId);
			if (info != null)
			{
				info.Status = ETaskStatus.Cancelled;
				if (info.Worker != null)
				{
					info.Worker.cancel(true);
				}
			}
		}
	}
	
	/**
	 * 异步任务�?��
	 * 
	 * @param taskName
	 */
	public void onAsyncTaskStart(String taskName, Object[] params)
	{
		if (_callback != null)
		{
			_callback.onAsyncTaskStart(taskName, params);
		}
	}
	
	/**
	 * 异步任务执行
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public Object onAsyncTaskInBackground(String taskName, Object[] params) throws Exception
	{
		if (_callback != null)
		{
			return _callback.onAsyncTaskInBackground(taskName, params);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 异步任务完成
	 * 
	 * @param taskName
	 * @throws result
	 */
	public void onAsyncTaskEnd(String taskId, String taskName, Object[] params, Object result)
	{
		if (_callback != null)
		{
			_callback.onAsyncTaskEnd(taskId, taskName, params, result);
		}
	}
	
	/**
	 * 异步任务被取�?
	 * 
	 * @param taskName
	 */
	public void onAsyncTaskCancelled(String taskName)
	{
		if (_callback != null)
		{
			_callback.onAsyncTaskCancelled(taskName);
		}
	}
	
	/**
	 * 异步任务发生错误
	 * 
	 * @param taskName
	 * @param e
	 */
	public void onAsyncTaskError(String taskId, String taskName, Exception e)
	{
		if (_callback != null)
		{
			_callback.onAsyncTaskError(taskId, taskName, e);
		}
	}
	
	/**
	 * 后台运行�?
	 */
	class BackgroundWorker extends AsyncTask<Object, Object, Object>
	{
		/**
		 * 异步任务
		 */
		private TaskInfo _task;
		
		@Override
		protected void onPreExecute()
		{
			_task = getTaskInfo();
			if (_task != null)
			{
				onAsyncTaskStart(_task.Name, _task.Params);
			}
		}
		
		@Override
		protected Object doInBackground(Object... params)
		{
			Object result = null;
			try
			{
				result = onAsyncTaskInBackground(_task.Name, _task.Params);
			}
			catch (RuntimeException e)
			{
				if (_task != null)
				{
					_task.Status = ETaskStatus.Error;
					_task.Exception = e;
				}
			}
			catch (Exception e)
			{
				if (_task != null)
				{
					_task.Status = ETaskStatus.Error;
					_task.Exception = e;
				}
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(Object result)
		{
			try
			{
				if (_task != null)
				{
					if (_task.Status == ETaskStatus.Error)
					{
						onAsyncTaskError(_task.Id, _task.Name, _task.Exception);
						return;
					}
					
					if (_task.Status != ETaskStatus.Cancelled)
					{
						onAsyncTaskEnd(_task.Id, _task.Name, _task.Params, result);
					}
				}
			}
			finally
			{
				removeAsyncTask(_task);
			}
		}
		
		@Override
		protected void onProgressUpdate(Object... values)
		{
			
		}
		
		@Override
		protected void onCancelled()
		{
			try
			{
				if (_task != null)
				{
					onAsyncTaskCancelled(_task.Name);
				}
			}
			finally
			{
				removeAsyncTask(_task);
			}
		}
		
		private TaskInfo getTaskInfo()
		{
			String taskId = "";
			for (String key : _asyncTasks.keySet())
			{
				if (_asyncTasks.get(key).Worker == this)
				{
					taskId = key;
					break;
				}
			}
			
			if (!taskId.equals(""))
			{
				return _asyncTasks.get(taskId);
			}
			else
			{
				return null;
			}
		}
		
		private void removeAsyncTask(TaskInfo info)
		{
			if (info != null)
			{
				_asyncTasks.remove(info.Id);
				info.clear();
				info = null;
				
				// 执行等待的异步任�?
				executeWaitAsyncTask();
			}
		}
	}
	
	class TaskInfo
	{
		public String Id;
		public String Name;
		public Object[] Params;
		public BackgroundWorker Worker;
		public ETaskStatus Status;
		public Exception Exception;
		
		public void clear()
		{
			this.Id = null;
			this.Name = null;
			this.Params = null;
			this.Worker = null;
			this.Status = ETaskStatus.None;
			this.Exception = null;
		}
	}
	
	enum ETaskStatus
	{
		None,
		Start,
		Running,
		End,
		Cancelled,
		Error
	}
}
