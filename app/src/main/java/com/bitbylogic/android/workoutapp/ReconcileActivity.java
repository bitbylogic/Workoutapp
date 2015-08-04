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
import android.widget.TextView;


public class ReconcileActivity extends Activity {
    private final Context context = this;
    private DatabaseAdapter myDbHelper;
    private Button update;
    private Button back;
    private TextView reconcileView;
    private EditText squatUpdate;
    private EditText benchUpdate;
    private EditText rowUpdate;
    private EditText overpressUpdate;
    private EditText deadliftUpdate;
    private EditText curlUpdate;
    private String theme;
    private final int[] updateArray = new int[6];

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
                setContentView(R.layout.activity_reconcile);
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Reconcile</font>"));
                update = (Button) findViewById(R.id.update_button);
                update.setBackgroundResource(R.drawable.blue_button);
                reconcileView = (TextView) findViewById(R.id.reconcile_title);
                reconcileView.setBackgroundResource(R.drawable.blue_button);
                back = (Button) findViewById(R.id.back_button);
                back.setBackgroundResource(R.drawable.blue_button);
                break;
            }
            case "Mixed Theme": {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red_blue);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red_blue);
                bar.setLogo(R.drawable.buff_andy_red_blue);
                setContentView(R.layout.activity_reconcile_mixed);
                setTheme(R.style.MixedActionBarTheme);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Reconcile</font>"));
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.MixedActionBarTheme);
                update = (Button) findViewById(R.id.update_button);
                update.setBackgroundResource(R.drawable.mix_button);
                reconcileView = (TextView) findViewById(R.id.reconcile_title);
                reconcileView.setBackgroundResource(R.drawable.mix_button);
                back = (Button) findViewById(R.id.back_button);
                back.setBackgroundResource(R.drawable.mix_button);
                break;
            }
            case "Red Theme": {
                ActionBar bar = getActionBar();
                Drawable d = getResources().getDrawable(R.drawable.barbell_actionbar_red);
                assert bar != null;
                bar.setBackgroundDrawable(d);
                bar.setIcon(R.drawable.buff_andy_red);
                bar.setLogo(R.drawable.buff_andy_red);
                setContentView(R.layout.activity_reconcile_red);
                bar.setTitle(Html.fromHtml("<font color='#ff0000'>Reconcile</font>"));
                //int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_red));
                setTheme(R.style.RedActionBarTheme);
                update = (Button) findViewById(R.id.update_button);
                update.setBackgroundResource(R.drawable.red_button);
                reconcileView = (TextView) findViewById(R.id.reconcile_title);
                reconcileView.setBackgroundResource(R.drawable.red_button);
                back = (Button) findViewById(R.id.back_button);
                back.setBackgroundResource(R.drawable.red_button);
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
                setContentView(R.layout.activity_reconcile);
                bar.setTitle(Html.fromHtml("<font color='#009cff'>Reconcile</font>"));
                //TextView yourTextView = (TextView) findViewById(titleId);
                //yourTextView.setTextColor(getResources().getColor(R.color.theme_blue));
                setTheme(R.style.CustomActionBarTheme);
                update = (Button) findViewById(R.id.update_button);
                update.setBackgroundResource(R.drawable.mix_button);
                reconcileView = (TextView) findViewById(R.id.reconcile_title);
                reconcileView.setBackgroundResource(R.drawable.mix_button);
                back = (Button) findViewById(R.id.back_button);
                back.setBackgroundResource(R.drawable.mix_button);
                break;
            }
        }
        reconcileView.setTextColor(getResources().getColor(R.color.black));
        update.setText("SAVE");
        update.setTextColor(getResources().getColor(R.color.black));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setBackgroundResource(R.drawable.button_pressed);
                update.setTextColor(getResources().getColor(R.color.black));
                squatUpdate = (EditText) findViewById(R.id.squat_update);
                benchUpdate = (EditText) findViewById(R.id.bench_update);
                rowUpdate = (EditText) findViewById(R.id.row_update);
                overpressUpdate = (EditText) findViewById(R.id.overpress_update);
                deadliftUpdate = (EditText) findViewById(R.id.deadlift_update);
                curlUpdate = (EditText) findViewById(R.id.curl_update);
                if (theme.equals("Red Theme"))  {
                    squatUpdate.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    benchUpdate.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    rowUpdate.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    overpressUpdate.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    deadliftUpdate.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                    curlUpdate.getBackground().setColorFilter(getResources().getColor(R.color.theme_red), PorterDuff.Mode.SRC_ATOP);
                }

                try {
                    updateArray[0] = Integer.parseInt(squatUpdate.getText().toString());
                    updateArray[1] = Integer.parseInt(benchUpdate.getText().toString());
                    updateArray[2] = Integer.parseInt(rowUpdate.getText().toString());
                    updateArray[3] = Integer.parseInt(overpressUpdate.getText().toString());
                    updateArray[4] = Integer.parseInt(deadliftUpdate.getText().toString());
                    updateArray[5] = Integer.parseInt(curlUpdate.getText().toString());
                    reconcileData();
                    startActivity(new Intent(ReconcileActivity.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                    switch (theme) {
                        case "Blue Theme":
                            update.setBackgroundResource(R.drawable.blue_button);
                            break;
                        case "Mixed Theme":
                            update.setBackgroundResource(R.drawable.mix_button);
                            break;
                        case "Red Theme":
                            update.setBackgroundResource(R.drawable.red_button);
                            break;
                    }
                    update.setTextColor(getResources().getColor(R.color.black));
                    Message.message(context, "Woops, you need to enter weights for all lifts.");
                }

            }
        });

        back.setText("CANCEL");
        back.setTextColor(getResources().getColor(R.color.black));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setBackgroundResource(R.drawable.button_pressed);
                back.setTextColor(getResources().getColor(R.color.black));
                startActivity(new Intent(ReconcileActivity.this, MainActivity.class));
                finish();
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

    private void reconcileData()  {

        for (int i = 0; i < myDbHelper.getLiftsCount(); i++) {
                myDbHelper.reconcileData(updateArray);
            }

    }

}
