package com.redorigami.simpleweather.model;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.redorigami.simpleweather.R;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Help extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Read raw file into string and populate TextView
        InputStream iFile = getResources().openRawResource(R.raw.help_overview);
        try {
            TextView helpText = (TextView) findViewById(R.id.helpText);
            String strFile = inputStreamToString(iFile);
            helpText.setText(strFile);
        } catch (Exception e) {
            Log.e("Emergency Buddy", "InputStreamToString failure", e);
        }

    }


    public String inputStreamToString(InputStream is) throws IOException {
        StringBuffer sBuffer = new StringBuffer();
        DataInputStream dataIO = new DataInputStream(is);
        String strLine = null;

        while ((strLine = dataIO.readLine()) != null) {
            sBuffer.append(strLine + "\n");
        }

        dataIO.close();
        is.close();

        return sBuffer.toString();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
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
