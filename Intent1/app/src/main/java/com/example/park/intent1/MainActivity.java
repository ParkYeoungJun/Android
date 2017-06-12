package com.example.park.intent1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText nameEditCtrl;
    Button btnCtlr;
    String name;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditCtrl = (EditText) findViewById(R.id.editText1);
        btnCtlr = (Button) findViewById(R.id.button1);
        btnCtlr.setOnClickListener(new ButtonClickHandler());
    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        public void onClick(View view)
        {
            if(nameEditCtrl != null && nameEditCtrl.getText().length() != 0)
            {
                name = nameEditCtrl.getText().toString();
            }
            else {
                name = "Guest";
            }

            Intent intObj = new Intent(MainActivity.this,
                    GreetingActivity.class);
            intObj.putExtra("USERNAME",name);
            startActivity(intObj);
        }
    }
}
