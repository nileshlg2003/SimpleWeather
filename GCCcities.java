package com.redorigami.simpleweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class GCCcities extends ActionBarActivity {

	Button Muscat;
	Button Dubai;
	LinearLayout ll ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gcccities);
		if (savedInstanceState == null) {
	        getSupportFragmentManager().beginTransaction()
	                .add(R.id.container, new WeatherFragment())
	                .commit();
	    }
		
		ll = (LinearLayout) findViewById(R.id.Subdef);
		Muscat = (Button) findViewById(R.id.button1);
		Muscat.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				
								
				ll.setVisibility(View.GONE);
				WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                        .findFragmentById(R.id.container);
				wf.changeCity("Muscat");					
				
			} 
		});
		
		Dubai = (Button) findViewById(R.id.button2);
		Dubai.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
								
				/*ll.setVisibility(View.GONE);
				WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                        .findFragmentById(R.id.container);
				wf.changeCity("Dubai");	*/
                Intent j = new Intent(GCCcities.this, Country.class);
                startActivity(j);
				/*Intent j = new Intent(GCCcities.this, MainActivity.class);
				j.putExtra("cityname", "Dubai");
				 startActivity(j);*/
				
			}
 
		});
				
	}
}
