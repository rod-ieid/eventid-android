package com.galicom.eventid;

import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import static us.monoid.web.Resty.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;
import us.monoid.web.Resty.*;

public class MainActivity extends Activity {
	
	final Context context = this;
	//final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	       
		
		SharedPreferences settings	=getSharedPreferences("login", 0); // methode recuperant les données
		if(settings.getString("email", null) != null)  // verifie si les champs sont deja mémoriser
			loginOnClick(null);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// CREATION DES MENUE BOUTONS
	// fonction qui va sur la page Signup lorsqu'on clique sur le bouton Signup
	public void signupOnClick(View view)
	{
		Intent intent;
		intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
	}
	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void loginOnClick(View view)
	{
		final EditText vEditText1 = (EditText)findViewById(R.id.editText2); // declaration du champs text pour le mdp
		final EditText vEditText2 = (EditText)findViewById(R.id.editText3); // declaration du champs text pour l'email
		final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

		String vTexteEmail;
		String vTextePassword;
		
		
		SharedPreferences settings	=getSharedPreferences("login", 0);
		if(view != null) {
		vTexteEmail = vEditText2.getText().toString(); // récuperation du champs email
		vTextePassword = vEditText1.getText().toString();
		} else {
			vTexteEmail = settings.getString("email", null); //recuperation mail
			vTextePassword = settings.getString("password", null);
		
		}
		//Log.d("TEST", vTexteEmail);
		//Log.d("TEST2", vTextePassword);

		//ProfilActivity objet = new ProfilActivity(vTexteEmail);

		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		/*

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://eventid.rodrigueh.com/api/authentication");

			try {
			    // Add your data
			    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			    nameValuePairs.add(new BasicNameValuePair("Username", vTexteEmail));
			    nameValuePairs.add(new BasicNameValuePair("Password", vTextePassword));
			    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			    // Execute HTTP Post Request
			    HttpResponse response = httpclient.execute(httppost);

			} catch (ClientProtocolException e) {
			    // TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			    // TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		if (vTexteEmail.equals("")|| vTextePassword.equals("") )
		{
			erreur();
		}

		else{

			Resty r = new Resty();
			//r.withHeader("Content-Type", "application/json");
			JSONObject obj = new JSONObject();

			try {
				obj.accumulate("Username",vTexteEmail);
				obj.accumulate("Password",vTextePassword);

				Object user;
				user = r.json("http://eventid.rodrigueh.com/api/authentication", content(obj));
				if(user != null)
				{
					JSONResource jsUser = (JSONResource)user;

					Student st = new Student();
					st.setEmail(jsUser.get("Email").toString());
					Log.d("auth",jsUser.get("Email").toString());
					st.setFirstName(jsUser.get("FirstName").toString());
					Log.d("auth",jsUser.get("FirstName").toString());
					st.setLastName(jsUser.get("LastName").toString());
					Log.d("auth",jsUser.get("LastName").toString());
					st.setPassword(jsUser.get("Password").toString());
					Log.d("auth",jsUser.get("Password").toString());
					st.setId((Integer) jsUser.get("Id"));
					Log.d("auth",jsUser.get("Id").toString());


					Session.getInstance().setCurrentStudent(st);
					//Session.getInstance().getCurrentStudent(); récuperer étudiant courant
				}
				
				
		       
				//if (checkBox1.isChecked()) {
					SharedPreferences.Editor editor = settings.edit(); // enregistre les identifiants 
					editor.putString("email", vTexteEmail);
					editor.putString("password", vTextePassword);
					editor.commit();
				//}
				
		        Intent intent;
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				
				Intent intent2 = new Intent("com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent2, 0);
				/*
				user = r.json("http://eventid.rodrigueh.com/api/authentication");
	               Representation rep = res.get();
	               try {
	                   Log.d(TAG, rep.getText());
	               } catch (IOException e) {
	                   Log.d(TAG, e.getMessage());
	               }
	               */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				erreur();
			}


			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				erreur();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				erreur();
			}


		}


	}
	// verification des champs
	/*if (vTexteEmail.equals("")&& vTextePassword.equals("") )
		{
			Intent intent;
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);*/


	/*else{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setTitle("incorrect authentication");
			alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			});
			// creer la boite d'alert
			AlertDialog alertDialog = alertDialogBuilder.create();

			// affiche l'alerte
			alertDialog.show();

		}*/

	public void erreur(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("incorrect authentication");
		alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			}
		});
		// creer la boite d'alert
		AlertDialog alertDialog = alertDialogBuilder.create();

		// affiche l'alerte
		alertDialog.show();


	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		   if (requestCode == 0) {
		      if (resultCode == RESULT_OK) {
		         String contents = intent.getStringExtra("SCAN_RESULT");
		         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
		         // Handle successful scan
		      } else if (resultCode == RESULT_CANCELED) {
		         // Handle cancel
		      }
		   }
		}

}







