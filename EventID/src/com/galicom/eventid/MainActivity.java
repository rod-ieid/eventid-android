package com.galicom.eventid;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends Activity {
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


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
	public void loginOnClick(View view)
	{
		final EditText vEditText1 = (EditText)findViewById(R.id.editText2); // declaration du champs text pour le mdp
		final EditText vEditText2 = (EditText)findViewById(R.id.editText3); // declaration du champs text pour l'email

		String vTexteEmail = vEditText2.getText().toString(); // récuperation du champs email
		String vTextePassword = vEditText1.getText().toString();
		
		// verification des champs
		if (vTexteEmail.equals("email")&& vTextePassword.equals("password") )
		{
			Intent intent;
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

		else{
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

	}

}







