package com.example.itayh.bike;

import android.app.Application;

public class OnYourBike2 extends Application {
    protected MySettings settings;
    public MySettings getSettings()
    {
        if (settings == null)
        {
            settings = new MySettings();
        }
        return settings;
    }
    public void setSettings(MySettings settings)
    {
        this.settings = settings;
    }
}
