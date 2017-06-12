package com.example.park.assignment1;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int locate[][];
    int num;

    Button reset;
    Button button11, button12, button13, button14, button15;
    Button button21, button22, button23, button24, button25;
    Button button31, button32, button33, button34, button35;
    Button button41, button42, button43, button44, button45;
    Button button51, button52, button53, button54, button55;

    Bitmap bit1, bit2, bit3;
    Drawable draw1, draw2, draw3;
    FragmentManager fm = getFragmentManager();

    MediaPlayer sound_background;
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, Start.class));

        sound_background = MediaPlayer.create(this,R.raw.bgm);
        sound_background.start();
        sound_background.setLooping(true);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        reset = (Button) findViewById(R.id.resetbutton);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button24 = (Button) findViewById(R.id.button24);
        button25 = (Button) findViewById(R.id.button25);
        button31 = (Button) findViewById(R.id.button31);
        button32 = (Button) findViewById(R.id.button32);
        button33 = (Button) findViewById(R.id.button33);
        button34 = (Button) findViewById(R.id.button34);
        button35 = (Button) findViewById(R.id.button35);
        button41 = (Button) findViewById(R.id.button41);
        button42 = (Button) findViewById(R.id.button42);
        button43 = (Button) findViewById(R.id.button43);
        button44 = (Button) findViewById(R.id.button44);
        button45 = (Button) findViewById(R.id.button45);
        button51 = (Button) findViewById(R.id.button51);
        button52 = (Button) findViewById(R.id.button52);
        button53 = (Button) findViewById(R.id.button53);
        button54 = (Button) findViewById(R.id.button54);
        button55 = (Button) findViewById(R.id.button55);

        num = 0; // odd or even number deter player

        locate = new int[5][5];  //

        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < 5; ++j)
                locate[i][j] = -1;

        bit1 = BitmapFactory.decodeResource(getResources(), R.drawable.images1);
        bit2 = BitmapFactory.decodeResource(getResources(), R.drawable.images2);
        bit3 = BitmapFactory.decodeResource(getResources(), R.drawable.images3);

        draw1 = new BitmapDrawable(getResources(), bit1);
        draw2 = new BitmapDrawable(getResources(), bit2);
        draw3 = new BitmapDrawable(getResources(), bit3);

        picreset();

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                sound_background.start();

                picreset();

                for(int i = 0 ; i < 5 ; ++i)
                    for(int j = 0 ; j < 5 ; ++j)
                        locate[i][j] = -1;

                num=0;

            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[0][0] == -1) {
                    if ((++num) % 2 == 1) {
                        button11.setBackground(draw1);
                        locate[0][0] = 1;
                    } else {
                        button11.setBackground(draw2);
                        locate[0][0] = 2;
                    }

                    Checkfin();

                } else if(locate[0][0] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[0][1] == -1) {
                    if ((++num) % 2 == 1) {
                        button12.setBackground(draw1);
                        locate[0][1] = 1;
                    } else {
                        button12.setBackground(draw2);
                        locate[0][1] = 2;
                    }

                    Checkfin();

                } else if(locate[0][1] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[0][2] == -1) {
                    if ((++num) % 2 == 1) {
                        button13.setBackground(draw1);
                        locate[0][2] = 1;
                    } else {
                        button13.setBackground(draw2);
                        locate[0][2] = 2;
                    }

                    Checkfin();

                } else if(locate[0][2] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[0][3] == -1) {
                    if ((++num) % 2 == 1) {
                        button14.setBackground(draw1);
                        locate[0][3] = 1;
                    } else {
                        button14.setBackground(draw2);
                        locate[0][3] = 2;
                    }

                    Checkfin();

                } else if(locate[0][3] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[0][4] == -1) {
                    if ((++num) % 2 == 1) {
                        button15.setBackground(draw1);
                        locate[0][4] = 1;
                    } else {
                        button15.setBackground(draw2);
                        locate[0][4] = 2;
                    }

                    Checkfin();

                } else if(locate[0][4] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button21.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[1][0] == -1) {
                    if ((++num) % 2 == 1) {
                        button21.setBackground(draw1);
                        locate[1][0] = 1;
                    } else {
                        button21.setBackground(draw2);
                        locate[1][0] = 2;
                    }

                    Checkfin();

                } else if(locate[1][0] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button22.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[1][1] == -1) {
                    if ((++num) % 2 == 1) {
                        button22.setBackground(draw1);
                        locate[1][1] = 1;
                    } else {
                        button22.setBackground(draw2);
                        locate[1][1] = 2;
                    }

                    Checkfin();

                } else if(locate[1][1] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button23.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[1][2] == -1) {
                    if ((++num) % 2 == 1) {
                        button23.setBackground(draw1);
                        locate[1][2] = 1;
                    } else {
                        button23.setBackground(draw2);
                        locate[1][2] = 2;
                    }

                    Checkfin();

                } else if(locate[1][2] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button24.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[1][3] == -1) {
                    if ((++num) % 2 == 1) {
                        button24.setBackground(draw1);
                        locate[1][3] = 1;
                    } else {
                        button24.setBackground(draw2);
                        locate[1][3] = 2;
                    }

                    Checkfin();

                } else if(locate[1][3] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button25.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[1][4] == -1) {
                    if ((++num) % 2 == 1) {
                        button25.setBackground(draw1);
                        locate[1][4] = 1;
                    } else {
                        button25.setBackground(draw2);
                        locate[1][4] = 2;
                    }

                    Checkfin();

                } else if(locate[1][4] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button31.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[2][0] == -1) {
                    if ((++num) % 2 == 1) {
                        button31.setBackground(draw1);
                        locate[2][0] = 1;
                    } else {
                        button31.setBackground(draw2);
                        locate[2][0] = 2;
                    }

                    Checkfin();

                } else if(locate[2][0] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button32.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[2][1] == -1) {
                    if ((++num) % 2 == 1) {
                        button32.setBackground(draw1);
                        locate[2][1] = 1;
                    } else {
                        button32.setBackground(draw2);
                        locate[2][1] = 2;
                    }

                    Checkfin();

                } else if(locate[2][1] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button33.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[2][2] == -1) {
                    if ((++num) % 2 == 1) {
                        button33.setBackground(draw1);
                        locate[2][2] = 1;
                    } else {
                        button33.setBackground(draw2);
                        locate[2][2] = 2;
                    }

                    Checkfin();

                } else if(locate[2][2] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button34.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[2][3] == -1) {
                    if ((++num) % 2 == 1) {
                        button34.setBackground(draw1);
                        locate[2][3] = 1;
                    } else {
                        button34.setBackground(draw2);
                        locate[2][3] = 2;
                    }

                    Checkfin();

                } else if(locate[2][3] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button35.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[2][4] == -1) {
                    if ((++num) % 2 == 1) {
                        button35.setBackground(draw1);
                        locate[2][4] = 1;
                    } else {
                        button35.setBackground(draw2);
                        locate[2][4] = 2;
                    }

                    Checkfin();

                } else if(locate[2][4] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button41.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[3][0] == -1) {
                    if ((++num) % 2 == 1) {
                        button41.setBackground(draw1);
                        locate[3][0] = 1;
                    } else {
                        button41.setBackground(draw2);
                        locate[3][0] = 2;
                    }

                    Checkfin();

                } else if(locate[3][0] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button42.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[3][1] == -1) {
                    if ((++num) % 2 == 1) {
                        button42.setBackground(draw1);
                        locate[3][1] = 1;
                    } else {
                        button42.setBackground(draw2);
                        locate[3][1] = 2;
                    }

                    Checkfin();

                } else if(locate[3][1] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button43.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[3][2] == -1) {
                    if ((++num) % 2 == 1) {
                        button43.setBackground(draw1);
                        locate[3][2] = 1;
                    } else {
                        button43.setBackground(draw2);
                        locate[3][2] = 2;
                    }

                    Checkfin();

                } else if(locate[3][2] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button44.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[3][3] == -1) {
                    if ((++num) % 2 == 1) {
                        button44.setBackground(draw1);
                        locate[3][3] = 1;
                    } else {
                        button44.setBackground(draw2);
                        locate[3][3] = 2;
                    }

                    Checkfin();

                } else if(locate[3][3] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button45.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[3][4] == -1) {
                    if ((++num) % 2 == 1) {
                        button45.setBackground(draw1);
                        locate[3][4] = 1;
                    } else {
                        button45.setBackground(draw2);
                        locate[3][4] = 2;
                    }

                    Checkfin();

                } else if(locate[3][4] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button51.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[4][0] == -1) {
                    if ((++num) % 2 == 1) {
                        button51.setBackground(draw1);
                        locate[4][0] = 1;
                    } else {
                        button51.setBackground(draw2);
                        locate[4][0] = 2;
                    }

                    Checkfin();

                } else if(locate[4][0] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button52.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[4][1] == -1) {
                    if ((++num) % 2 == 1) {
                        button52.setBackground(draw1);
                        locate[4][1] = 1;
                    } else {
                        button52.setBackground(draw2);
                        locate[4][1] = 2;
                    }

                    Checkfin();

                } else if(locate[4][1] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button53.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[4][2] == -1) {
                    if ((++num) % 2 == 1) {
                        button53.setBackground(draw1);
                        locate[4][2] = 1;
                    } else {
                        button53.setBackground(draw2);
                        locate[4][2] = 2;
                    }

                    Checkfin();

                } else if(locate[4][2] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button54.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[4][3] == -1) {
                    if ((++num) % 2 == 1) {
                        button54.setBackground(draw1);
                        locate[4][3] = 1;
                    } else {
                        button54.setBackground(draw2);
                        locate[4][3] = 2;
                    }

                    Checkfin();

                } else if(locate[4][3] == 3){
                    Finished();
                }else
                    Checked();
            }
        });
        button55.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View view) {
                if (locate[4][4] == -1) {
                    if ((++num) % 2 == 1) {
                        button55.setBackground(draw1);
                        locate[4][4] = 1;
                    } else {
                        button55.setBackground(draw2);
                        locate[4][4] = 2;
                    }

                    Checkfin();

                } else if(locate[4][4] == 3){
                    Finished();
                }else
                    Checked();
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void picreset()
    {
        button11.setBackground(draw3);
        button12.setBackground(draw3);
        button13.setBackground(draw3);
        button14.setBackground(draw3);
        button15.setBackground(draw3);
        button21.setBackground(draw3);
        button22.setBackground(draw3);
        button23.setBackground(draw3);
        button24.setBackground(draw3);
        button25.setBackground(draw3);
        button31.setBackground(draw3);
        button32.setBackground(draw3);
        button33.setBackground(draw3);
        button34.setBackground(draw3);
        button35.setBackground(draw3);
        button41.setBackground(draw3);
        button42.setBackground(draw3);
        button43.setBackground(draw3);
        button44.setBackground(draw3);
        button45.setBackground(draw3);
        button51.setBackground(draw3);
        button52.setBackground(draw3);
        button53.setBackground(draw3);
        button54.setBackground(draw3);
        button55.setBackground(draw3);
    }

    public void Checked() {

        vibe.vibrate(200);

        Toast.makeText(this, "Aleady Checked Choose different one", Toast.LENGTH_LONG).show();
    }

    public void Finished(){

        vibe.vibrate(200);

        Toast.makeText(this, "Aleady finished", Toast.LENGTH_LONG).show();

    }

    public boolean Checkfin() {

        vibe.vibrate(200);

        int temp1;
        boolean finish;

        finish = true;
        int winner = 0;


            temp1 = locate[0][0];

            if(temp1 != -1) {
                for (int i = 0; i < 5; ++i) {
                    if (temp1 != locate[i][i]) {
                        finish = false;
                        break;
                    }
                }

                if(finish == true)
                    winner = temp1;
            }
            else
                finish = false;

        if(finish == false) {
            finish = true;

            temp1 = locate[0][4];

            if (temp1 != -1) {
                int j = 0;
                for (int i = 4; i >= 0; --i) {
                    if (temp1 != locate[i][j]) {
                        finish = false;
                        break;
                    }
                    j++;
                }

                if (finish == true)
                    winner = temp1;
            } else
                finish = false;
        }

            if (finish != true) {
                int whet = 0;
                for (int i = 0; i < 5; ++i) {
                    if (finish == true) {
                        winner = locate[i-1][0];
                        whet = 1;
                        break;
                    }
                    temp1 = locate[i][0];
                    if(temp1 == -1)
                    {
                        if(i == 4)
                            break;
                    }
                    else {
                        finish = true;
                        for (int j = 0; j < 5; ++j) {
                            if (temp1 != locate[i][j])
                                finish = false;
                        }
                    }
                }

                if(whet != 1 && finish == true)
                    winner = locate[4][0];

            }

            if (finish != true) {
                int whet = 0;

                for (int i = 0; i < 5; ++i) {
                    if (finish == true) {
                        winner = locate[0][i-1];
                        whet = 1;
                        break;
                    }
                    temp1 = locate[0][i];
                    if(temp1 == -1)
                    {
                        if(i == 4)
                        {
                            break;
                        }
                    }else {
                        finish = true;
                        for (int j = 0; j < 5; ++j) {
                            if (temp1 != locate[j][i])
                                finish = false;
                        }
                    }
                }

                if(whet != 1 && finish == true)
                    winner = locate[0][4];
            }


            if (finish == true) {
                sound_background.pause();

                if(winner == 1) {
                    Inputfrag input1 = new Inputfrag();
                    input1.show(fm, "What is Player's name ?");
                }
                else
                {
                    Inputfrag2 input2 = new Inputfrag2();
                    input2.show(fm,"What is Player's name?");
                }

                for(int i = 0 ; i < 5 ; i++)
                    for(int j = 0 ; j < 5 ; j++)
                        locate[i][j] = 3;

                return true;

            } else
            {
                finish = true;

                for (int i = 0; i < 5; ++i)
                    for (int j = 0; j < 5; ++j) {
                        if (locate[i][j] == -1) {
                            finish = false;
                            break;
                        }
                    }

                if(finish == true) {
                    for(int i = 0 ; i < 5 ; i++)
                        for(int j = 0 ; j < 5 ; j++)
                            locate[i][j] = 3;

                    Dialog a = new Dialog(this);
                    a.setTitle("    No Winner!!   ");
                    a.show();
                }
                return false;
            }
    }

}
