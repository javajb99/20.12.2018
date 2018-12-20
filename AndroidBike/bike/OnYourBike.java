package com.example.itayh.bike;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class OnYourBike extends AppCompatActivity {

    class UpdateTimer implements Runnable{
        public void run()
        {
            SetTimeDisplay();
            if (handler != null)
            {
                handler.postDelayed(this, UPDATE_EVERY);
              //  if (byc.getVisibility() == View.VISIBLE)
              //      byc.setVisibility(View.INVISIBLE);
              //  else
               //     byc.setVisibility(View.VISIBLE);
            }
        }
    }

    public Notify notify = null;
    public ImageView byc = null;
    protected Handler handler;
    protected UpdateTimer updateTimer;
    protected TextView counter;
    protected Button start;
    protected Button stop;
    protected Boolean timerRunning;
    protected long startedAt;
    protected long lastStopped;
    static long UPDATE_EVERY = 200;
    protected Vibrator vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build());
        setContentView(R.layout.activity_on_your_bike);
        counter = (TextView)findViewById(R.id.timer);
        start = (Button)findViewById(R.id.start_button);
        stop = (Button)findViewById(R.id.stop_button);
        //byc = (ImageView)findViewById(R.id.bycImg);
        stop.setEnabled(false);
        //enableButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_on_your_bike, menu);
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
            Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
            startActivity(settingsIntent);
            return true;
        }
        else if (id == R.id.action_country) {
            Intent countryIntent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(countryIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (this.timerRunning != null)
        {
        if (this.timerRunning) {
           this.handler = new Handler();
            this.updateTimer = new UpdateTimer();
            this.handler.postDelayed(updateTimer, UPDATE_EVERY);
        }
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (this.timerRunning != null)
        {
        if (timerRunning)
        {
            handler.removeCallbacks(updateTimer);
            updateTimer = null;
            handler = null;
        }}
    }
    @Override
        public void onResume() {
        super.onResume();
        if (this.timerRunning != null) {
            enableButtons();
            SetTimeDisplay();
        }
    }


    public void clickedStart(View view)
    {
        counter.setText("Start");



        startedAt = System.currentTimeMillis();
        timerRunning = true;
        enableButtons();
        SetTimeDisplay();
        handler = new Handler();
        updateTimer = new UpdateTimer();
        handler.postDelayed(updateTimer, UPDATE_EVERY);

    }

    public void clickedStop(View view)
    {
        counter.setText("Stop");
        timerRunning = false;
        lastStopped = System.currentTimeMillis();
        enableButtons();
        SetTimeDisplay();
        handler.removeCallbacks(updateTimer);
        handler = null;
        if (notify == null)
            notify = new Notify(this);
        notify.notify("Bike Timer", "Resting...");
    }
    public void clickedSettings(View view)
    {
        Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
        startActivity(settingsIntent);
    }
    protected void enableButtons()
    {
        start.setEnabled(!timerRunning);
        stop.setEnabled(timerRunning);
    }

    protected void SetTimeDisplay()
    {
        String disaply;
        long timeNow;
        long diff;
        long seconds;
        long minutes;
        long hours;
        if (timerRunning) {
            timeNow = System.currentTimeMillis();
        }
        else
        {
            timeNow = lastStopped;
        }

        diff = timeNow - startedAt;
        if (diff < 0) { diff = 0;    }
        seconds = diff / 1000;
        minutes = seconds / 60;
        hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        disaply = String.format("%d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        counter.setText(disaply);
        if (notify == null)
            notify = new Notify(this);
        notify.notify("Bike Timer", disaply);
    }

}

