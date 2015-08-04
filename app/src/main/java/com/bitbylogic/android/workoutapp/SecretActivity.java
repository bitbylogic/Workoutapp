package com.bitbylogic.android.workoutapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class SecretActivity extends Activity {
    private final Context context = this;
    private DatabaseAdapter myDbHelper;
    private Button save;
    private Button quit;
    private EditText weekInput;
    private EditText dayInput;
    private EditText liftInput;
    private final int[] setupArray = new int[3];
    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        myDbHelper = new DatabaseAdapter(this);
        theme = myDbHelper.getTheme();
        switch (theme) {
            case "Blue Theme": {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                setContentView(R.layout.activity_secret);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Admin Setup</font>"));
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.blue_button);
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
                setContentView(R.layout.activity_secret_mixed);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Admin Setup</font>"));
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.MixedActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.mix_button);
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
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setContentView(R.layout.activity_secret_red);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Admin Setup</font>"));
                setTheme(R.style.RedActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.red_button);
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
                setContentView(R.layout.activity_secret);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Admin Setup</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                break;
            }
        }

        save.setText("SAVE");
        save.setTextColor(getResources().getColor(R.color.black));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setBackgroundResource(R.drawable.button_pressed);
                save.setTextColor(getResources().getColor(R.color.black));
                weekInput = (EditText) findViewById(R.id.week_input);
                dayInput = (EditText) findViewById(R.id.day_input);
                liftInput = (EditText) findViewById(R.id.lift_input);
                if (theme.equals("Red Theme"))  {
                    weekInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    dayInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    liftInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                }

                try {
                    setupArray[0] = Integer.parseInt(weekInput.getText().toString());
                    setupArray[1] = Integer.parseInt(dayInput.getText().toString());
                    setupArray[2] = (Integer.parseInt(liftInput.getText().toString()) - 1);
                    adminSetup(setupArray);
                    startActivity(new Intent(SecretActivity.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                    switch (theme) {
                        case "Blue Theme":
                            save.setBackgroundResource(R.drawable.blue_button);
                            break;
                        case "Mixed Theme":
                            save.setBackgroundResource(R.drawable.mix_button);
                            break;
                        case "Red Theme":
                            save.setBackgroundResource(R.drawable.red_button);
                            break;
                    }
                    save.setTextColor(getResources().getColor(R.color.black));
                    Message.message(context, "Fill in each value.");
                }

            }
        });

        quit.setText("BACK");
        quit.setTextColor(getResources().getColor(R.color.black));
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit.setBackgroundResource(R.drawable.button_pressed);
                quit.setTextColor(getResources().getColor(R.color.black));
                exitConfirm();
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

        new AlertDialog.Builder(this).setTitle("Exit App").setMessage("Are you sure you don\'t want to fast forward?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(SecretActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    private void adminSetup(int[] vals)  {

            myDbHelper.setWeek(vals[0]);
            myDbHelper.setDay(vals[1]);
            myDbHelper.setLiftNum(vals[2]);

    }

}
