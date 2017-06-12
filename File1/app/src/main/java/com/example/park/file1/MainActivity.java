package com.example.park.file1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg = (EditText) findViewById(R.id.editText1);
    }

    public void WriteBtn(View v)
    {
        try
        {
            FileOutputStream fileout = openFileOutput("mytextfile.txt",MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();;
        }
    }

    public void ReadBtn(View v){
        try{
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while((charRead=InputRead.read(inputBuffer))>0){
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s+=readstring;
            }
            InputRead.close();
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
