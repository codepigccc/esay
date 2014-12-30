package com.esay.app;

import android.os.Bundle;

import com.esay.base.BaseATY;

public class SplashATY extends BaseATY
{
   @Override
   public void onAsyncTaskStart(String taskName, Object[] params)
   {
   }

   @Override
   public Object onAsyncTaskInBackground(String taskName, Object[] params)
   {
	  return null;
   }

   @Override
   public void onAsyncTaskEnd(String taskId, String taskName, Object[] params, Object result)
   {
   }

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
   }  
   
   
}
