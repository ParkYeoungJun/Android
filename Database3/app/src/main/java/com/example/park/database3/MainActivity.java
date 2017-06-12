package com.example.park.database3;

import android.app.Dialog;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends ListActivity {

    private StudentOperations studentDBoperation;
    private int kcount=0,ncount=0,jcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDBoperation = new StudentOperations(this);
        try {
            studentDBoperation.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List values = studentDBoperation.getAllStudents();

//        int[] a = studentDBoperation.getcount();
//
//        kcount = a[0];
//        ncount = a[1];
//        jcount = a[2];

        read();
        save();

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void addUser(View view){
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

        EditText text1 = (EditText) findViewById(R.id.editText1);

        CheckBox korea = (CheckBox) findViewById(R.id.korea);
        CheckBox newzealand = (CheckBox) findViewById(R.id.newzealand);
        CheckBox japen = (CheckBox) findViewById(R.id.japen);

        String str ="Add \n";

        if(korea.isChecked())
        {
            kcount += Integer.parseInt(text1.getText().toString());

            str += "Korea : " + Integer.parseInt(text1.getText().toString());
        }

        if(newzealand.isChecked())
        {
            ncount += Integer.parseInt(text1.getText().toString());

            str += "\nNew Zealand : " + Integer.parseInt(text1.getText().toString());
        }

        if(japen.isChecked())
        {
            jcount += Integer.parseInt(text1.getText().toString());

            str += "\nJapen : " + Integer.parseInt(text1.getText().toString());
        }

        Student stud = studentDBoperation.addStudent(str, kcount, ncount, jcount);

        save();

        adapter.add(stud);
    }

    public void deleteFirstUser(View view){
//        int[] a = studentDBoperation.getcount();
//
//        kcount = a[0];
//        ncount = a[1];
//        jcount = a[2];

        Toast.makeText(this, "\nKorea : "+kcount+"\nNew Zealand : "+ncount+"\nJapen : "+jcount+"\n",
                Toast.LENGTH_SHORT).show();
    }

    public void delete(View view){
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        Student stud = null;

        int[] a = studentDBoperation.getcount();

        kcount = a[0];
        ncount = a[1];
        jcount = a[2];

        save();

        if(getListAdapter().getCount() > 0 ){
            stud = (Student) getListAdapter().getItem(0);
            studentDBoperation.deleteStudent(stud);
            adapter.remove(stud);
        }
    }

    @Override
    protected void onResume(){
        try {
            studentDBoperation.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        read();
        save();
//        int[] a = studentDBoperation.getcount();
//
//        kcount = a[0];
//        ncount = a[1];
//        jcount = a[2];

        super.onResume();

    }

    @Override
    protected  void onPause(){
        studentDBoperation.close();
        read();
        save();
        super.onPause();
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

    public void save()
    {
        try {
            FileOutputStream fileout = openFileOutput("Result.txt", MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fileout);

            writer.write(kcount+" "+ncount+" "+jcount+"c");

            writer.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            FileInputStream fileIn = openFileInput("Result.txt");

            InputStreamReader reader = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];

            int charRead;
            String str = "";

            int count = 0;

            while ((charRead = reader.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                str += readstring;

            }

            String a = "";
            int c = 0;

            while(count <= str.length())
            {
                a += ""+str.charAt(count);
                count++;
                if(str.charAt(count) == ' ' && c == 0)
                {
                    kcount = Integer.parseInt(a);
                    count++;
                    c++;
                    a = "";
                }
                else if(str.charAt(count) == ' ' && c == 1)
                {
                    ncount = Integer.parseInt(a);
                    count++;
                    c++;
                    a = "";
                }
                else if(str.charAt(count) == 'c' && c == 2)
                {
                    jcount = Integer.parseInt(a);
                    count++;
                    c++;
                    a = "";
                }
            }
            reader.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
