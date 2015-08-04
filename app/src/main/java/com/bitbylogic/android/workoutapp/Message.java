package com.bitbylogic.android.workoutapp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

class Message {

    public static void message(Context context, String message) {

        String theme;
        DatabaseAdapter myDbHelper;
        myDbHelper = new DatabaseAdapter(context);
        theme = myDbHelper.getTheme();

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        View view = toast.getView();
        switch (theme) {
            case "Blue Theme":
                view.setBackgroundResource(R.drawable.bg_toast_frame);
                break;
            case "Mixed Theme":
                view.setBackgroundResource(R.drawable.bg_toast_frame);
                break;
            case "Red Theme":
                view.setBackgroundResource(R.drawable.bg_toast_frame_red);
                break;
        }
        toast.show();

    }


}
