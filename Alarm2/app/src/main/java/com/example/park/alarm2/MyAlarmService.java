package com.example.park.alarm2;

/**
 * Created by Park on 2015-11-02.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MyAlarmService extends Service {
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    @Override
    public void onCreate() {

//        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        builder = new NotificationCompat.Builder(MyAlarmService.this);
//
//        builder.setContentTitle("Alarm!!!");
//        builder.setSmallIcon(R.drawable.icon);
//        builder.setDefaults(Notification.DEFAULT_SOUND);
//        builder.setAutoCancel(true);
//        builder.setContentText("띠리리리링");
//
//        notificationManager.notify(1, builder.build());

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Intent ent = new Intent();
        ent.setClass(this,MainActivity.class);
        PendingIntent pend = PendingIntent.getActivity(this, 0 , ent, 0);

        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        builder = new NotificationCompat.Builder(getApplicationContext());

        builder.setContentTitle("Alarm!!!");
        builder.setSmallIcon(R.drawable.icon);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        builder.setContentText("띠리리리링");
        builder.setContentIntent(pend);

        notificationManager.notify(1, builder.build());
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
