package com.redorigami.simpleweather.adapter;

/**
 * Created by nilesh on 3/10/2015.
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.redorigami.simpleweather.R;
import com.redorigami.simpleweather.util.WeatherIconMapper;
import com.survivingwithandroid.weather.lib.model.BaseWeather;
import com.survivingwithandroid.weather.lib.model.WeatherForecast;
import com.survivingwithandroid.weather.lib.model.DayForecast;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends ArrayAdapter<DayForecast>{

    private List<DayForecast> dayForecastList;
    private Context ctx;
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("E");
    private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("dd/MMM");
    private BaseWeather.WeatherUnit units;

    public WeatherAdapter(WeatherForecast forecast, Context ctx) {
        super(ctx, R.layout.row_forecast_layout);
        this.dayForecastList = forecast.getForecast();
        units = forecast.getUnit();
        this.ctx = ctx;
    }



    @Override
    public int getCount() {
        return dayForecastList.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_forecast_layout, parent, false);
        }

        // We need to apply holder pattern

        TextView dayText = (TextView) convertView.findViewById(R.id.dayName);
        TextView dayDate = (TextView) convertView.findViewById(R.id.dayDate);
        ImageView icon = (ImageView) convertView.findViewById(R.id.dayIcon);
        TextView minTempText = (TextView) convertView.findViewById(R.id.dayTempMin);
        TextView maxTempText = (TextView) convertView.findViewById(R.id.dayTempMax);
        TextView dayCloud = (TextView) convertView.findViewById(R.id.dayCloud);
        TextView dayDescr = (TextView) convertView.findViewById(R.id.dayDescr);
        TextView dayRain = (TextView) convertView.findViewById(R.id.dayRain);

        DayForecast forecast = dayForecastList.get(position);
        Date d = new Date();
        Calendar gc =  new GregorianCalendar();
        gc.setTime(d);
        gc.add(GregorianCalendar.DAY_OF_MONTH, position + 1);
        dayText.setText(sdfDay.format(gc.getTime()));
        dayDate.setText(sdfMonth.format(gc.getTime()));

        icon.setImageResource(WeatherIconMapper.getWeatherResource(forecast.weather.currentCondition.getIcon(), forecast.weather.currentCondition.getWeatherId()));
        Log.d("SwA", "Min [" + minTempText + "]");

        minTempText.setText( Math.round(forecast.forecastTemp.min) + units.tempUnit);
        maxTempText.setText( Math.round(forecast.forecastTemp.max) + units.tempUnit);
        dayCloud.setText("" + forecast.weather.clouds.getPerc() + "%");
        dayDescr.setText(forecast.weather.currentCondition.getDescr());
        try {
            float rainVal = forecast.weather.rain[0].getAmmount();
            dayRain.setText("Rain:" + String.valueOf((int) rainVal));
        }
        catch(Throwable t) {}
        return convertView;
    }



}