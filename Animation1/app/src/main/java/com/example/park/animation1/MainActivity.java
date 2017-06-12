package com.example.park.animation1;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {

    private TextSwitcher mSwitcher;
    Button btnAnimate;
    String textToShow[] = {"UI and Practice", "Java", "MFC", "Soongsil University"};
    int messageCount = textToShow.length;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnimate = (Button)findViewById(R.id.button1);
        mSwitcher = (TextSwitcher)findViewById(R.id.textSwitcher1);

        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(MainActivity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER);
                myText.setTextSize(30);
                myText.setTextColor(Color.RED);
                return myText;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        btnAnimate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentIndex++;
                if(currentIndex == messageCount)
                    currentIndex=0;
                mSwitcher.setText(textToShow[currentIndex]);
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
