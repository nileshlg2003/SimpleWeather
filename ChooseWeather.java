package com.redorigami.simpleweather;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseWeather extends Activity {
	Button GCCweather;
	Button Worldweather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_weather);
				
		GCCweather = (Button) findViewById(R.id.button1);
		GCCweather.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent k = new Intent(ChooseWeather.this, GCCcities.class);
		    	startActivity(k);
				
			}
 
		});
		 
    	
    	Worldweather = (Button) findViewById(R.id.button2);
    	Worldweather.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent j = new Intent(ChooseWeather.this, WeatherActivity.class);
				 startActivity(j);
			}
 
		});
		 
	}
}
