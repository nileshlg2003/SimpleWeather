package com.redorigami.simpleweather.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.redorigami.simpleweather.R;
import com.redorigami.simpleweather.adapter.CityAdapter;
import com.redorigami.simpleweather.model.Weather;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.client.volley.WeatherClientDefault;
import com.survivingwithandroid.weather.lib.exception.LocationProviderNotFoundException;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.exception.WeatherProviderInstantiationException;
import com.survivingwithandroid.weather.lib.model.City;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;

import java.util.ArrayList;
import java.util.List;

public class SearchLocationActivity extends Activity {
    private ListView cityListView;
    private ProgressBar bar;
    private CityAdapter adp;
    private WeatherClient clienttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        WeatherClient client = null;
        try {
            clienttt = (new WeatherClient.ClientBuilder().attach(this))
                    .httpClient(WeatherClientDefault.class)
                    .provider(new OpenweathermapProviderType())
                    .config(new WeatherConfig())
                    .build();
        } catch (WeatherProviderInstantiationException e) {
            e.printStackTrace();
        }

        //client = WeatherContext.getInstance().getClient(this);

        Log.d("App", "Client [" + client + "]");

        cityListView = (ListView) findViewById(R.id.cityList);
        bar = (ProgressBar) findViewById(R.id.progressBar2);
        adp = new CityAdapter(SearchLocationActivity.this, new ArrayList<City>());
        cityListView.setAdapter((android.widget.ListAdapter) adp);

        ImageView searchView = (ImageView) findViewById(R.id.imgSearch);
        final EditText edt = (EditText) findViewById(R.id.cityEdtText);

        edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(v.getText().toString());
                    return true;
                }

                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                search(edt.getEditableText().toString());
            }
        });

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos,
                                    long id) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(SearchLocationActivity.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                City city = (City) parent.getItemAtPosition(pos);
                editor.putString("cityid", city.getId());
                editor.putString("cityName", city.getName());
                editor.putString("country", city.getCountry());
                editor.commit();
                //NavUtils.navigateUpFromSameTask(SearchLocationActivity.this);
                finish();
            }
        });

        ImageView locImg = (ImageView) findViewById(R.id.imgLocationSearch);
        final WeatherClient finalClient = client;
        locImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                try {

                    clienttt.searchCityByLocation(WeatherClient.createDefaultCriteria(), new WeatherClient.CityEventListener() {
                        @Override
                        public void onCityListRetrieved(List<City> cities) {
                            bar.setVisibility(View.GONE);
                            adp.setCityList(cities);
                            adp.notifyDataSetChanged();

                        }

                        @Override
                        public void onWeatherError(WeatherLibException e) {
                            bar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onConnectionError(Throwable throwable) {
                            bar.setVisibility(View.GONE);

                        }
                    });
                    /*finalClient.searchCityByLocation(WeatherClient.createDefaultCriteria(), new WeatherClient.CityEventListener() {

                        @Override
                        public void onCityListRetrieved(List<City> cityList) {
                            bar.setVisibility(View.GONE);
                            adp.setCityList(cityList);
                             adp.notifyDataSetChanged();
                        }

                        @Override
                        public void onWeatherError(WeatherLibException wle) {
                            bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onConnectionError(Throwable t) {
                            bar.setVisibility(View.GONE);
                        }
                    });*/
                }
                catch(LocationProviderNotFoundException lpnfe) {

                }
            }

        });
    }

    private void search(String pattern) {
        bar.setVisibility(View.VISIBLE);
        clienttt.searchCity(pattern, new WeatherClient.CityEventListener() {
            @Override
            public void onCityListRetrieved(List<City> cityList) {
                bar.setVisibility(View.GONE);
                adp.setCityList(cityList);
                //adp.notifyDataSetChanged();
            }

            @Override
            public void onWeatherError(WeatherLibException t) {
                bar.setVisibility(View.GONE);
            }

            @Override
            public void onConnectionError(Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });
    }

}

