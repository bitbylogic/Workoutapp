package com.bitbylogic.android.workoutapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;


public class LiftCycleActivity extends Activity {
    private DatabaseAdapter dbHelper;
    private CheckBox squat_success;
    private CheckBox bench_success;
    private CheckBox row_success;
    private CheckBox overpress_success;
    private CheckBox deadlift_success;
    private CheckBox curl_success;
    private Button close_cycle_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dbHelper = new DatabaseAdapter(this);
        String theme = dbHelper.getTheme();
        switch (theme) {
            case "Blue Theme":
                setContentView(R.layout.activity_lift_cycle);
                close_cycle_button = (Button) findViewById(R.id.close_cycle_button);
                close_cycle_button.setBackgroundResource(R.drawable.blue_button);
                close_cycle_button.setTextColor(getResources().getColor(R.color.black));
                squat_success = (CheckBox) findViewById(R.id.squat_option);
                squat_success.setTextColor(getResources().getColor(R.color.theme_blue));
                bench_success = (CheckBox) findViewById(R.id.bench_option);
                bench_success.setTextColor(getResources().getColor(R.color.theme_blue));
                row_success = (CheckBox) findViewById(R.id.row_option);
                row_success.setTextColor(getResources().getColor(R.color.theme_blue));
                overpress_success = (CheckBox) findViewById(R.id.overpress_option);
                overpress_success.setTextColor(getResources().getColor(R.color.theme_blue));
                deadlift_success = (CheckBox) findViewById(R.id.deadlift_option);
                deadlift_success.setTextColor(getResources().getColor(R.color.theme_blue));
                curl_success = (CheckBox) findViewById(R.id.curl_option);
                curl_success.setTextColor(getResources().getColor(R.color.theme_blue));
                break;
            case "Mixed Theme":
                setContentView(R.layout.activity_lift_cycle_mixed);
                close_cycle_button = (Button) findViewById(R.id.close_cycle_button);
                close_cycle_button.setBackgroundResource(R.drawable.mix_button);
                close_cycle_button.setTextColor(getResources().getColor(R.color.black));
                squat_success = (CheckBox) findViewById(R.id.squat_option);
                squat_success.setTextColor(getResources().getColor(R.color.theme_red));
                bench_success = (CheckBox) findViewById(R.id.bench_option);
                bench_success.setTextColor(getResources().getColor(R.color.theme_red));
                row_success = (CheckBox) findViewById(R.id.row_option);
                row_success.setTextColor(getResources().getColor(R.color.theme_red));
                overpress_success = (CheckBox) findViewById(R.id.overpress_option);
                overpress_success.setTextColor(getResources().getColor(R.color.theme_red));
                deadlift_success = (CheckBox) findViewById(R.id.deadlift_option);
                deadlift_success.setTextColor(getResources().getColor(R.color.theme_red));
                curl_success = (CheckBox) findViewById(R.id.curl_option);
                curl_success.setTextColor(getResources().getColor(R.color.theme_red));
                break;
            case "Red Theme":
                setContentView(R.layout.activity_lift_cycle_red);
                close_cycle_button = (Button) findViewById(R.id.close_cycle_button);
                close_cycle_button.setBackgroundResource(R.drawable.red_button);
                close_cycle_button.setTextColor(getResources().getColor(R.color.black));
                squat_success = (CheckBox) findViewById(R.id.squat_option);
                squat_success.setTextColor(getResources().getColor(R.color.theme_red));
                bench_success = (CheckBox) findViewById(R.id.bench_option);
                bench_success.setTextColor(getResources().getColor(R.color.theme_red));
                row_success = (CheckBox) findViewById(R.id.row_option);
                row_success.setTextColor(getResources().getColor(R.color.theme_red));
                overpress_success = (CheckBox) findViewById(R.id.overpress_option);
                overpress_success.setTextColor(getResources().getColor(R.color.theme_red));
                deadlift_success = (CheckBox) findViewById(R.id.deadlift_option);
                deadlift_success.setTextColor(getResources().getColor(R.color.theme_red));
                curl_success = (CheckBox) findViewById(R.id.curl_option);
                curl_success.setTextColor(getResources().getColor(R.color.theme_red));
                break;
        }

        close_cycle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close_cycle_button.setBackgroundResource(R.drawable.button_pressed);
                close_cycle_button.setTextColor(getResources().getColor(R.color.black));
                saveConfirm();
            }
        });

    }

    private void saveConfirm() {

        StringBuilder successes = new StringBuilder();
        successes.append("You selected: \n");
        if(squat_success.isChecked())
            successes.append("Squat\n");
        if(bench_success.isChecked())
            successes.append("Bench Press\n");
        if(row_success.isChecked())
            successes.append("Row\n");
        if(overpress_success.isChecked())
            successes.append("Shoulder Press\n");
        if(deadlift_success.isChecked())
            successes.append("Stiff-Legged Deadlift\n");
        if(curl_success.isChecked())
            successes.append("Curl\n");

        boolean anySuccess = false;
        if (squat_success.isChecked() || bench_success.isChecked() || row_success.isChecked() || overpress_success.isChecked() ||
                deadlift_success.isChecked() || curl_success.isChecked())   {
            anySuccess = true;
        }

        if (!anySuccess)  {
            new AlertDialog.Builder(this).setTitle("Save Successes").setMessage("Let's keep the same weights next cycle, ok?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            int[] liftSuccesses = {0, 0, 0, 0, 0, 0};
                            if (squat_success.isChecked()) {
                                liftSuccesses[0] = 1;
                            }
                            if (bench_success.isChecked()) {
                                liftSuccesses[1] = 1;
                            }
                            if (row_success.isChecked()) {
                                liftSuccesses[2] = 1;
                            }
                            if (overpress_success.isChecked()) {
                                liftSuccesses[3] = 1;
                            }
                            if (deadlift_success.isChecked()) {
                                liftSuccesses[4] = 1;
                            }
                            if (curl_success.isChecked()) {
                                liftSuccesses[5] = 1;
                            }
                            dbHelper.updateLiftData(liftSuccesses);
                            Intent intent = new Intent(LiftCycleActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Exit me", true);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", null).show();
        } else {
            successes.append("\nWere all of these done for 12 reps with good form on Day 1?");
            new AlertDialog.Builder(this).setTitle("Save Successes").setMessage(successes.toString())
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            int[] liftSuccesses = {0, 0, 0, 0, 0, 0};
                            if (squat_success.isChecked()) {
                                liftSuccesses[0] = 1;
                            }
                            if (bench_success.isChecked()) {
                                liftSuccesses[1] = 1;
                            }
                            if (row_success.isChecked()) {
                                liftSuccesses[2] = 1;
                            }
                            if (overpress_success.isChecked()) {
                                liftSuccesses[3] = 1;
                            }
                            if (deadlift_success.isChecked()) {
                                liftSuccesses[4] = 1;
                            }
                            if (curl_success.isChecked()) {
                                liftSuccesses[5] = 1;
                            }
                            dbHelper.updateLiftData(liftSuccesses);
                            Intent intent = new Intent(LiftCycleActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Exit me", true);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", null).show();
        }

    }

}
