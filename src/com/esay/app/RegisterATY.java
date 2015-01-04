package com.esay.app;

import android.os.Bundle;

import com.esay.base.BaseATY;
import com.lidroid.xutils.ViewUtils;

public class RegisterATY extends BaseATY
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.register);
	  ViewUtils.inject(this);
   }
}
