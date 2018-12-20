package com.example.itayh.bike;

/**
 * Created by Itayh on 9/16/2015.
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.Editable;

/**
 * Created by Itayh on 9/16/2015.
 */
public class MySettings {
    private static String VIBRATE = "vibrate";
    private static String CLASS_NAME;
    protected boolean vibrateOn;
    public boolean isVibrateOn(Activity activity)
    {
        SharedPreferences preferences = activity.getPreferences(Activity.MODE_PRIVATE);
        if (preferences.contains(VIBRATE))
        {
            vibrateOn = preferences.getBoolean(VIBRATE, false);
        }
        return vibrateOn;
    }
    public void SetVibrate(boolean vibrate, Activity activity)
    {
        vibrateOn = vibrate;
        SharedPreferences preferences = activity.getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(VIBRATE, vibrate);
        editor.apply();
    }
    public MySettings()
    {
        CLASS_NAME = getClass().getName();
    }
}

