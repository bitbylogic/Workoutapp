package com.bitbylogic.android.workoutapp;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;


public class ProgressActivity extends Activity {

    private final String[] lifts = {"Squat", "Bench Press", "Rows", "Shoulder Press", "DeadLifts", "Curls"};
    private final String[] labels = {"Exercises", "Then", "Now"};
    private final TextView[] lblTxtViews = new TextView[3];
    private int viewCount = 0;
    private final int[] stVal = new int[lifts.length + 1];
    private final int[] nowVal = new int[lifts.length + 1];
    private Button return_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        DatabaseAdapter dbHelp = new DatabaseAdapter(this);
        String theme = dbHelp.getTheme();
        switch (theme) {
            case "Blue Theme":
                setContentView(R.layout.activity_progress);
                return_button = (Button) findViewById(R.id.return_button);
                return_button.setBackground(this.getResources().getDrawable(R.drawable.blue_button));
                break;
            case "Mixed Theme":
                setContentView(R.layout.activity_progress_mixed);
                return_button = (Button) findViewById(R.id.return_button);
                return_button.setBackground(this.getResources().getDrawable(R.drawable.mix_button));
                break;
            case "Red Theme":
                setContentView(R.layout.activity_progress_red);
                return_button = (Button) findViewById(R.id.return_button);
                return_button.setBackground(this.getResources().getDrawable(R.drawable.red_button));
                break;
        }

        TableRow progLblRow = (TableRow) findViewById(R.id.prog_row);
        for (int i = 0; i < progLblRow.getChildCount(); i++) {
            if (progLblRow.getChildAt(i).getClass() == TextView.class) {
                lblTxtViews[viewCount] = (TextView)progLblRow.getChildAt(i);
                lblTxtViews[viewCount].setText(labels[viewCount]);
                viewCount++;
            }
        }
        viewCount = 0;

        for (int i = 1; i < 7; i++) {
            stVal[i] = dbHelp.getLiftStart(i);
            nowVal[i] = dbHelp.getLiftData(i);
        }
        lblTxtViews[0] = (TextView)findViewById(R.id.squat_name);
        lblTxtViews[0].setText(lifts[0]);
        lblTxtViews[1] = (TextView)findViewById(R.id.squat_then);
        lblTxtViews[2] = (TextView)findViewById(R.id.squat_now);
        if (Integer.toString(stVal[1]).length() < 3)    {
            lblTxtViews[1].setText("  " + String.valueOf(stVal[1])+" lbs");
        } else {
            lblTxtViews[1].setText(String.valueOf(stVal[1])+" lbs");
        }
        if (Integer.toString(nowVal[1]).length() < 3)    {
            lblTxtViews[2].setText("  " + String.valueOf(nowVal[1]) + " lbs");
        } else {
            lblTxtViews[2].setText(String.valueOf(nowVal[1]) + " lbs");
        }

        lblTxtViews[0] = (TextView)findViewById(R.id.bench_name);
        lblTxtViews[0].setText(lifts[1]);
        lblTxtViews[1] = (TextView)findViewById(R.id.bench_then);
        lblTxtViews[2] = (TextView)findViewById(R.id.bench_now);
        if (Integer.toString(stVal[2]).length() < 3)    {
            lblTxtViews[1].setText("  " + String.valueOf(stVal[2])+" lbs");
        } else {
            lblTxtViews[1].setText(String.valueOf(stVal[2])+" lbs");
        }
        if (Integer.toString(nowVal[2]).length() < 3)    {
            lblTxtViews[2].setText("  " + String.valueOf(nowVal[2]) + " lbs");
        } else {
            lblTxtViews[2].setText(String.valueOf(nowVal[2]) + " lbs");
        }

        lblTxtViews[0] = (TextView)findViewById(R.id.rows_name);
        lblTxtViews[0].setText(lifts[2]);
        lblTxtViews[1] = (TextView)findViewById(R.id.rows_then);
        lblTxtViews[2] = (TextView)findViewById(R.id.rows_now);
        if (Integer.toString(stVal[3]).length() < 3)    {
            lblTxtViews[1].setText("  " + String.valueOf(stVal[3])+" lbs");
        } else {
            lblTxtViews[1].setText(String.valueOf(stVal[3])+" lbs");
        }
        if (Integer.toString(nowVal[3]).length() < 3)    {
            lblTxtViews[2].setText("  " + String.valueOf(nowVal[3]) + " lbs");
        } else {
            lblTxtViews[2].setText(String.valueOf(nowVal[3]) + " lbs");
        }

        lblTxtViews[0] = (TextView)findViewById(R.id.press_name);
        lblTxtViews[0].setText(lifts[3]);
        lblTxtViews[1] = (TextView)findViewById(R.id.press_then);
        lblTxtViews[2] = (TextView)findViewById(R.id.press_now);
        if (Integer.toString(stVal[4]).length() < 3)    {
            lblTxtViews[1].setText("  " + String.valueOf(stVal[4])+" lbs");
        } else {
            lblTxtViews[1].setText(String.valueOf(stVal[4])+" lbs");
        }
        if (Integer.toString(nowVal[4]).length() < 3)    {
            lblTxtViews[2].setText("  " + String.valueOf(nowVal[4]) + " lbs");
        } else {
            lblTxtViews[2].setText(String.valueOf(nowVal[4]) + " lbs");
        }

        lblTxtViews[0] = (TextView)findViewById(R.id.stiff_name);
        lblTxtViews[0].setText(lifts[4]);
        lblTxtViews[1] = (TextView)findViewById(R.id.stiff_then);
        lblTxtViews[2] = (TextView)findViewById(R.id.stiff_now);
        if (Integer.toString(stVal[5]).length() < 3)    {
            lblTxtViews[1].setText("  " + String.valueOf(stVal[5])+" lbs");
        } else {
            lblTxtViews[1].setText(String.valueOf(stVal[5])+" lbs");
        }
        if (Integer.toString(nowVal[5]).length() < 3)    {
            lblTxtViews[2].setText("  " + String.valueOf(nowVal[5]) + " lbs");
        } else {
            lblTxtViews[2].setText(String.valueOf(nowVal[5]) + " lbs");
        }

        lblTxtViews[0] = (TextView)findViewById(R.id.curls_name);
        lblTxtViews[0].setText(lifts[5]);
        lblTxtViews[1] = (TextView)findViewById(R.id.curls_then);
        lblTxtViews[2] = (TextView)findViewById(R.id.curls_now);
        if (Integer.toString(stVal[6]).length() < 3)    {
            lblTxtViews[1].setText("  " + String.valueOf(stVal[6])+" lbs");
        } else {
            lblTxtViews[1].setText(String.valueOf(stVal[6])+" lbs");
        }
        if (Integer.toString(nowVal[6]).length() < 3)    {
            lblTxtViews[2].setText("  " + String.valueOf(nowVal[6]) + " lbs");
        } else {
            lblTxtViews[2].setText(String.valueOf(nowVal[6]) + " lbs");
        }

        return_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.button_pressed);
                finish();
            }
        });
    }

}
