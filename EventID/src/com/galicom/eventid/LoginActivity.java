package com.galicom.eventid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import static us.monoid.web.Resty.*;
import us.monoid.web.Resty.*;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	public void profileOnClick(View view)
	{
		Intent intent;
		intent = new Intent(this, ProfilActivity.class);
		startActivity(intent);
	}
	public void logoutOnClick(View view)
	{
		Intent intent;
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}


}
