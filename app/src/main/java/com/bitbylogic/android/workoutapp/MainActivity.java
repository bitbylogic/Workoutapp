package com.bitbylogic.android.workoutapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    // --Commented out by Inspection (8/4/15, 10:29 AM):TimerActivity popupTimer = new TimerActivity();
    private int setNum = 1;
    private int liftNum = 0;
    private DatabaseAdapter myDbHelper;
    private Button finish;
    private Button quit;
    private Button exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        myDbHelper = new DatabaseAdapter(this);
        myDbHelper.initTheme();
        String theme = myDbHelper.getTheme();
        //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        ActionBar bar = getActionBar();

        switch (theme) {
            case "Blue Theme": {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                setContentView(R.layout.activity_main);
                //TextView yourTextView = (TextView) findViewById(titleId);
               // yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                bar.setTitle(Html.fromHtml("<font color='#009cff'>WorkoutApp</font>"));
                setTheme(R.style.CustomActionBarTheme);
                exercise = (Button) findViewById(R.id.exercise_title_button);
                exercise.setBackgroundResource(R.drawable.blue_button);
                finish = (Button) findViewById(R.id.finish_button);
                finish.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.abort_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                Log.d(null, "theme is default");
                break;
            }
            case "Mixed Theme": {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red_blue);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red_blue);
                bar.setLogo(R.drawable.buff_andy_red_blue);
                setContentView(R.layout.activity_main_mixed);
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>WorkoutApp</font>"));
                setTheme(R.style.MixedActionBarTheme);
                exercise = (Button) findViewById(R.id.exercise_title_button);
                exercise.setBackgroundResource(R.drawable.mix_button);
                finish = (Button) findViewById(R.id.finish_button);
                finish.setBackgroundResource(R.drawable.mix_button);
                quit = (Button) findViewById(R.id.abort_button);
                quit.setBackgroundResource(R.drawable.mix_button);
                break;
            }
            case "Red Theme": {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red);
                bar.setLogo(R.drawable.buff_andy_red);
                setContentView(R.layout.activity_main_red);
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>WorkoutApp</font>"));
                setTheme(R.style.RedActionBarTheme);
                exercise = (Button) findViewById(R.id.exercise_title_button);
                exercise.setBackgroundResource(R.drawable.red_button);
                finish = (Button) findViewById(R.id.finish_button);
                finish.setBackgroundResource(R.drawable.red_button);
                quit = (Button) findViewById(R.id.abort_button);
                quit.setBackgroundResource(R.drawable.red_button);
                break;
            }
            default: {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                setContentView(R.layout.activity_main);
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                bar.setTitle(Html.fromHtml("<font color='#009cff'>WorkoutApp</font>"));
                setTheme(R.style.CustomActionBarTheme);
                exercise = (Button) findViewById(R.id.exercise_title_button);
                exercise.setBackgroundResource(R.drawable.blue_button);
                finish = (Button) findViewById(R.id.finish_button);
                finish.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.abort_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                Log.d(null, "theme is default");
                break;
            }
        }

        if( getIntent().getBooleanExtra("Exit me", false)){
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
            finish();
        }

        if(myDbHelper.getProgStatus() == 1) {
            startActivity(new Intent("com.bitbylogic.android.workoutapp.WelcomeActivity"));
            Log.d(null, "Program Status is new");
            finish();
        } else if(myDbHelper.getProgStatus() == 0) {
            setNum = myDbHelper.getSet();
            liftNum = myDbHelper.getLiftNum();

            exercise.setTextColor(getResources().getColor(R.color.black));
            exercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exercise.setBackgroundResource(R.drawable.button_pressed);
                    exercise.setTextColor(getResources().getColor(R.color.black));
                    startActivity(new Intent("com.bitbylogic.android.workoutapp.LiftsInfoActivity"));
                }
            });

            TextView liftTitle = (TextView) findViewById(R.id.lift_view);
            liftTitle.setText(myDbHelper.getLiftNames(liftNum));

            TextView dayTitle = (TextView) findViewById(R.id.day_view);
            dayTitle.setText(String.valueOf(myDbHelper.getDay()));

            TextView setsInfo = (TextView) findViewById(R.id.sets_info);
            setsInfo.setText(String.valueOf(setNum));

            TextView wtInfo = (TextView) findViewById(R.id.wt_info);
            wtInfo.setText(String.valueOf(repWeight()));

            TextView repInfo = (TextView) findViewById(R.id.reps_info);
            repInfo.setText(String.valueOf(myDbHelper.getWeek() + 7));

            finish.setTextColor(getResources().getColor(R.color.black));
            finish.setText("NEXT");
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish.setBackgroundResource(R.drawable.button_pressed);
                    finish.setTextColor(getResources().getColor(R.color.black));
                    incrementSet();
                }
            });

            quit.setTextColor(getResources().getColor(R.color.black));
            quit.setText("ABORT");
            quit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    quit.setBackgroundResource(R.drawable.button_pressed);
                    quit.setTextColor(getResources().getColor(R.color.black));
                    exitConfirm();
                }
            });

            /*advance = (Button) findViewById(R.id.advance_button);
            advance.setBackgroundResource(R.drawable.blue_button);
            advance.setTextColor(getResources().getColor(R.color.black));
            advance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    advance.setBackgroundResource(R.drawable.blue_button_pressed);
                    advance.setTextColor(getResources().getColor(R.color.theme_blue_dark));
                    myDbHelper.setWeek(5);
                    myDbHelper.setDay(3);
                    myDbHelper.setLiftNum(5);
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }); */

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
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_progress:
                startActivity(new Intent("com.bitbylogic.android.workoutapp.ProgressActivity"));
                return true;
            case R.id.action_program:
                startActivity(new Intent("com.bitbylogic.android.workoutapp.WelcomeActivity"));
                return true;
            case R.id.action_lifts:
                startActivity(new Intent("com.bitbylogic.android.workoutapp.LiftsInfoActivity"));
                return true;
            case R.id.action_reconcile:
                startActivity(new Intent("com.bitbylogic.android.workoutapp.ReconcileActivity"));
                finish();
                return true;
            case R.id.action_theme:
                startActivity(new Intent("com.bitbylogic.android.workoutapp.ThemeActivity"));
                finish();
                return true;
            case R.id.action_reset:
                resetConfirm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetConfirm() {

        new AlertDialog.Builder(this).setTitle("Reset Data").setMessage("Are you sure you want to start over?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myDbHelper.setProgStatus();
                        myDbHelper.resetData();
                        startActivity(new Intent("com.bitbylogic.android.workoutapp.WelcomeActivity"));
                        finish();
                    }
                })
                .setNegativeButton("No", null).show();

    }

    private void exitConfirm() {

        new AlertDialog.Builder(this).setTitle("Finish Day").setMessage("Are you sure you want to finish the day?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myDbHelper.setLiftNum(0);
                        myDbHelper.setSet(1);
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Exit me", true);
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null).show();

    }

    private void incrementSet()  {
        liftNum = myDbHelper.getLiftNum();
        setNum = myDbHelper.getSet();
        Intent intent = new Intent(getBaseContext(), TimerActivity.class);

        if (liftNum == 0 || liftNum == 1 || liftNum == 2)    {
            if (setNum == 4)    {
                setNum = 1;
                liftNum++;
                intent.putExtra("EXTRA_TIME", 180000);
                startActivity(intent);
            } else if (setNum == 3){
                setNum++;
                intent.putExtra("EXTRA_TIME", 120000);
                startActivity(intent);
            } else {
                setNum++;
                intent.putExtra("EXTRA_TIME", 60000);
                startActivity(intent);
            }
        } else {
            if (setNum == 2)    {
                setNum = 1;
                if(liftNum == 5)    {
                    liftNum = 0;
                    incrementDay();
                } else {
                    liftNum++;
                    intent.putExtra("EXTRA_TIME", 180000);
                    startActivity(intent);
                }
            } else {
                setNum++;
                intent.putExtra("EXTRA_TIME", 120000);
                startActivity(intent);
            }
        }
        myDbHelper.setLiftNum(liftNum);
        myDbHelper.setSet(setNum);
        finish();

    }

    private void incrementDay()  {
        int day = myDbHelper.getDay();
        int wk = myDbHelper.getWeek();
        int wkInc = 0;

        if(day == 3)    {
            day = 1;
            if (wk == 5)    {
                wk = 1;
                wkInc = 1;
            } else {
                wk++;
            }
        } else {
            day++;
        }
        myDbHelper.setDay(day);
        myDbHelper.setWeek(wk);
        if (wkInc == 0) {
            startActivity(new Intent("com.bitbylogic.android.workoutapp.DayDoneActivity"));
            finish();
        } else {
            startActivity(new Intent("com.bitbylogic.android.workoutapp.LiftCycleActivity"));
            finish();
        }
    }


    private int repWeight() {
        liftNum = myDbHelper.getLiftNum();
        setNum = myDbHelper.getSet();
        int result = 0;
        int dayNum = myDbHelper.getDay();

        if (liftNum == 0 || liftNum == 1 || liftNum == 2)   {
            if (dayNum == 1) {
                switch (setNum) {
                    case 1:
                        result = myDbHelper.getWu1(liftNum + 1);
                        return result;
                    case 2:
                        result = myDbHelper.getWu2(liftNum + 1);
                        return result;
                    case 3:
                        result = myDbHelper.getDay1(liftNum + 1);
                        return result;
                    case 4:
                        result = myDbHelper.getDay1(liftNum + 1);
                        return result;
                }
            } else if (dayNum == 2) {
                switch (setNum) {
                    case 1:
                        result = myDbHelper.getWu1(liftNum + 1);
                        return result;
                    case 2:
                        result = myDbHelper.getWu2(liftNum + 1);
                        return result;
                    case 3:
                        result = myDbHelper.getDay2(liftNum + 1);
                        return result;
                    case 4:
                        result = myDbHelper.getDay2(liftNum + 1);
                        return result;
                }
            } else if (dayNum == 3) {
                switch (setNum) {
                    case 1:
                        result = myDbHelper.getWu1(liftNum + 1);
                        return result;
                    case 2:
                        result = myDbHelper.getWu2(liftNum + 1);
                        return result;
                    case 3:
                        result = myDbHelper.getDay3(liftNum + 1);
                        return result;
                    case 4:
                        result = myDbHelper.getDay3(liftNum + 1);
                        return result;
                }
            }
        } else {
            if (dayNum == 1) {
                switch (setNum) {
                    case 1:
                        result = myDbHelper.getDay1(liftNum + 1);
                        return result;
                    case 2:
                        result = myDbHelper.getDay1(liftNum + 1);
                        return result;
                }
            } else if (dayNum == 2) {
                switch (setNum) {
                    case 1:
                        result = myDbHelper.getDay2(liftNum + 1);
                        return result;
                    case 2:
                        result = myDbHelper.getDay2(liftNum + 1);
                        return result;
                }
            } else if (dayNum == 3) {
                switch (setNum) {
                    case 1:
                        result = myDbHelper.getDay3(liftNum + 1);
                        return result;
                    case 2:
                        result = myDbHelper.getDay3(liftNum + 1);
                        return result;
                }
            }
        }
        return result;

    }

// --Commented out by Inspection START (8/4/15, 10:29 AM):
//    public void AppExit()
//    {
//
//        this.finish();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//
//    }
// --Commented out by Inspection STOP (8/4/15, 10:29 AM)

}
