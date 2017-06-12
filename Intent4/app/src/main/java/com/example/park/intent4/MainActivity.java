package com.example.park.intent4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    Button button;
    EditText name;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);

        button.setOnClickListener(new ButtonClickHandler());

        pass.setInputType(InputType.TYPE_CLASS_NUMBER);
        PasswordTransformationMethod passw = new PasswordTransformationMethod();
        pass.setTransformationMethod(passw);
    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        public void onClick(View view)
        {
            if(name.getText().toString().equals("Android")
                    && pass.getText().toString().equals("123456")) {
                Intent inte = new Intent(MainActivity.this,
                        Good.class);

                startActivity(inte);
            }
            else
            {
                Intent inte = new Intent(MainActivity.this,
                        Failed.class);

                startActivity(inte);
            }
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
