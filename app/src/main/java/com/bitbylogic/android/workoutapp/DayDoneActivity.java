package com.bitbylogic.android.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class DayDoneActivity extends Activity {
    private Button close_day_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        DatabaseAdapter dbHelper = new DatabaseAdapter(this);
        String theme = dbHelper.getTheme();
        switch (theme) {
            case "Blue Theme":
                setContentView(R.layout.activity_day_done);
                close_day_button = (Button) findViewById(R.id.close_day_button);
                close_day_button.setBackgroundResource(R.drawable.blue_button);
                break;
            case "Mixed Theme":
                setContentView(R.layout.activity_day_done_mixed);
                close_day_button = (Button) findViewById(R.id.close_day_button);
                close_day_button.setBackgroundResource(R.drawable.mix_button);
                break;
            case "Red Theme":
                setContentView(R.layout.activity_day_done_red);
                close_day_button = (Button) findViewById(R.id.close_day_button);
                close_day_button.setBackgroundResource(R.drawable.red_button);
                break;
        }

        close_day_button.setTextColor(getResources().getColor(R.color.black));
        close_day_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close_day_button.setBackgroundResource(R.drawable.button_pressed);
                close_day_button.setTextColor(getResources().getColor(R.color.black));
                Intent intent = new Intent(DayDoneActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Exit me", true);
                startActivity(intent);
                AppExit();
                finish();
            }
        });

    }

    private void AppExit()
    {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
