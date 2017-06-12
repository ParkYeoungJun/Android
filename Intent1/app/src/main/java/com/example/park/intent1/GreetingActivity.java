package com.example.park.intent1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Park on 2015-10-26.
 */
public class GreetingActivity extends Activity{

    TextView greetMsg;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting);
        greetMsg = (TextView)findViewById(R.id.textView1);
        Intent intename = getIntent();
        String uname = (String) intename.getSerializableExtra("USERNAME");
        greetMsg.setText("Welcome "+ uname);
    }
}
