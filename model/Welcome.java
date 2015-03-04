package com.redorigami.simpleweather.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.redorigami.simpleweather.R;

public class Welcome extends Activity {
	
	Button Enter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Enter = (Button) findViewById(R.id.button1);
		Enter.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent j = new Intent(Welcome.this, LoginActivity.class);
				 startActivity(j);
			}
 
		});
		 
	}
}
