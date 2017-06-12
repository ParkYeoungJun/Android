package com.example.park.pick;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    Button button,done;

    ArrayList<String> strArray;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText)findViewById(R.id.text);
        button = (Button)findViewById(R.id.button);
        done =  (Button)findViewById(R.id.done);

        size = 0;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str;

                str = edit.getText().toString();

                if(str.length() != 0) {
                    strArray.add(str);
                    size++;
                }
                else
                    Toast.makeText(MainActivity.this, "입력해라 ㅡㅡ", Toast.LENGTH_SHORT).show();
            }
        });

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int rand;

                if(size != 0) {
                    rand = (int) (Math.random() * size);

                    AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);

                    dia.setTitle("이거나 처묵어\n "+strArray.get(rand));

                    dia.show();

                }
                else
                    Toast.makeText(MainActivity.this, "입력해라 ㅡㅡ", Toast.LENGTH_SHORT).show();

            }
        });


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
