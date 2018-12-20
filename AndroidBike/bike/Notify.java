package com.example.itayh.bike;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import com.example.itayh.bike.R;


/**
 * Created by Itayh on 9/22/2015.
 */
public class Notify {
    private static String CLASS_NAME;
    private final NotificationManager manager;
    private final Context context;
    public int smallIcon = R.drawable.china;
    private static final int MESSAGE_ID = 1;
    public Notify(Activity activity)
    {
        CLASS_NAME = getClass().getName();
        manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        context = activity.getApplicationContext();
    }
    private Notification create(String title, String message, long when)
    {
        //Notification notification = new Notification.Builder(context).setContentTitle(title).setContentText(message).setWhen(when).setSmallIcon(smallIcon).build();

        Notification notification = new Notification.Builder(context).setContentTitle(title).setContentText(message).setWhen(when).setSmallIcon(smallIcon).build();

        return notification;
    }

    public void notify(String title, String message)
    {
        Notification not = create(title, message, System.currentTimeMillis());
        manager.notify(MESSAGE_ID, not);
    }
}
