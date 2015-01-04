package com.esay.app;

import android.os.Bundle;
import android.view.View;

import com.esay.base.BaseATY;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class LoginSelectATY extends BaseATY
{
   @ViewInject(R.id.login_select_lay_login)
   private View mLayLogin;
   @ViewInject(R.id.login_select_lay_reg)
   private View mLayReg;
   @ViewInject(R.id.login_select_lay_guest)
   private View mLayGuest;
   
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.login_select);
	  ViewUtils.inject(this);
	  
	  
   }
   
   
   @OnClick(R.id.login_select_lay_login)
   private void goLogin(View v)
   {
	  
   }
   @OnClick(R.id.login_select_lay_reg)
   private void goRegister(View v)
   {
	  
   }
   
   @OnClick(R.id.login_select_lay_guest)
   private void goGuestLogin(View v)
   {
	  
   }
}
