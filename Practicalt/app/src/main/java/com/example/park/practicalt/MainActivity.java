package com.example.park.practicalt;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    Button mButton;
    EditText mEdit;
    TextView mText;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.start_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEdit   = (EditText)findViewById(R.id.main_input);
                mText = (TextView)findViewById(R.id.textView1);
                str = mEdit.getText().toString();
                int i = 0;
                i = Integer.parseInt(str);
                mText.setTextColor(Color.RED);

                if(i > 50)
                {
                    mText.setText("PASS");
                }
                else
                {
                    mText.setText("False");
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
    public void onClick1(View view)
    {
        String s2=getResources().getString(R.string.app_name);
        Toast.makeText(this,mEdit.getText().toString(), Toast.LENGTH_LONG).show();
    }
}
