package com.redorigami.simpleweather.fragment;

/**
 * Created by nilesh on 3/10/2015.
 */
import android.app.Fragment;
import android.os.Bundle;

import com.redorigami.simpleweather.model.WeatherContext;
import com.survivingwithandroid.weather.lib.WeatherClient;


/**
 * ${copyright}.
 */
public abstract class WeatherFragmented extends Fragment {

    protected WeatherClient weatherClient;

    public WeatherFragmented() {


    }

    protected WeatherEventListener getListener() {
        return ( (WeatherEventListener) getActivity());
    }

    public static interface WeatherEventListener {
        public void requestCompleted();
    }

    public abstract void refreshData();

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(android.os.Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherClient = WeatherContext.getInstance().getClient(getActivity());
    }
}
