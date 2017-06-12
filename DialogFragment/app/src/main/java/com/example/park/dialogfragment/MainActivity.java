package com.example.park.dialogfragment;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button dfragbutton;
    Button alertdfragbutton;
    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dfragbutton = (Button) findViewById(R.id.dfragbutton);
        alertdfragbutton = (Button) findViewById(R.id.alertdfragbutton);

        dfragbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                DFragment dFragment = new DFragment();
                dFragment.show(fm,"Dialog Fragment");
            }
        });

        alertdfragbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDFragment alertdFragment = new AlertDFragment();

                alertdFragment.show(fm,"Alert Dialog Fragment");
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
