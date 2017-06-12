package com.example.park.database1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends ListActivity {

    private StudentOperations studentDBoperation;

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

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void addUser(View view){
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

        EditText text = (EditText) findViewById(R.id.editText1);
        Student stud = studentDBoperation.addStudent(text.getText().toString());

        adapter.add(stud);
    }

    public void deleteFirstUser(View view){
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        Student stud = null;

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
        super.onResume();
    }

    @Override
    protected  void onPause(){
        studentDBoperation.close();
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
}
