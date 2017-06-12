package com.example.park.assignment2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Park on 2015-11-18.
 */
public class settingactivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
}
