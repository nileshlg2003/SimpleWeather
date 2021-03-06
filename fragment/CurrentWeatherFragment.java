package com.redorigami.simpleweather.fragment;

/**
 * Created by nilesh on 3/10/2015.
 */
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redorigami.simpleweather.R;
import com.redorigami.simpleweather.util.WeatherIconMapper;
import com.redorigami.simpleweather.util.WeatherUtil;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.client.volley.WeatherClientDefault;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.model.Weather;
import com.survivingwithandroid.weather.lib.model.WeatherHourForecast;
import com.survivingwithandroid.weather.lib.util.LogUtils;
import com.survivingwithandroid.weather.lib.util.WindDirection;



public class CurrentWeatherFragment extends WeatherFragmented {


    private SharedPreferences prefs;

    // UI elements
    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView unitTemp;
    private TextView hum;
    private ImageView imgView;
    private TextView tempMin;
    private TextView tempMax;
    private TextView sunset;
    private TextView sunrise;
    private TextView cloud;
    private TextView colorTextLine;
    private TextView rain;

    private WeatherConfig config;

    public static CurrentWeatherFragment newInstance() {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        return fragment;
    }
    public CurrentWeatherFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.current_fragment, container, false);
        cityText = (TextView) v.findViewById(R.id.location);
        temp = (TextView) v.findViewById(R.id.temp);
        condDescr = (TextView) v.findViewById(R.id.descrWeather);
        imgView = (ImageView) v.findViewById(R.id.imgWeather);
        hum = (TextView) v.findViewById(R.id.humidity);
        press = (TextView) v.findViewById(R.id.pressure);
        windSpeed = (TextView) v.findViewById(R.id.windSpeed);
        windDeg = (TextView) v.findViewById(R.id.windDeg);
        tempMin = (TextView) v.findViewById(R.id.tempMin);
        tempMax = (TextView) v.findViewById(R.id.tempMax);
        unitTemp = (TextView) v.findViewById(R.id.tempUnit);
        sunrise = (TextView) v.findViewById(R.id.sunrise);
        sunset = (TextView) v.findViewById(R.id.sunset);
        cloud = (TextView) v.findViewById(R.id.cloud);
        colorTextLine = (TextView) v.findViewById(R.id.lineTxt);
        rain = (TextView) v.findViewById(R.id.rain);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    public void refreshData() {
        refresh();
    }

    private void refresh() {
        config = new WeatherConfig();
        String cityId = prefs.getString("cityid", null);
        Log.d("Swa", "City Id [" + cityId + "]");

        if (cityId == null) {
            getListener().requestCompleted();
            return ;
        }

        config.lang = WeatherUtil.getLanguage(prefs.getString("swa_lang", "en"));
        config.maxResult = 5;
        config.numDays = 5;

        String unit = prefs.getString("swa_temp_unit", "c");
        if (unit.equals("c"))
            config.unitSystem = WeatherConfig.UNIT_SYSTEM.M;
        else
            config.unitSystem = WeatherConfig.UNIT_SYSTEM.I;


        weatherClient.updateWeatherConfig(config);

        weatherClient.getCurrentCondition(cityId, new WeatherClient.WeatherEventListener() {
            @Override
            public void onWeatherRetrieved(CurrentWeather cWeather) {
                Weather weather = cWeather.weather;
                getListener().requestCompleted();
                cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
                condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
                LogUtils.LOGD("SwA", "Temp [" + temp + "]");
                LogUtils.LOGD("SwA", "Val [" + weather.temperature.getTemp() + "]");
                temp.setText("" + ((int) weather.temperature.getTemp()));
                unitTemp.setText(cWeather.getUnit().tempUnit);
                colorTextLine.setBackgroundResource(WeatherUtil.getResource(weather.temperature.getTemp(), config));
                hum.setText(weather.currentCondition.getHumidity() + "%");
                tempMin.setText(weather.temperature.getMinTemp() + cWeather.getUnit().tempUnit);
                tempMax.setText(weather.temperature.getMaxTemp() + cWeather.getUnit().tempUnit);
                windSpeed.setText(weather.wind.getSpeed() + cWeather.getUnit().speedUnit);
                windDeg.setText((int) weather.wind.getDeg() + "° (" + WindDirection.getDir((int) weather.wind.getDeg()) + ")");
                press.setText(weather.currentCondition.getPressure() + cWeather.getUnit().pressureUnit);

                sunrise.setText(WeatherUtil.convertDate(weather.location.getSunrise()));

                sunset.setText(WeatherUtil.convertDate(weather.location.getSunset()));

                imgView.setImageResource(WeatherIconMapper.getWeatherResource(weather.currentCondition.getIcon(), weather.currentCondition.getWeatherId()));

                /*
                client.getDefaultProviderImage(weather.currentCondition.getIcon(), new WeatherClient.WeatherImageListener() {
                    @Override
                    public void onImageReady(Bitmap image) {
                        imgView.setImageBitmap(image);
                    }
                });
                */
                cloud.setText(weather.clouds.getPerc() + "%");

                if (weather.rain[0].getTime() != null && weather.rain[0].getAmmount() != 0)
                    rain.setText(weather.rain[0].getTime() + ":" + weather.rain[0].getAmmount());
                else
                    rain.setText("----");

            }

            @Override
            public void onWeatherError(WeatherLibException t) {
                //WeatherDialog.createErrorDialog("Error parsing data. Please try again", MainActivity.this);
                getListener().requestCompleted();
            }

            @Override
            public void onConnectionError(Throwable t) {
                //WeatherDialog.createErrorDialog("Error parsing data. Please try again", MainActivity.this);
                getListener().requestCompleted();
            }
        });



    }




}
