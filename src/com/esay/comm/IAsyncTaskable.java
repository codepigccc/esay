package com.esay.comm;


public interface IAsyncTaskable
{
	String executeAsyncTask(String taskName, Object[] params);
	void cancelAsyncTask(String taskId);
	void onAsyncTaskStart(String taskName, Object[] params);
	Object onAsyncTaskInBackground(String taskName, Object[] params) throws Exception;
	void onAsyncTaskEnd(String taskId, String taskName, Object[] params, Object result);
	void onAsyncTaskCancelled(String taskName);
	void onAsyncTaskError(String taskId, String taskName, Exception e);
}
