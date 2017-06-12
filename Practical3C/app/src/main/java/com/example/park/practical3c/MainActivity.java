package com.example.park.practical3c;

//import com.example.practical3b.R;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    EditText mEdit;
    TextView mText;
    Button mButton;
    Float result;
    float f;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup group1 = (RadioGroup) findViewById(R.id.orientation);
        mButton = (Button)findViewById(R.id.show_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEdit = (EditText) findViewById(R.id.input1);
                mText = (TextView) findViewById(R.id.textView1);
                String str;
                str = mEdit.getText().toString();
                f = Float.parseFloat(str);
                if(i==0){
                    result = convertFahrenheitToCelsius(f);
                }
                else if (i==1){
                    result = convertCelsiusToFahrenheit(f);
                }

                //받아서 스트링에 넣고 인트형으로 바꿔서 넣고
                //라디오 찍힌거에 따라서
                //알맞은 함수 호출해서 출력
                mText.setText(result.toString());
                //mText.setText(mEdit.getText().toString());

            }
        });

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Celsius:
                        i = 0;
                        break;
                    case R.id.Fahrenheit:
                        i = 1;
                        break;
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }

}
