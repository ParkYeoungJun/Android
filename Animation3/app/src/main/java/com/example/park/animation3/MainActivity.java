package com.example.park.animation3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.zoomInOut:
                ImageView image = (ImageView)findViewById(R.id.imageView1);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zooming);
                image.startAnimation(animation);
                return true;
            case R.id.rotate360:
                ImageView image1 = (ImageView)findViewById(R.id.imageView1);
                Animation animagion1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
                image1.startAnimation(animagion1);
                return true;
            case R.id.fadeInOut:
                ImageView image2 = (ImageView)findViewById(R.id.imageView1);
                Animation animagion2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                image2.startAnimation(animagion2);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
