package com.example.park.pendingintent;

/**
 * Created by Park on 2015-11-02.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by seungjin on 2015-11-01.
 */
public class Activiry2 extends Activity {

    TextView v1;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        v1 = (TextView)findViewById(R.id.textView1);
        v1.setTextSize(30);
        v1.setText("This is example of Pending Intent");
    }
}

