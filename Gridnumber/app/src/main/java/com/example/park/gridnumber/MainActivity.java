package com.example.park.gridnumber;

        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Button7;
    Button Button8;
    Button Button9;
    Button Button0;
    Button Button_plus;
    Button Button_sub;
    Button Button_mul;
    Button Button_div;
    Button Button_clear;
    Button Button_equal;

    String num_1="";
    String num_2="";

    String input="";
    char c;


    EditText mEdit;
    TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button1 = (Button)findViewById(R.id.button1);
        Button2 = (Button)findViewById(R.id.button2);
        Button3 = (Button)findViewById(R.id.button3);
        Button4 = (Button)findViewById(R.id.button4);
        Button5 = (Button)findViewById(R.id.button5);
        Button6 = (Button)findViewById(R.id.button6);
        Button7 = (Button)findViewById(R.id.button7);
        Button8 = (Button)findViewById(R.id.button8);
        Button9 = (Button)findViewById(R.id.button9);
        Button0 = (Button)findViewById(R.id.button0);
        Button_plus = (Button)findViewById(R.id.button_plus);
        Button_sub = (Button)findViewById(R.id.button_sub);
        Button_mul = (Button)findViewById(R.id.button_mul);
        Button_div = (Button)findViewById(R.id.button_div);
        Button_equal = (Button)findViewById(R.id.button_equal);
        Button_clear = (Button)findViewById(R.id.button_clear);

        mEdit   = (EditText)findViewById(R.id.main_input);
        mText = (TextView)findViewById(R.id.textView);

        Button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "1";

                mEdit.setText(input);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "2";

                mEdit.setText(input);
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                input += "3";

                mEdit.setText(input);
            }
        });
        Button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input +="4";

                mEdit.setText(input);
            }
        });
        Button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "5";

                mEdit.setText(input);
            }
        });
        Button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "6";

                mEdit.setText(input);
            }
        });
        Button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input +="7";

                mEdit.setText(input);
            }
        });
        Button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "8";

                mEdit.setText(input);
            }
        });
        Button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "9";

                mEdit.setText(input);
            }
        });
        Button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "0";

                mEdit.setText(input);
            }
        });
        Button_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "+";

                mEdit.setText(input);
            }
        });
        Button_sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "-";

                mEdit.setText(input);
            }
        });
        Button_mul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "*";

                mEdit.setText(input);
            }
        });
        Button_div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                input += "/";

                mEdit.setText(input);
            }
        });
        Button_equal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                int cnt = 0;
                int i;


                for(i = 0 ; i < input.length() ; i++)
                {
                    if(input.charAt(i) == '+')
                    {
                        c = '+';
                        cnt = 1;
                    }
                          else if(input.charAt(i) == '-')
                         {
                             c = '-';
                             cnt = 1;
                         }
                            else if(input.charAt(i) == '*')
                             {
                                 c = '*';
                                 cnt = 1;
                             }
                                else if(input.charAt(i) == '/')
                                {
                                    c = '/';
                                    cnt = 1;
                                }
                                    else
                                    {
                                        if(cnt == 0)
                                            num_1 += input.charAt(i);
                                        else
                                            num_2 += input.charAt(i);
                                    }
                }


                int num1 = Integer.parseInt(num_1);
                int num2 = Integer.parseInt(num_2);


                if(c == '+')
                    mText.setText(""+(num1+num2));
                else if(c == '-')
                         mText.setText(""+(num1-num2));
                else if(c == '*')
                    mText.setText(""+(num1*num2));
                else if(c == '/')
                    mText.setText(""+(num1/num2));

                mEdit.setText("");
                num_1 ="";
                num_2 ="";
                input = "";


            }
        });
        Button_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                mEdit.setText("");
                mText.setText("");
                input = "";

                mEdit.setText(input);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClick1(View view)
    {
        String s2=getResources().getString(R.string.app_name);
        Toast.makeText(this,mEdit.getText().toString(), Toast.LENGTH_LONG).show();
    }
}
