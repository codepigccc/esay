package com.esay.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.esay.base.BaseATY;
import com.esay.comm.Session;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SplashATY extends BaseATY
{
   @ViewInject(R.id.splash_lay)
   private LinearLayout mLay;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.splash);
	  ViewUtils.inject(this);
	 
	  Session.getInstance();
	  LogUtils.customTagPrefix="ESAY";

	  Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_small);
	  animation.setAnimationListener(new Animation.AnimationListener()
	  {
		 @Override
		 public void onAnimationStart(Animation animation)
		 {

		 }

		 @Override
		 public void onAnimationRepeat(Animation animation)
		 {

		 }

		 @Override
		 public void onAnimationEnd(Animation animation)
		 {
			try
			{
			   Thread.sleep(2000);
			}
			catch (Exception e)
			{
			   LogUtils.e(e.getMessage(), e);
		    }
			startActivity(new Intent(mContext, MainATY.class));
		 }
	  });
	  mLay.startAnimation(animation);
   }

}
