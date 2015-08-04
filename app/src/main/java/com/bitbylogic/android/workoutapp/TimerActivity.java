package com.bitbylogic.android.workoutapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class TimerActivity extends Activity  {

    private TextView timeLeft;
    private final Context context = this;
    private Button stop_button;
    private int timerDone = 0;
    private int time;
    private final NumberFormat f = new DecimalFormat("00");
    private Notification n;
    private NotificationManager notificationManager;
    private DatabaseAdapter myDbHelper;
    private String theme;
    private AlarmUtil alarm;
    private MyTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        myDbHelper = new DatabaseAdapter(this);
        theme = myDbHelper.getTheme();
        switch (theme) {
            case "Blue Theme":
                setContentView(R.layout.activity_timer);
                stop_button = (Button) findViewById(R.id.timer_stop_button);
                stop_button.setBackgroundResource(R.drawable.blue_button);
                break;
            case "Mixed Theme":
                setContentView(R.layout.activity_timer_mixed);
                stop_button = (Button) findViewById(R.id.timer_stop_button);
                stop_button.setBackgroundResource(R.drawable.mix_button);
                break;
            case "Red Theme":
                setContentView(R.layout.activity_timer_red);
                stop_button = (Button) findViewById(R.id.timer_stop_button);
                stop_button.setBackgroundResource(R.drawable.red_button);
                break;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            time = extras.getInt("EXTRA_TIME", 10000);
        }
        alarm = new AlarmUtil(this);
        timeLeft = (TextView) findViewById(R.id.time_rem);
        timer = new MyTimer(time);
        timer.start();
        stop_button.setTextColor(getResources().getColor(R.color.black));
        stop_button.setText("STOP");
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_button.setBackgroundResource(R.drawable.button_pressed);
                stop_button.setTextColor(getResources().getColor(R.color.black));
                alarm.stopBeepSoundAndVibrate();
                Intent intent = new Intent(TimerActivity.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();
                notificationManager.cancelAll();
                finish();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myDbHelper = new DatabaseAdapter(this);
        theme = myDbHelper.getTheme();
        switch (theme) {
            case "Blue Theme":
                setContentView(R.layout.activity_timer);
                stop_button = (Button) findViewById(R.id.timer_stop_button);
                stop_button.setBackgroundResource(R.drawable.blue_button);
                break;
            case "Mixed Theme":
                setContentView(R.layout.activity_timer_mixed);
                stop_button = (Button) findViewById(R.id.timer_stop_button);
                stop_button.setBackgroundResource(R.drawable.mix_button);
                break;
            case "Red Theme":
                setContentView(R.layout.activity_timer_red);
                stop_button = (Button) findViewById(R.id.timer_stop_button);
                stop_button.setBackgroundResource(R.drawable.red_button);
                break;
        }
        timeLeft = (TextView)findViewById(R.id.time_rem);
        if (timerDone == 1) {
            timeLeft.setText("Back to work!");
            stop_button.setText("WORK OUT");
        }
        stop_button.setTextColor(getResources().getColor(R.color.black));
        stop_button.setText("STOP");
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_button.setBackgroundResource(R.drawable.button_pressed);
                stop_button.setTextColor(getResources().getColor(R.color.theme_blue_dark));
                alarm.stopBeepSoundAndVibrate();
                Intent intent = new Intent(TimerActivity.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();
                notificationManager.cancelAll();
                finish();
            }
        });
    }

    private void notif(String subject)   {

        Intent intent = new Intent(context, TimerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        switch (theme) {
            case "Blue Theme":
                n = new Notification.Builder(context)
                        .setContentTitle("WorkoutApp ")
                        .setContentText(subject)
                        .setSmallIcon(R.drawable.buff_andy)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .addAction(0, "Back to workout", pIntent).build();
                break;
            case "Mixed Theme":
                n = new Notification.Builder(context)
                        .setContentTitle("WorkoutApp ")
                        .setContentText(subject)
                        .setSmallIcon(R.drawable.buff_andy_red_blue)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .addAction(0, "Back to workout", pIntent).build();
                break;
            case "Red Theme":
                n = new Notification.Builder(context)
                        .setContentTitle("WorkoutApp ")
                        .setContentText(subject)
                        .setSmallIcon(R.drawable.buff_andy_red)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .addAction(0, "Back to workout", pIntent).build();
                break;
        }

        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager == null)
            notificationManager = (NotificationManager)  getSystemService(NOTIFICATION_SERVICE);
        n.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(1, n);

    }

    public class MyTimer extends CountDownTimer  {
        public MyTimer(long millisInFuture) {
            super(millisInFuture, (long) 1000);
        }


        @Override
        public void onFinish() {

            timerDone = 1;
            timeLeft.setText("Back to work!");
            stop_button.setText("WORK OUT");
            alarm.updatePrefs();
            alarm.playBeepSoundAndVibrate();
            notif("Rest time is over");

        }

        @Override
        public void onTick(long millisUntilFinished) {

            int min = (int) (millisUntilFinished / 1000 / 60);
            int sec = (int) ((millisUntilFinished / 1000) % 60);
            timeLeft.setText("Rest time remaining: " + min + ":" + f.format(sec));
            timeLeft.setGravity(1);
            if (timerDone == 0) {
                notif("Rest time remaining: " + min + ":" + f.format(sec));
            } else {
                notificationManager.cancel(1);
            }

        }



    }



}
