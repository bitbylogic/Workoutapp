package com.bitbylogic.android.workoutapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class LiftsInfoActivity extends Activity {
    private DatabaseAdapter myDbHelper;
    private Button next;
    private Button quit;
    // --Commented out by Inspection (8/4/15, 10:28 AM):Button return_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        myDbHelper = new DatabaseAdapter(this);
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
                setContentView(R.layout.activity_lifts_info);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Lifts Info</font>"));
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
                setTheme(R.style.MixedActionBarTheme);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_lifts_info_mixed);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Lifts Info</font>"));
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
                setContentView(R.layout.activity_lifts_info_red);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Lifts Info</font>"));
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
                setContentView(R.layout.activity_lifts_info);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Lifts Info</font>"));
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

        next.setTextColor(getResources().getColor(R.color.black));
        next.setText("RETURN");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setBackgroundResource(R.drawable.button_pressed);
                next.setTextColor(getResources().getColor(R.color.black));
                startActivity(new Intent(LiftsInfoActivity.this, MainActivity.class));
                finish();
            }
        });

        quit.setTextColor(getResources().getColor(R.color.black));
        quit.setText("QUIT");
        quit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                quit.setBackgroundResource(R.drawable.button_pressed);
                quit.setTextColor(getResources().getColor(R.color.black));
                exitConfirm();
            }
        });

        TextView liftWelcome = (TextView) findViewById(R.id.lift_welcome);
        liftWelcome.setText(Html.fromHtml(getResources().getString(R.string.lift_welcome)));
        TextView squatInfo = (TextView) findViewById(R.id.squat_info);
        squatInfo.setText(Html.fromHtml(getResources().getString(R.string.squat_info)));
        TextView benchInfo = (TextView) findViewById(R.id.bench_info);
        benchInfo.setText(Html.fromHtml(getResources().getString(R.string.bench_info)));
        TextView rowInfo = (TextView) findViewById(R.id.row_info);
        rowInfo.setText(Html.fromHtml(getResources().getString(R.string.row_info)));
        TextView overpressInfo = (TextView) findViewById(R.id.overpress_info);
        overpressInfo.setText(Html.fromHtml(getResources().getString(R.string.overpress_info)));
        TextView deadliftInfo = (TextView) findViewById(R.id.deadlift_info);
        deadliftInfo.setText(Html.fromHtml(getResources().getString(R.string.deadlift_info)));
        TextView curlInfo = (TextView) findViewById(R.id.curl_info);
        curlInfo.setText(Html.fromHtml(getResources().getString(R.string.curl_info)));
        TextView rowAlt = (TextView) findViewById(R.id.row_alt);
        rowAlt.setText(Html.fromHtml(getResources().getString(R.string.row_alt)));
        TextView shoulderPressAlt = (TextView) findViewById(R.id.shoulder_press_alt);
        shoulderPressAlt.setText(Html.fromHtml(getResources().getString(R.string.shoulder_press_alt)));
        TextView stiffAlt = (TextView) findViewById(R.id.stiff_alt);
        stiffAlt.setText(Html.fromHtml(getResources().getString(R.string.stiff_alt)));
        TextView curlAlt = (TextView) findViewById(R.id.curl_alt);
        curlAlt.setText(Html.fromHtml(getResources().getString(R.string.curl_alt)));

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
                        Intent intent = new Intent(LiftsInfoActivity.this, MainActivity.class);
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
}
