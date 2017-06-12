package com.example.park.business_card;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by seungjin on 2015-12-08.
 */
public class Capture extends Activity {

    LinearLayout ll;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture);
        ll = (LinearLayout)findViewById(R.id.capturelayout);
        ll.buildDrawingCache();
        Bitmap cv = ll.getDrawingCache();
        FileOutputStream fout;
        try
        {
            fout = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/capture.jpeg");
            cv.compress(Bitmap.CompressFormat.JPEG, 100, fout);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
   /*     finally
        {
            finish();
        }*/
    }
}