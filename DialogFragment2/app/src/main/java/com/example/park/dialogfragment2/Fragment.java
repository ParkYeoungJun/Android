package com.example.park.dialogfragment2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Park on 2015-10-19.
 */
public class Fragment extends DialogFragment{

    @Override
        public Dialog onCreateDialog( Bundle savedInstanceState)
        {
            AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            a.setTitle("What is Android");
            a.setView(inflater.inflate(R.layout.dialog,null));
            return a.create();
        }


}
