package com.galicom.eventid;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;


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
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		
		Log.d("truc","truc");
	}
	
	public void logoutOnClick(View view)
	{
	    Intent intent;
		intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		getSharedPreferences("login", 0).edit().clear().commit(); // efface les mémorisation des champs
		finish(); // tue la méthode loginActivity pour revenir au min activity
	}
	
	public void interaction(View view)
	{
		Intent intent;
		intent = new Intent(this, GenerateQRCode.class);
		startActivity(intent);
	}
	
	/*public static void encodeText (Activity activity, String text){
		Intent i =new Intent();
		i.setAction("com.google.zxing.client.android.ENCODE");
		i.putExtra("ENCODE_TYPE", "TEXT_TYPE");
		i.putExtra("ENCODE_DATA", text);
		activity.startActivity(i);
	}*/
	 

}
