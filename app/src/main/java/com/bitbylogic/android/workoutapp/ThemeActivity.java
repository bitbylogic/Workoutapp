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
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class ThemeActivity extends Activity {
    private DatabaseAdapter myDbHelper;
    private Button save;
    private Button quit;
    private RadioGroup themeChoice;
    private RadioButton radioThemeChoice;

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
                setContentView(R.layout.activity_theme);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Theme</font>"));
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
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_theme_mixed);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Theme</font>"));
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
                setContentView(R.layout.activity_theme_red);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Theme</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
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
                setContentView(R.layout.activity_theme);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Theme</font>"));
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

        themeChoice = (RadioGroup)findViewById(R.id.theme_choice);


        save.setText("SAVE");
        save.setTextColor(getResources().getColor(R.color.black));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setBackgroundResource(R.drawable.button_pressed);
                save.setTextColor(getResources().getColor(R.color.black));
                int selectedTheme = themeChoice.getCheckedRadioButtonId();
                radioThemeChoice = (RadioButton) findViewById(selectedTheme);
                myDbHelper.setTheme(radioThemeChoice.getText().toString());
                Message.message(ThemeActivity.this, radioThemeChoice.getText() + " Applied");
                startActivity(new Intent(ThemeActivity.this, MainActivity.class));
                finish();

            }
        });

        quit.setText("QUIT");
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

        new AlertDialog.Builder(this).setTitle("Exit App").setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ThemeActivity.this, MainActivity.class);
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
