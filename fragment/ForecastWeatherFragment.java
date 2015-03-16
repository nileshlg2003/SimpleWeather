package com.redorigami.simpleweather.fragment;

/**
 * Created by nilesh on 3/10/2015.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.redorigami.simpleweather.R;
import com.redorigami.simpleweather.adapter.WeatherAdapter;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.WeatherForecast;


public class ForecastWeatherFragment extends WeatherFragmented {

    private SharedPreferences prefs;
    private ListView forecastList;
    private WeatherAdapter weatherAdapter;

    public static ForecastWeatherFragment newInstance() {
        ForecastWeatherFragment fragment = new ForecastWeatherFragment();
        return fragment;
    }

    public ForecastWeatherFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.forecast_fragment, container, false);
        forecastList = (ListView) v.findViewById(R.id.forecastDays);
        return v;

    }

    public void refreshData() {
        refresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
        // Update forecast

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String cityId = prefs.getString("cityid", null);
       // String cityId =
        System.out.println("Here city Id is " + cityId);

        //weatherClient.getForecastWeather(cityId);

        weatherClient.getForecastWeather(cityId, new WeatherClient.ForecastWeatherEventListener() {
            @Override
            public void onWeatherRetrieved(WeatherForecast forecast) {
                WeatherAdapter adp = new WeatherAdapter(forecast, getActivity());
                forecastList.setAdapter(adp);
            }

            @Override
            public void onWeatherError(WeatherLibException t) {

            }

            @Override
            public void onConnectionError(Throwable t) {
                //WeatherDialog.createErrorDialog("Error parsing data. Please try again", MainActivity.this);
            }
        });
    }
}