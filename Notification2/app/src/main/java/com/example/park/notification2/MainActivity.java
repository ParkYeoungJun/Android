package com.example.park.notification2;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText title,subject,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button1);
        title = (EditText)findViewById(R.id.title);
        subject = (EditText)findViewById(R.id.subject);
        content = (EditText)findViewById(R.id.content);

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String t,s,c;

                t = title.getText().toString();
                s = subject.getText().toString();
                c = content.getText().toString();

                NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);

                builder.setSmallIcon(R.drawable.icon);
//                builder.setSubText("");
                builder.setContentTitle(s);
                builder.setContentText(c);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                builder.setAutoCancel(true);

                nm.notify(1,builder.build());
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
