package com.example.park.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Park on 2015-10-19.
 */
public class DFragment extends DialogFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.dialogfragment,container,false);
        getDialog().setTitle("DialogFragment Tutorial");



        return rootView;
    }
}
