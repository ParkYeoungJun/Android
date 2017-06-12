package com.example.park.alarm2;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    private PendingIntent pendingIntent;
    TimePickerDialog timePick;
    DatePickerDialog datePick;
    Button setDT, setAlarm, offAlarm;
    TextView timeView, dateView;
    AlarmManager alarmManager;
    int YEAR, MONTH, DAY, HOUR, MINUTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = (TextView)findViewById(R.id.dateView);
        timeView = (TextView)findViewById(R.id.timeView);
        setDT = (Button)findViewById(R.id.setDT);
        setAlarm = (Button)findViewById(R.id.setAlarm);
        offAlarm = (Button)findViewById(R.id.offAlarm);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        setDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                OnDateSetListener callBack = new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        dateView.setText(year+". "+(month+1)+" ."+day);
                        YEAR=year;
                        MONTH=month;
                        DAY=day;
                    }
                };
                OnTimeSetListener callBack2 = new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        timeView.setText(hour+" : "+minute);
                        HOUR=hour;
                        MINUTE=minute;
                    }
                };
                datePick = new DatePickerDialog(view.getContext(), callBack, 2015, 10, 2);
                timePick = new TimePickerDialog(view.getContext(), callBack2, 00, 00, true);
                datePick.show();
                timePick.show();
            }
        });
        setAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);
                pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.YEAR, YEAR);
                calendar.set(Calendar.MONTH, MONTH);
                calendar.set(Calendar.DAY_OF_MONTH, DAY);
                calendar.set(Calendar.HOUR_OF_DAY, HOUR);
                calendar.set(Calendar.MINUTE, MINUTE);
                calendar.set(Calendar.SECOND, 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Toast.makeText(MainActivity.this, "Alarm is Be Executed", Toast.LENGTH_SHORT).show();
            }
        });
        offAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder choose = new AlertDialog.Builder(MainActivity.this);
                choose.setMessage("Choose Problem");
                choose.setPositiveButton("IQ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder iq = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                        View view = inflater.inflate(R.layout.iq, null);
                        Button button1 = (Button) view.findViewById(R.id.button1);
                        Button button2 = (Button) view.findViewById(R.id.button2);
                        Button button3 = (Button) view.findViewById(R.id.button3);

                        button1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Wrong Anwer!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                alarmManager.cancel(pendingIntent);
                                Toast.makeText(MainActivity.this, "ALARM IS OFF NOW!", Toast.LENGTH_SHORT).show();
                                dateView.setText("Canceled");
                                timeView.setText("Canceled");
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Wrong Anwer!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        iq.setTitle("IQ test, Choose 2");

                        iq.setView(view);

                        iq.show();
                    }
                });

                choose.setNegativeButton("MATH", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder math = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                        View view = inflater.inflate(R.layout.math, null);
                        Button button1 = (Button) view.findViewById(R.id.button5);
                        Button button2 = (Button) view.findViewById(R.id.button10);
                        Button button3 = (Button) view.findViewById(R.id.button15);

                        button1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                alarmManager.cancel(pendingIntent);
                                Toast.makeText(MainActivity.this, "ALARM IS OFF NOW!", Toast.LENGTH_SHORT).show();
                                dateView.setText("Canceled");
                                timeView.setText("Canceled");
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Wrong Anwer!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Wrong Anwer!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        math.setTitle("Math test, 51/(2/10+5*2)");

                        math.setView(view);

                        math.show();
                    }
                });

                choose.show();
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
}
