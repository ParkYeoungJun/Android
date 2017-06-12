package com.example.park.notification1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final int MY_NOTIFICATION_ID=1;
    private NotificationManager notificationManager;
    private Notification myNotification;
    private final String myGoogle = "http://www.google.com/";
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSend = (Button) findViewById(R.id.send);
        buttonSend.setOnClickListener(new Button.OnClickListener(){
            @SuppressWarnings("deprecation")
        @Override
        public void onClick(View arg0){
                notificationManager =
                        (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                builder = new NotificationCompat.Builder(MainActivity.this);

                String notificationTitle = "Notification from Google";
                String notificationText = "http://www.google.com/";
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myGoogle));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, myIntent,
                        0);

                builder.setSmallIcon(R.drawable.icon);
                builder.setContentTitle(notificationTitle);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                builder.setAutoCancel(true);
                builder.setContentIntent(pendingIntent);
                builder.setContentText(notificationText);
                notificationManager.notify(MY_NOTIFICATION_ID,builder.build());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
