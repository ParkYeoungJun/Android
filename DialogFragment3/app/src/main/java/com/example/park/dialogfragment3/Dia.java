package com.example.park.dialogfragment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Park on 2015-10-19.
 */
public class Dia extends DialogFragment{
    String str = "";
    EditText text;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

                a.setView(view);
                a.setTitle("What is your name?");

                text = (EditText)view.findViewById(R.id.edit);

                a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Hello, "+text.getText(), Toast.LENGTH_LONG).show();
                    }
                });

                a.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                    }
                });

        return a.create();
    }

}
