package com.example.park.intent2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBrowser = (Button) findViewById(R.id.start_browser);
        startBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first_intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(first_intent);
            }
        });
        Button startPhone = (Button) findViewById(R.id.start_phone);
        startPhone.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent second_intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:028287171"));
                startActivity(second_intent);
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
