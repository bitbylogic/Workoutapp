package com.bitbylogic.android.workoutapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class SetupActivity extends Activity {
    private final Context context = this;
    private DatabaseAdapter myDbHelper;
    private Button save;
    private Button quit;
    private Button repCalc;
    private String theme;
    private EditText squatInput;
    private EditText benchInput;
    private EditText rowInput;
    private EditText overpressInput;
    private EditText deadliftInput;
    private EditText curlInput;
    private final int[] setupArray = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        myDbHelper = new DatabaseAdapter(this);
        myDbHelper.initTheme();
        theme = myDbHelper.getTheme();
        ActionBar bar = getActionBar();
        switch (theme) {
            case "Blue Theme": {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_setup);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Setup</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                repCalc = (Button) findViewById(R.id.rep_calc_button);
                repCalc.setBackgroundResource(R.drawable.blue_button);
                break;
            }
            case "Mixed Theme": {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red_blue);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red_blue);
                bar.setLogo(R.drawable.buff_andy_red_blue);
                setTheme(R.style.MixedActionBarTheme);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_setup_mixed);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Setup</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.MixedActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.mix_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.mix_button);
                repCalc = (Button) findViewById(R.id.rep_calc_button);
                repCalc.setBackgroundResource(R.drawable.mix_button);
                break;
            }
            case "Red Theme": {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red);
                bar.setLogo(R.drawable.buff_andy_red);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                setContentView(R.layout.activity_setup_red);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Setup</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.RedActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.red_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.red_button);
                repCalc = (Button) findViewById(R.id.rep_calc_button);
                repCalc.setBackgroundResource(R.drawable.red_button);
                break;
            }
            default: {
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy);
                bar.setLogo(R.drawable.buff_andy);
                setContentView(R.layout.activity_setup);
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Setup</font>"));
                setTheme(R.style.CustomActionBarTheme);
                save = (Button) findViewById(R.id.save_button);
                save.setBackgroundResource(R.drawable.blue_button);
                quit = (Button) findViewById(R.id.quit_button);
                quit.setBackgroundResource(R.drawable.blue_button);
                Log.d(null, "theme is default");
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
                squatInput = (EditText) findViewById(R.id.squat_input);
                benchInput = (EditText) findViewById(R.id.bench_input);
                rowInput = (EditText) findViewById(R.id.row_input);
                overpressInput = (EditText) findViewById(R.id.overpress_input);
                deadliftInput = (EditText) findViewById(R.id.deadlift_input);
                curlInput = (EditText) findViewById(R.id.curl_input);
                if (theme.equals("Red Theme"))  {
                    squatInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    benchInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    rowInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    overpressInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    deadliftInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    curlInput.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                }

                try {
                    setupArray[0] = Integer.parseInt(squatInput.getText().toString());
                    setupArray[1] = Integer.parseInt(benchInput.getText().toString());
                    setupArray[2] = Integer.parseInt(rowInput.getText().toString());
                    setupArray[3] = Integer.parseInt(overpressInput.getText().toString());
                    setupArray[4] = Integer.parseInt(deadliftInput.getText().toString());
                    setupArray[5] = Integer.parseInt(curlInput.getText().toString());

                    initData();
                    startActivity(new Intent(SetupActivity.this, MainActivity.class));
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
                    Message.message(context, "Woops, you need to enter weights for all lifts.");
                }

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

        repCalc.setTextColor(getResources().getColor(R.color.black));
        repCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repCalc.setBackgroundResource(R.drawable.button_pressed);
                repCalc.setTextColor(getResources().getColor(R.color.black));
                Uri uri = Uri.parse("http://www.naturalphysiques.com/18/one-rep-max-calculator");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

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
                        Intent intent = new Intent(SetupActivity.this, MainActivity.class);
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

    private void initData()  {

        if (myDbHelper.getProgStatus() == 1) {
            for (int i = 0; i < myDbHelper.getLiftsCount(); i++) {
                myDbHelper.initLiftData(myDbHelper.getLiftNames(i), setupArray[i]);
            }
            myDbHelper.initProgData();
            myDbHelper.initTheme();

        }

    }
}
