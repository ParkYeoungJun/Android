package com.example.park.fragment3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button Button1;
    Button Button2;
    Button Button3;

    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button1 = (Button)findViewById(R.id.button1);
//        Button2 = (Button)findViewById(R.id.button2);
//        Button3 = (Button)findViewById(R.id.button3);

        mText = (TextView)findViewById(R.id.textView1);


//        Button1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                mText.setText("Android is good");
//            }
//        });
//        Button2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                mText.setText("Java is good");
//            }
//        });
//        Button3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                mText.setText("No good");
//            }
//        });
    }

    public void selectFrag(View view)
    {
        if(view == findViewById(R.id.button1))
        {
            mText.setText("Android is good");
        }else if(view == findViewById(R.id.button2))
        {
            mText.setText("Java is good");
        }else
            mText.setText("Script is bad");

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.commit();
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
