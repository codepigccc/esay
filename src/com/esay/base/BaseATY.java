package com.esay.base;

import com.esay.comm.AsyncTaskManager;
import com.esay.comm.IAsyncTaskable;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class BaseATY extends Activity implements IAsyncTaskable
{
   @Override
   public void onBackPressed()
   {
	  super.onBackPressed();
	  finish();
   }

   protected Context mContext=BaseATY.this;
   
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
   }

   @Override
   protected void onDestroy()
   {
	  super.onDestroy();
   }

   @Override
   protected void onPause()
   {
	  super.onPause();
   }

   @Override
   protected void onRestart()
   {
	  super.onRestart();
   }

   @Override
   protected void onResume()
   {
	  super.onResume();
   }

   @Override
   protected void onStart()
   {
	  super.onStart();
   }

   @Override
   protected void onStop()
   {
	  super.onStop();
   }

   
   
   private AsyncTaskManager _taskManager;
   public AsyncTaskManager getTaskManager()
   {
	  if (_taskManager == null)
		 _taskManager = new AsyncTaskManager(this);
	  return _taskManager;
   }

   /**
    * 执行异步任务
    */
   public String executeAsyncTask()
   {
	  return getTaskManager().executeAsyncTask();
   }

   /**
    * 执行异步任务
    * 
    * @param taskName
    */
   public String executeAsyncTask(String taskName)
   {
	  return getTaskManager().executeAsyncTask(taskName);
   }

   /**
    * 执行异步任务
    * 
    * @param taskName
    */
   public String executeAsyncTask(String taskName, Object[] params)
   {
	  return getTaskManager().executeAsyncTask(taskName, params);
   }

   /**
    * 取消异步任务
    */
   public void cancelAsyncTask()
   {
	  getTaskManager().cancelAsyncTask();
   }

   /**
    * 取消异步任务
    * 
    * @param taskId
    */
   public void cancelAsyncTask(String taskId)
   {
	  getTaskManager().cancelAsyncTask(taskId);
   }

   /**
    * 异步任务开始
    */
   public void onAsyncTaskStart(String taskName, Object[] params)
   {

   }

   /**
    * 异步任务执行
    * 
    * @param taskName
    */
   public Object onAsyncTaskInBackground(String taskName, Object[] params) throws Exception
   {
	  return null;
   }

   /**
    * 异步任务完成
    * 
    * @param arg
    */
   public void onAsyncTaskEnd(String taskId, String taskName, Object[] params, Object result)
   {

   }

   /**
    * 异步任务被取消
    */
   public void onAsyncTaskCancelled(String taskName)
   {

   }

   /**
    * 异步任务发生错误
    * 
    * @param e
    */
   public void onAsyncTaskError(String taskId, String taskName, Exception e)
   {

   } 
}
