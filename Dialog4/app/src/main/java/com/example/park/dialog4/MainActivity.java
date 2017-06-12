package com.example.park.dialog4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    int mHour = 15;
    int mMinute = 15;

    /**
     * This handles the message send from TimerDialogFragment on setting Time
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message m) {
            /** Creating a bundle object to pass currently set Time to the fragment */
            Bundle b = m.getData();

            /** Getting the Hour of day from bundle */
            mHour = b.getInt("set_hour");

            /** Getting the Minute of the hour from bundle */
            mMinute = b.getInt("set_minute");

            /** Displaying a short time message containing time set by Timer dialog fragment */
            Toast.makeText(getBaseContext(), b.getString("set_time"), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Click Event Handler for button */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** Creating a bundle object to pass currently set time to the fragment */
                Bundle b = new Bundle();

                /** Adding currently set hour to bundle object */
                b.putInt("set_hour", mHour);

                /** Adding currently set minute to bundle object */
                b.putInt("set_minute", mMinute);

                /** Instantiating TimePickerDialogFragment */
                TimerDialogFragment timePicker = new TimerDialogFragment(mHandler);

                /** Setting the bundle object on timer fragment */
                timePicker.setArguments(b);

                /** Getting fragment manger for this activity */
                FragmentManager fm = getFragmentManager();

                /** Starting a fragment transaction */
                FragmentTransaction ft = fm.beginTransaction();

                /** Adding the fragment object to the fragment transaction */
                ft.add(timePicker, "time_picker");

                /** Opening the TimePicker fragment */
                ft.commit();
            }
        };

        /** Getting an instance of Set button */
        Button btnSet = (Button) findViewById(R.id.button);

        /** Setting click event listener for the button */
        btnSet.setOnClickListener(listener);
    }
}
