package com.redorigami.simpleweather.model;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.redorigami.simpleweather.R;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setUpMapIfNeeded();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                LatLng position = marker.getPosition();
                Double Lat = position.latitude;
                Double Lon = position.longitude;


                System.out.println(marker.getId() + "is Clicked");
                Intent j = new Intent(MapsActivity.this, Weathercity.class);
                Bundle b = new Bundle();
                b.putDouble("Lat", Lat);
                b.putDouble("Lon", Lon);
                j.putExtras(b);
                //j.putExtra("Lon", Lon);
                startActivity(j);
                if (marker.getTitle() == "Muscat"){
                    System.out.println(marker.getTitle() + "is Clicked");
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                   /* Intent j = new Intent(MapsActivity.this, Weathercity.class);
                    startActivity(j);*/
                }else if (marker.getTitle() == "Dubai"){
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                }else if (marker.getTitle() == "Manama"){
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                }else if (marker.getTitle() == "Riyadh"){
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                }else if (marker.getTitle() == "Doha"){
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                }else if (marker.getTitle() == "Kuwait"){
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(23.613871, 58.592201)).title("Muscat"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(25.258169, 55.304722)).title("Dubai"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(26.215361, 50.583199)).title("Manama"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(24.687731, 46.721851)).title("Riyadh"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(25.286667, 51.533333)).title("Doha"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.369720, 47.978329)).title("Kuwait"));
    }
}
