package com.example.park.business_card;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Park on 2015-12-08.
 */

// Start Screen

public class Start extends Activity {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg)
            {
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0,4000);
    }
}
