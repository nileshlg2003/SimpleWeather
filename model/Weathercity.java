package com.redorigami.simpleweather.model;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.redorigami.simpleweather.R;
import com.redorigami.simpleweather.util.WeatherIconMapper;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.client.volley.WeatherClientDefault;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.exception.WeatherProviderInstantiationException;
import com.survivingwithandroid.weather.lib.model.City;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;
import com.survivingwithandroid.weather.lib.request.WeatherRequest;
import com.survivingwithandroid.weather.lib.util.WeatherUtility;
import com.survivingwithandroid.weather.lib.util.WindDirection;

import java.util.List;

public class Weathercity extends ActionBarActivity {

    private TextView tempView;
    private TextView tempMin;
    private TextView tempMax;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView humidity;
    private TextView pressure;
    private TextView pressureStat;
    private TextView sunrise;
    private TextView sunset;
    private TextView cloud;
    private TextView cityText;
    private TextView condDescr;
    private TextView rain;
    private TextView colorTextLine;

    private WeatherConfig config;
    private ImageView imgView;
    double Long;
    double Lat;
   // private ProgressBar bar;
    //private CityAdapter adp;
    private WeatherClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathercity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Lat = extras.getDouble("Lat");
            Long = extras.getDouble("Lon");
        }

        try {
            WeatherClient client = (new WeatherClient.ClientBuilder().attach(this))
                    .httpClient(WeatherClientDefault.class)
                    .provider(new OpenweathermapProviderType())
                    .config(new WeatherConfig())
                    .build();

            config = new WeatherConfig();




            client.getCurrentCondition(new WeatherRequest(Long, Lat), new WeatherClient.WeatherEventListener() {
                @Override
                public void onWeatherRetrieved(CurrentWeather currentWeather) {
                    float currentTemp = currentWeather.weather.temperature.getTemp();
                    Log.d("WL", "City [" + currentWeather.weather.location.getCity() + "] Current temp [" + currentTemp + "]");

                    imgView = (ImageView) findViewById(R.id.imgWeather);
                    tempView = (TextView) findViewById(R.id.temp);
                    tempMin = (TextView) findViewById(R.id.      tempMin);
                    tempMax = (TextView) findViewById(R.id.      tempMax);
                    windSpeed = (TextView) findViewById(R.id.    windSpeed);
                    windDeg = (TextView) findViewById(R.id.      windDeg);
                    humidity = (TextView) findViewById(R.id.     humidity);
                    pressure = (TextView) findViewById(R.id.     pressure);
                    pressureStat = (TextView) findViewById(R.id. pressureStat);
                    sunrise = (TextView) findViewById(R.id.      sunrise);
                    sunset = (TextView) findViewById(R.id.       sunset);
                    cityText = (TextView) findViewById(R.id.location);
                    condDescr = (TextView) findViewById(R.id.descrWeather);
                    cloud = (TextView) findViewById(R.id.cloud);
                    colorTextLine = (TextView)findViewById(R.id.lineTxt);
                    rain = (TextView) findViewById(R.id.rain);

                    cityText.setText(currentWeather.weather.location.getCity() + "," + currentWeather.weather.location.getCountry());
                    condDescr.setText(currentWeather.weather.currentCondition.getCondition() + "(" + currentWeather.weather.currentCondition.getDescr() + ")");

                    tempView.setText("Temp:" + currentWeather.weather.temperature.getTemp());
                    tempMin.setText(currentWeather.weather.temperature.getMinTemp() + currentWeather.getUnit().tempUnit);
                    tempMax.setText(currentWeather.weather.temperature.getMaxTemp() + currentWeather.getUnit().tempUnit);
                    windSpeed.setText(currentWeather.weather.wind.getSpeed() + currentWeather.getUnit().speedUnit);
                    windDeg.setText((int) currentWeather.weather.wind.getDeg() + "° (" + WindDirection.getDir((int) currentWeather.weather.wind.getDeg()) + ")");
                    humidity.setText( currentWeather.weather.currentCondition.getHumidity() + "%");
                    pressure.setText( currentWeather.weather.currentCondition.getPressure() + currentWeather.getUnit().pressureUnit);
                    colorTextLine.setBackgroundResource(WeatherUtil.getResource(currentWeather.weather.temperature.getTemp(),config ));
                    cloud.setText(currentWeather.weather.clouds.getPerc() + "%");

                    if (currentWeather.weather.rain[0].getTime() != null && currentWeather.weather.rain[0].getAmmount() != 0)
                        rain.setText(currentWeather.weather.rain[0].getTime() + ":" + currentWeather.weather.rain[0].getAmmount());
                    else
                        rain.setText("----");

                    sunrise.setText(com.redorigami.simpleweather.util.WeatherUtil.convertDate(currentWeather.weather.location.getSunrise()));

                    sunset.setText(com.redorigami.simpleweather.util.WeatherUtil.convertDate(currentWeather.weather.location.getSunset()));

                    imgView.setImageResource(WeatherIconMapper.getWeatherResource(currentWeather.weather.currentCondition.getIcon(), currentWeather.weather.currentCondition.getWeatherId()));

                }


                @Override
                public void onWeatherError(WeatherLibException e) {
                    Log.d("WL", "Weather Error - parsing data");
                    e.printStackTrace();

                }

                @Override
                public void onConnectionError(Throwable throwable) {
                    Log.d("WL", "Connection error");
                    throwable.printStackTrace();

                }
            });
        }catch (WeatherProviderInstantiationException wpie){
            wpie.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weathercity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
