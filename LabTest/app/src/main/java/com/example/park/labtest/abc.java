package com.example.park.labtest;

/**
 * Created by Park on 2015-12-07.
 */
public class abc {
}





















































/*
** ==================================================================================================
   fragment

   extends Fragment

   public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);

    }

   Fragment fr;
        if(view == findViewById(R.id.button2))
        {
            fr = new FragmentTwo();
        }else
        {
            fr = new FragmentOne();

        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();


** =================================================================================================
 */



  /*
  ** =================================================================================================
     DialogFragment


        FragmentManager fm = getFragmentManager();

        Fragment frag = new Fragment();
        frag.show(fm, "What is Android?");

        public class Fragment extends DialogFragment{

            @Override
            public Dialog onCreateDialog( Bundle savedInstanceState)
            {
                AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                a.setTitle("What is Android");
                a.setView(inflater.inflate(R.layout.dialog,null));
                return a.create();
            }
        }


  //
      View view = inflater.inflate(R.layout.memo, null);

 ** =================================================================================================
 */


/*
** =================================================================================================

 write

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


   read

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

 ** =================================================================================================
 */

/*
** =================================================================================================
 list view

 <fragment
        android:id="@+id/fragment_place1"
        android:name="com.example.park.fragment2.FragmentOne"
        android:layout_width="100dp"
        android:layout_height="match_parent"
        tools:layout="@layout/activity_main"/>


        public class FragmentOne extends Fragment{

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);

    }
}


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

                                    }
                  });
**
*/

/*
**
intent

 Intent inte = new Intent(Intent2.this,
                    MainActivity.class);

            startActivity(inte);

** =================================================================================================
 */

/*
** =================================================================================================

notification

                builder = new NotificationCompat.Builder(MainActivity.this);

Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myGoogle));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, myIntent,
                        0);

                builder.setSmallIcon(R.drawable.icon);
                builder.setContentTitle(notificationTitle);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                builder.setAutoCancel(true);
                builder.setContentIntent(pendingIntent);
                builder.setContentText(notificationText);
                notificationManager.notify(MY_NOTIFICATION_ID,builder.build());

** =================================================================================================
 */

/*
** =================================================================================================

preference

public class settingactivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
}

// 불러오기
                Intent i = new Intent(getApplicationContext(), settingactivity.class);
                startActivityForResult(i, 1);


                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String settings = "";
       settings = ""+sharedPrefs.getString("edittext","NO");
        TextView textViewSetting = (TextView)findViewById(R.id.textview);
        CheckBox check = (CheckBox)findViewById(R.id.check);
        check.setChecked(sharedPrefs.getBoolean("checked",false));
        textViewSetting.setText(settings);


<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <PreferenceCategory android:title="PREFERENCECATEGORY A">
        <CheckBoxPreference
            android:defaultValue="false"
            android:key="checked"
            android:title="Checkbox_preference"
            android:summary="Click to enable check preference"/>
    </PreferenceCategory>
    <PreferenceCategory android:title="PREFERENCECATEGORY B">
        <EditTextPreference android:title="summary_edittext_preference"
            android:summary="summary_edittext_preference"
            android:key="edittext"/>
    </PreferenceCategory>
</PreferenceScreen>

// string

<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

    <PreferenceCategory android:title="PREFERENCECATEGORY \n LISTPREFERENCE">

        <ListPreference
            android:key="list"
            android:title="Food Preference"
            android:summary="Food Preference Summary"
            android:entries="@array/frequency"
            android:entryValues="@array/frequencyvalue"/>

    </PreferenceCategory>

</PreferenceScreen>


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String settings = "";
        settings = "LIST preference = "+sharedPrefs.getString("list","NO");
        TextView textViewSetting = (TextView)findViewById(R.id.textview);
        textViewSetting.setText(settings);


<?xml version="1.0" encoding="utf-8"?>
<resources>

    <string-array name="frequency">
        <item name="1">KimChi</item>
        <item name="2">BiBimBap</item>
        <item name="3">GamJaTang</item>
        <item name="4">Pasta</item>
    </string-array>
    <string-array name="frequencyvalue">
        <item name="1">1</item>
        <item name="2">2</item>
        <item name="3">3</item>
        <item name="4">4</item>
    </string-array>

</resources>

** =================================================================================================
 */

/*
** =================================================================================================

button


btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), settingactivity.class);
                startActivityForResult(i, 1);
            }
        });


** =================================================================================================
 */

/*
** =================================================================================================

animation

 ImageView image = (ImageView)findViewById(R.id.imageView1);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zooming);
                image.startAnimation(animation);



//  rotate
    <rotate xmlns:android="http://schemas.android.com/apk/res/android"
        android:fromDegrees="0"
        android:toDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="5000"/>

    <rotate xmlns:android="http://schemas.android.com/apk/res/android"
        android:startOffset="5000"
        android:fromDegrees="360"
        android:toDegrees="0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="5000"/>


//  alpha

<alpha
        android:fromAlpha="0"
        android:toAlpha="1"
        android:duration="2000"/>

    <alpha
        android:startOffset="2000"
        android:fromAlpha="1"
        android:toAlpha="0"
        android:duration="2000"/>

 // zooming

     <scale xmlns:android="http://schemas.android.com/apk/res/android"
        android:fromXScale="0.5"
        android:toXScale="2.0"
        android:fromYScale="0.5"
        android:toYScale="2.0"
        android:duration="5000"
        android:pivotX="50%"
        android:pivotY="50%"/>

    <scale xmlns:android="http://schemas.android.com/apk/res/android"
        android:startOffset="5000"
        android:fromXScale="2.0"
        android:toXScale="0.5"
        android:fromYScale="2.0"
        android:toYScale="0.5"
        android:duration="5000"
        android:pivotX="50%"
        android:pivotY="50%"/>

** =================================================================================================
 */

/*
** =================================================================================================
    맨위 setting 버튼

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.zoomInOut:
                ImageView image = (ImageView)findViewById(R.id.imageView1);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zooming);
                image.startAnimation(animation);
                return true;
            case R.id.rotate360:
                ImageView image1 = (ImageView)findViewById(R.id.imageView1);
                Animation animagion1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
                image1.startAnimation(animagion1);
                return true;
            case R.id.fadeInOut:
                ImageView image2 = (ImageView)findViewById(R.id.imageView1);
                Animation animagion2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                image2.startAnimation(animagion2);
                return true;
        }
        return false;
    }

     <item
        android:id="@+id/rotate360"
        android:orderInCategory="100"
        android:showAsAction="never"
        android:title="Rotate_String"/>

    <item
        android:id="@+id/zoomInOut"
        android:orderInCategory="100"
        android:title="Zoom_In_Out"/>

    <item
        android:id="@+id/fadeInOut"
        android:orderInCategory="100"
        android:title="fade_String"/>


** =================================================================================================
 */


/*
** =================================================================================================
       imageSwitcher


    <ImageSwitcher
        android:id="@+id/imageSwitcher1"
        android:layout_width="match_parent"
        android:layout_height="100dp"/>

    <Button
        android:id="@+id/buttonNext"
        android:layout_height="wrap_content"
        android:layout_width="fill_parent"
        android:layout_marginTop="150dp"
        android:text="Animate"/>


           imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                        ActionBar.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        btnNext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentIndex++;

                if(currentIndex == messageCount)
                    currentIndex=0;
                imageSwitcher.setImageResource(imageIds[currentIndex]);
            }
        });

** =================================================================================================
 */

/*
** =================================================================================================
  Grid View

  <GridView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/gridview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:columnWidth="90dp"
    android:numColumns="auto_fit"
    android:verticalSpacing="10dp"
    android:horizontalSpacing="10dp"
    android:stretchMode="columnWidth"
    android:gravity="center"
    />


    GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this,"" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });



    public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}
** =================================================================================================
 */

/*
** =================================================================================================
    intent content

    dial = (Button) findViewById(R.id.dial);

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dia = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:01071374971"));
                startActivity(dia);
            }
        });

        browser = (Button) findViewById(R.id.browser);

        browser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent bro = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(bro);
            }
        });

        call = (Button) findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent bro = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:01083437770"));
                startActivity(bro);
            }
        });

        photo = (Button) findViewById(R.id.photo);

        photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE,null);
                startActivityForResult(pho, 2);
            }
        });

            <uses-permission android:name = "android.permission.CALL_PHONE"/>
    <uses-permission android:name = "android.permission.INTERNET"/>
    <uses-permission android:name = "android.permission.camera"/>

** ================================================================================================
 */