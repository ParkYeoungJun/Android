package com.example.park.fragment2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private TextView mText;
   private String[] days;
    private ImageView image;
    private BitmapDrawable bit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainListView = (ListView) findViewById(R.id.mainListView);
        mText = (TextView) findViewById(R.id.textview1);
        image = (ImageView) findViewById(R.id.image);

        // Create and populate a List of days.
       days = new String[]{"Yap", "Chee", "Een", "Park",
                "Young", "Jun", "조교님","good","bad","haha","kkk",":D","XD",":X","하하"};
        ArrayList<String> daylist = new ArrayList<String>();
        daylist.addAll(Arrays.asList(days));

        // Create ArrayAdapter using the day list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, daylist);

        // Add more Days. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        mainListView.setAdapter(listAdapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mText.setText(days[position]);

                if(position == 0) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image);
                    image.setImageDrawable(bit);
                }else if(position == 1) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image1);
                    image.setImageDrawable(bit);
                }
                else if(position == 2) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image2);
                    image.setImageDrawable(bit);
                }
                else if(position == 3) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image3);
                    image.setImageDrawable(bit);
                }
                else if(position == 4) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image4);
                    image.setImageDrawable(bit);
                }
                else if(position == 5) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image5);
                    image.setImageDrawable(bit);
                }
                else if(position == 6) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image6);
                    image.setImageDrawable(bit);
                }
                else if(position == 7) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image7);
                    image.setImageDrawable(bit);
                }
                else if(position == 8) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image8);
                    image.setImageDrawable(bit);
                }
                else if(position == 9) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image9);
                    image.setImageDrawable(bit);
                }
                else if(position == 10) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image);
                    image.setImageDrawable(bit);
                }else if(position == 11) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image1);
                    image.setImageDrawable(bit);
                }
                else if(position == 12) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image2);
                    image.setImageDrawable(bit);
                }
                else if(position == 13) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image3);
                    image.setImageDrawable(bit);
                }
                else if(position == 14) {
                    bit = (BitmapDrawable) getResources().getDrawable(R.drawable.image4);
                    image.setImageDrawable(bit);
                }

            }
        });


    }

    public void selectFrag(View view)
    {
//        if(view == findViewById(R.id.button1))
//        {
//            mText.setText("Android is good");
//        }else if(view == findViewById(R.id.button2))
//        {
//            mText.setText("Java is good");
//        }else
//            mText.setText("Script is bad");

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.commit();
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
