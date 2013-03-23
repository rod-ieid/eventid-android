package com.galicom.eventid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class ProfilActivity extends Activity {

	/*public ProfilActivity(String vTexteEmail) {
		String text =vTexteEmail; EditText edtText = (EditText) findViewById(R.id.editText3); edtText.setText(text);
	}*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profil, menu);
		return true;
	}
	public void doneOnClick(View view)
	{
		Intent intent;
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	// EditText editText = (EditText)findViewById(R.id.editText3); editText.setText("email");

}
