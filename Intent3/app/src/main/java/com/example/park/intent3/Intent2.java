package com.example.park.intent3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Park on 2015-10-26.
 */
public class Intent2 extends Activity{
    Button button;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new Intent2.ButtonClickHandler());

    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        public void onClick(View view)
        {
            Intent inte = new Intent(Intent2.this,
                    MainActivity.class);

            startActivity(inte);
        }
    }
}
