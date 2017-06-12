package com.example.park.fragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Park on 2015-10-12.
 */
public class FragmentOne extends Fragment{

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
}
