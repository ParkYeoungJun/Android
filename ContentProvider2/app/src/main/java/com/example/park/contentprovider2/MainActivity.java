package com.example.park.contentprovider2;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView output;
    String out;
    Cursor num,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView)findViewById(R.id.output);
        out = refreshData();
        output.setText(out);
    }

    private String refreshData()
    {
        String emailAddress = "";
        out = "";
        try{
            ContentResolver cr = getBaseContext().getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            int id = cur.getColumnIndex(ContactsContract.Contacts._ID);
            cur.moveToFirst();

            if(cur.getCount() > 0)
            {
                Log.i("Content provider", "Reading contact emails");

                while(!cur.isAfterLast())
                {
                    String tel = "";

                    String contactId = cur.getString(id);

                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    num = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+contactId, null,null);

                    num.moveToFirst();

                    while(!num.isAfterLast())
                    {
                        tel = num.getString(num.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        num.moveToNext();
                    }

                    num.close();

                    email = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Email.CONTACT_ID
                                    +" = " + contactId,null,null);

                    email.moveToFirst();
                    while(!email.isAfterLast())
                    {
                        emailAddress = email.getString(email.
                                getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        email.moveToNext();
                    }

                    email.close();


                    out += "Name : "+name+"\nPhone number : "+tel+"\nEmail : "+emailAddress+"\n\n";
                    name = "";
                    tel = "";
                    emailAddress = "";
                    cur.moveToNext();
                }
            }
            else
            {
                out += "Data not found.";
            }
            cur.close();
        } catch (Exception e)
        {
            out += "Exception : "+e+" ";
        }
        return out;
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
