package com.esay.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.esay.base.BaseATY;
import com.lidroid.xutils.ViewUtils;

public class LoginATY extends BaseATY
{

   private ActionBar mActionBar;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.login);
	  ViewUtils.inject(this);
	  mActionBar = getActionBar();

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
	  getMenuInflater().inflate(R.menu.menu_normal, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
	  switch (item.getItemId())
	  {
		 case android.R.id.home:

			return true;
		 case R.id.menu_item_setting:
			Toast.makeText(mContext, "dddddddddd", Toast.LENGTH_LONG).show();
			return true;
		 default:
			return super.onOptionsItemSelected(item);
	  }

   }

}
