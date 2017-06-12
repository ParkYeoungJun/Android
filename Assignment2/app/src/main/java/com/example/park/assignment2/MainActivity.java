package com.example.park.assignment2;

import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button ai, user, rock, scissor, paper, start, save, setting, file;

    int winStrike = 1;
    String userName = "Player";

    Bitmap b_rock,b_scissor,b_paper;
    Drawable d_rock,d_scissor,d_paper;

    Animation click;
    Animation ani;

    int what[];
    int Ai_winCount, Player_winCount;
    boolean checked = false;

    AlertDialog.Builder builder;

    String inFile = "";

    SoundPool pool;
    int congrat;

    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, Start.class));

        ai = (Button)findViewById(R.id.aibtn);
        user = (Button)findViewById(R.id.userview);
        rock = (Button)findViewById(R.id.rock);
        scissor = (Button)findViewById(R.id.scissor);
        paper = (Button)findViewById(R.id.paper);
        start = (Button)findViewById(R.id.start);
        save = (Button)findViewById(R.id.save);
        setting = (Button)findViewById(R.id.setting);
        file = (Button)findViewById(R.id.file);


        b_rock = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
        b_scissor = BitmapFactory.decodeResource(getResources(), R.drawable.scissor);
        b_paper = BitmapFactory.decodeResource(getResources(), R.drawable.paper);

        d_rock = new BitmapDrawable(getResources(), b_rock);
        d_scissor = new BitmapDrawable(getResources(), b_scissor);
        d_paper = new BitmapDrawable(getResources(), b_paper);

        click = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.click);
        ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apear);


        what = new int[2];
        what[0] = -1;
        what[1] = -1;

        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        congrat = pool.load(MainActivity.this, R.raw.game, 1);

        vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        ai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }
        });

        rock.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v){

                what[1] = 1;

                user.setBackground(d_rock);
                user.setAnimation(ani);

                checked = true;

                rock.startAnimation(click);

                Toast.makeText(MainActivity.this, "Rock", Toast.LENGTH_SHORT).show();

            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                what[1] = 2;

                user.setBackground(d_scissor);
                user.setAnimation(ani);

                checked = true;

                scissor.startAnimation(click);

                Toast.makeText(MainActivity.this, "Scissor", Toast.LENGTH_SHORT).show();

            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                what[1] = 3;

                user.setBackground(d_paper);
                user.setAnimation(ani);

                checked = true;

                paper.startAnimation(click);

                Toast.makeText(MainActivity.this, "Paper", Toast.LENGTH_SHORT).show();
            }
        });

        start.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                vibe.vibrate(200);

                if (what[1] == -1) {

                    Toast.makeText(MainActivity.this, "Please Choose", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        what[0] = (int) (Math.random() * 3 + 1);

                        Animation ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apear);

                        if (what[0] == 1) {
                            ai.setBackground(d_rock);
                            ai.setAnimation(ani);
                        } else if (what[0] == 2) {
                            ai.setBackground(d_scissor);
                            ai.setAnimation(ani);
                        } else if (what[0] == 3) {
                            ai.setBackground(d_paper);
                            ai.setAnimation(ani);
                        }

                        pool.play(congrat, 1, 1, 0, 0, 1);

                        Thread.sleep(700);
                    } catch (Exception e) {
                    }
                }

                if(what[0] == what[1] && what[1] != -1)
                {
                    Toast.makeText(MainActivity.this, "Draw !!", Toast.LENGTH_SHORT).show();
                }
                else if(what[0] == 1 && what[1] == 2)
                {
                    Toast.makeText(MainActivity.this, "AI Win !!", Toast.LENGTH_SHORT).show();
                    Ai_winCount++;
                }
                else if(what[0] == 1 && what[1] == 3)
                {
                    Toast.makeText(MainActivity.this, "Player Win !!", Toast.LENGTH_SHORT).show();
                    Player_winCount++;
                }
                else if(what[0] == 2 && what[1] == 1)
                {
                    Toast.makeText(MainActivity.this, "Player Win !!", Toast.LENGTH_SHORT).show();
                    Player_winCount++;
                }
                else if(what[0] == 2 && what[1] == 3)
                {
                    Toast.makeText(MainActivity.this, "AI Win !!", Toast.LENGTH_SHORT).show();
                    Ai_winCount++;
                }
                else if(what[0] == 3 && what[1] == 1)
                {
                    Toast.makeText(MainActivity.this, "AI Win !!", Toast.LENGTH_SHORT).show();
                    Ai_winCount++;
                }
                else if(what[0] == 3 && what[1] == 2)
                {
                    Toast.makeText(MainActivity.this, "Player Win !!", Toast.LENGTH_SHORT).show();
                    Player_winCount++;
                }

                if(Ai_winCount == winStrike)
                {
                    vibe.vibrate(200);

                    builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Winner");

                    builder.setMessage("        Ai is Win !!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();

                    inFile += "Ai : "+Ai_winCount+"   "+userName+" : "+Player_winCount+"\n" +
                            "Ai Win\n\n";

                    Player_winCount = 0;
                    Ai_winCount = 0;
                }
                else if(Player_winCount == winStrike)
                {
                    vibe.vibrate(200);

                    builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Winner");

                    builder.setMessage("       " + userName + " is Win !!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();

                    inFile += "Ai : "+Ai_winCount+"   "+userName+" : "+Player_winCount+"\n" +
                            "Player Win\n\n";

                    Player_winCount = 0;
                    Ai_winCount = 0;
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    FileInputStream fileIn = openFileInput("AssignmentResult.txt");

                    InputStreamReader reader = new InputStreamReader(fileIn);

                    char[] inputBuffer = new char[100];

                    int charRead;
                    String str = "";

                    while((charRead = reader.read(inputBuffer)) > 0){
                        String readstring = String.copyValueOf(inputBuffer,0,charRead);
                        str += readstring;
                    }

                    reader.close();

                    FileOutputStream fileout = openFileOutput("AssignmentResult.txt", MODE_PRIVATE);

                    OutputStreamWriter writer = new OutputStreamWriter(fileout);

                    writer.write(str + inFile);

                    writer.close();

                    inFile = "";

                    Toast.makeText(MainActivity.this, "Results Saved", Toast.LENGTH_SHORT).show();

                }catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), settingactivity.class);
                startActivityForResult(i, 1);
            }
        });

        file.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent resultfile = new Intent(MainActivity.this, resultActivity.class);

                startActivity(resultfile);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            displayUserSetting();
        }
    }

    private void displayUserSetting()
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        winStrike = Integer.parseInt(sharedPrefs.getString("list", "NO"));
        userName = sharedPrefs.getString("name", "NO");

        if(userName.equals(""))
            userName = "Player";
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
