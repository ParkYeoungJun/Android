package com.example.park.labtest2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView num;

    Button plus,minus;

    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = (TextView)findViewById(R.id.num);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);

        k = 0;

        num.setTextColor(Color.RED);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i;

                i = Integer.parseInt(num.getText().toString());

                ++i;

                num.setText("" + i);

                if(k == 0) {
                    num.setTextColor(Color.BLUE);
                    k++;
                }else if(k == 1) {
                    num.setTextColor(Color.RED);
                    k++;
                }else if(k == 2) {
                    num.setTextColor(Color.GRAY);
                    k = 0;
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                int i;

                i = Integer.parseInt(num.getText().toString());

                --i;

                num.setText(""+i);

                if(k == 0) {
                    num.setTextColor(Color.RED);
                    k++;
                }else if(k == 1) {
                    num.setTextColor(Color.BLUE);
                    k++;
                }else if(k == 2) {
                    num.setTextColor(Color.GRAY);
                    k = 0;
                }
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
