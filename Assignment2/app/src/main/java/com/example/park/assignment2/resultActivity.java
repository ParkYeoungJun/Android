package com.example.park.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Park on 2015-11-19.
 */
public class resultActivity extends Activity{

    TextView text;
    Button reset;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultfile);

        text = (TextView)findViewById(R.id.resultfile);
        reset = (Button)findViewById(R.id.reset);

        try{

            FileInputStream fileIn = openFileInput("AssignmentResult.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[100];
            String s = "";
            int charRead;
            while((charRead=InputRead.read(inputBuffer))>0){
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s+=readstring;
            }
            text.setText(s);
            InputRead.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    FileOutputStream fileout = openFileOutput("AssignmentResult.txt",MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write("");
                    outputWriter.close();

                    text.setText("");

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
