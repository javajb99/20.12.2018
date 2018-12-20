package com.example.itayh.bike;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private CheckBox vibrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        vibrate = (CheckBox)findViewById(R.id.vibrate_checkbox);
        MySettings settings = ((OnYourBike2)getApplication()).getSettings();
        vibrate.setChecked(settings.isVibrateOn(this));
        //vibrate.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        setupActionBar();
        return true;
    }

    private void setupActionBar()
    {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toaster.Toaster toast = new Toaster.Toaster(getApplicationContext());
        toast.make(R.string.vibrate_on);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Context context = getApplicationContext();
        //CharSequence text = Integer.toString(item.getItemId());
        CharSequence text = item.getTitle();
        int duration = Toast.LENGTH_SHORT;

        Toast toast2 = Toast.makeText(context, text, duration);
        toast2.show();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        //16908332
        if (id == android.R.id.home) {
            toast.make(R.string.vibrate_on);
            gotoHome();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        MySettings settings = ((OnYourBike2)getApplication()).getSettings();
        settings.SetVibrate(vibrate.isChecked(), this);
        ((OnYourBike2)getApplication()).setSettings(settings);
    }

    public void vibrateChanged(View view)
    {
        Toaster.Toaster toast = new Toaster.Toaster(getApplicationContext());
        if (vibrate.isChecked())
        {
            toast.make(R.string.vibrate_on);
        }
        else
        {
            toast.make(R.string.vibrate_off);
        }

    }

    private void   gotoHome()
    {
        Intent timer = new Intent(getApplicationContext(), OnYourBike.class);
        timer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(timer);
    }

    public void goBack(View view)
    {
        finish();
    }
}
