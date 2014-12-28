package com.esay.comm;

import android.app.Application;
import android.util.Log;

/**
 * Session全局静态变量,用于存储公共信息,生命周期同整个APPLICATION
 * 
 * @author Administrator
 * 
 */
public class Session extends Application
{
   private static Session instance = null;
   public final static String DEFAULT_USER_ID = "10000";
   public static synchronized Session getInstance()
   {
	  if (instance == null)
	  {
		 instance = new Session();
	  }
	  return instance;
   }
   
   @Override
   public void onCreate()
   {
	  super.onCreate();
	  instance = (Session) getApplicationContext();

	  try
	  {

	  }
	  catch (Exception e)
	  {
		 Log.d("ERROR", "Session_onCreate() " + e);
	  }
   }
}
