package com.example.park.business_card;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Park on 2015-12-09.
 */

// Two touch Back key
public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "If you want Close, Press one More time", Toast.LENGTH_SHORT);
        toast.show();
    }

}
