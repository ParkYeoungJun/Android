package com.example.park.intent5;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button dial;
    Button call;
    Button browser;
    Button photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dial = (Button) findViewById(R.id.dial);

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dia = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:01071374971"));
                startActivity(dia);
            }
        });

        browser = (Button) findViewById(R.id.browser);

        browser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent bro = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(bro);
            }
        });

        call = (Button) findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent bro = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:01083437770"));
                startActivity(bro);
            }
        });

        photo = (Button) findViewById(R.id.photo);

        photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE,null);
                startActivityForResult(pho, 2);
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
