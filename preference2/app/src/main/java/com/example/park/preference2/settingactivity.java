package com.example.park.preference2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Park on 2015-11-09.
 */
public class settingactivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
}
