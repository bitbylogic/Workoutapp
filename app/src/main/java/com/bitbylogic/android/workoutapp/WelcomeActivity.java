package com.bitbylogic.android.workoutapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class WelcomeActivity extends Activity {
    private DatabaseAdapter myDbHelper;
    private Button next;
    private Button quit;
    private int secretTouch;
    private long startMillis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        myDbHelper = new DatabaseAdapter(this);
        myDbHelper.initTheme();
        String theme = myDbHelper.getTheme();
        switch (theme) {
            case "Blue Theme": {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_welcome);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Program Info</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                next = (Button) findViewById(R.id.next_button);
                next.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                break;
            }
            case "Mixed Theme": {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red_blue);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red_blue);
                bar.setLogo(R.drawable.buff_andy_red_blue);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_welcome_mixed);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Program Info</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.MixedActionBarTheme);
                next = (Button) findViewById(R.id.next_button);
                next.setBackgroundResource(R.drawable.mix_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.mix_button);
                break;
            }
            case "Red Theme": {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red);
                bar.setLogo(R.drawable.buff_andy_red);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_welcome_red);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Program Info</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.RedActionBarTheme);
                next = (Button) findViewById(R.id.next_button);
                next.setBackgroundResource(R.drawable.red_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.red_button);
                break;
            }
            default: {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_welcome);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Program Info</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                next = (Button) findViewById(R.id.next_button);
                next.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                break;
            }
        }
        showCalcLink();
        secretTouch = 0;
        TextView grats = (TextView) findViewById(R.id.grats_view);
        next.setTextColor(getResources().getColor(R.color.black));
        if(myDbHelper.getProgStatus() == 0) {
            next.setText("RETURN");
            } else {
            next.setText("SETUP");
        }
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setBackgroundResource(R.drawable.button_pressed);
                next.setTextColor(getResources().getColor(R.color.black));
                if (myDbHelper.getProgStatus() == 0) {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent("com.bitbylogic.android.workoutapp.SetupActivity"));
                    finish();
                }
            }
        });
        quit.setTextColor(getResources().getColor(R.color.black));
        quit.setText("QUIT");
        quit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                quit.setBackgroundResource(R.drawable.button_pressed);
                quit.setTextColor(getResources().getColor(R.color.black));
                exitConfirm();
            }
        });

        grats.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //get system current milliseconds
                long time = System.currentTimeMillis();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //if it is the first time, or if it has been more than 5 seconds since the first tap ( so it is like a new try), we reset everything
                    if (startMillis == 0 || (time - startMillis > 5000)) {
                        startMillis = time;
                        secretTouch = 1;
                    }
                    //if it is not the first, and it has been  less than 5 seconds since the first
                    else { //  time-startMillis< 5000
                        secretTouch++;
                    }
                }
                if (secretTouch == 6) {
                    startActivity(new Intent("com.bitbylogic.android.workoutapp.SecretActivity"));
                    finish();
                    return true;
                } else return false;


            }
        });
        TextView welcome = (TextView) findViewById(R.id.welcome_message);
        welcome.setText(Html.fromHtml(getResources().getString(R.string.welcome)));
        TextView rep_cycle = (TextView) findViewById(R.id.rep_cycle);
        rep_cycle.setText(Html.fromHtml(getResources().getString(R.string.rep_cycle)));
        TextView day_cycle = (TextView) findViewById(R.id.day_cycle);
        day_cycle.setText(Html.fromHtml(getResources().getString(R.string.day_cycle)));
        TextView cycle_prog = (TextView) findViewById(R.id.cycle_prog);
        cycle_prog.setText(Html.fromHtml(getResources().getString(R.string.cycle_prog)));
        TextView safety = (TextView) findViewById(R.id.safety);
        safety.setText(Html.fromHtml(getResources().getString(R.string.safety)));
        TextView lifts = (TextView) findViewById(R.id.lifts);
        lifts.setText(Html.fromHtml(getResources().getString(R.string.lifts)));
        TextView warmups = (TextView) findViewById(R.id.warmups);
        warmups.setText(Html.fromHtml(getResources().getString(R.string.warmups)));
        TextView determine_wt = (TextView) findViewById(R.id.determine_wt);
        determine_wt.setText(Html.fromHtml(getResources().getString(R.string.determine_wt)));
        TextView missed_day = (TextView) findViewById(R.id.missed_day);
        missed_day.setText(Html.fromHtml(getResources().getString(R.string.missed_day)));
        TextView disclaimer = (TextView) findViewById(R.id.disclaimer);
        disclaimer.setText(Html.fromHtml(getResources().getString(R.string.disclaimer)));
        TextView to_menu = (TextView) findViewById(R.id.to_menu);
        to_menu.setText(Html.fromHtml(getResources().getString(R.string.to_menu)));
        TextView welcome_next = (TextView) findViewById(R.id.welcome_next);
        welcome_next.setText(Html.fromHtml(getResources().getString(R.string.welcome_next)));

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
                finish();
                return true;
            case R.id.action_lifts:
                startActivity(new Intent("com.bitbylogic.android.workoutapp.LiftsInfoActivity"));
                finish();
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

    private void exitConfirm() {

        new AlertDialog.Builder(this).setTitle("Exit App").setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Exit me", true);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", null).show();

    }

    private void resetConfirm() {

        new AlertDialog.Builder(this).setTitle("Reset Data").setMessage("Are you sure you want to start over?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myDbHelper.setProgStatus();
                        myDbHelper.resetData();
                        startActivity(new Intent("com.bitbylogic.android.workoutapp.SetupActivity"));
                    }
                })
                .setNegativeButton("No", null).show();

    }

    private void showCalcLink()  {

        TextView textView = (TextView)findViewById(R.id.rep_calc);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.naturalphysiques.com/18/one-rep-max-calculator'> CLICK FOR 10-REP CALCULATOR </a>";
        textView.setText(Html.fromHtml(text));


    }

}
