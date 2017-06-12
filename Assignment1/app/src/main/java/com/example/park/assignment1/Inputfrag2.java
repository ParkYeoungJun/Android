package com.example.park.assignment1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Park on 2015-10-24.
 */
public class Inputfrag2 extends DialogFragment {
    EditText edit;
    Button ok;
    SoundPool pool;
    int congrat;


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState)
    {
        AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.inputdialog2,null);

        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        congrat = pool.load(getActivity(),R.raw.victory,1);

        edit = (EditText)view.findViewById(R.id.dialogtext);

        ok = (Button)view.findViewById(R.id.yes);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder win = new AlertDialog.Builder(getActivity());

                if(edit.getText().toString().length() != 0) {

                    win.setTitle("Winner is ~~~~");

                    win.setMessage("                "+ edit.getText().toString() + " !!!");

                    win.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    pool.play(congrat, 1, 1, 0, 0, 1);

                    win.show();
                }
                else
                    Toast.makeText(getActivity(), "Please Insert Player2's Name", Toast.LENGTH_LONG).show();

            }
        });

        a.setView(view);

        return a.create();
    }

}

