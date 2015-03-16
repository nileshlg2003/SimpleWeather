package com.redorigami.simpleweather.model;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.redorigami.simpleweather.R;

public class ChooseWeather extends Activity {
	ImageButton GCCweather;
	ImageButton Worldweather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_weather);
				
		GCCweather = (ImageButton) findViewById(R.id.imageButton);
		GCCweather.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent k = new Intent(ChooseWeather.this, GCCcities.class);
		    	startActivity(k);*/
                Intent j = new Intent(ChooseWeather.this, Country.class);
                startActivity(j);

				
			}
 
		});
		 
    	
    	Worldweather = (ImageButton) findViewById(R.id.imageButton2);
    	Worldweather.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

                Intent j = new Intent(ChooseWeather.this, WeatherActivity.class);
                startActivity(j);
               /* FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CountryFragment countryFragment = new CountryFragment();
               fragmentTransaction.add(countryFragment, "this");*/


			}
 
		});
		 
	}
}
