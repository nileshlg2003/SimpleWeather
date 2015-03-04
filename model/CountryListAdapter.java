package com.redorigami.simpleweather.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

import com.redorigami.simpleweather.R;

import java.util.List;


/**
 * Created by nilesh on 2/10/2015.
 */
public class CountryListAdapter extends ArrayAdapter {

    private Context context;
    private boolean useList = true;
    private  Integer[] imgid = null;


    public CountryListAdapter(Context context, List items, Integer[] imgid) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
        this.imgid = imgid;

    }



    /**
     * Holder for the list items.
     */
    private class ViewHolder {
        TextView titleText;
        ImageView img;
    }

    /**
     * * @param position
     * * @param convertView
     * * @param parent
     * * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        CountryListItem item = (CountryListItem) getItem(position);
        View viewToUse = null; // This block exists to inflate the settings list item conditionally based on whether // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if (useList) {
                viewToUse = mInflater.inflate(R.layout.country_list_item, null);
            } else {
                viewToUse = mInflater.inflate(R.layout.country_grid_item, null);
            }
           //v = mInflater.inflate(R.layout.)
            //ImageView img =(ImageView)mInflater.inflate(R.layout.)
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.titleTextView);
            holder.img = (ImageView)viewToUse.findViewById(R.id.img);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        holder.titleText.setText(item.getItemTitle());
        holder.img.setImageResource(imgid[position]);
        return viewToUse;
    }

}
