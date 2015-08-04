package com.bitbylogic.android.workoutapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DatabaseAdapter  {

    private final DatabaseHelper dbHelper;
    private final Context context;
    private final String[] dbLiftNames = {"Squat", "Bench Press", "Row", "Shoulder Press", "Stiff-Legged Deadlift", "Curl"};

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
        this.context = context;
    }

    public void initTheme()  {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.THEME, "Blue Theme");
        dbOb.insert(DatabaseHelper.THEME_TABLE_NAME, null, cv);
        dbOb.close();

    }

    public String getTheme()    {
        String theme = null;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.THEME};
            Cursor cursor = dbOb.query(DatabaseHelper.THEME_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = 1", null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.THEME);
                theme = cursor.getString(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting theme");
        }
        return theme;
    }

    public void setTheme(String theme)    {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.THEME, theme);
        dbOb.update(DatabaseHelper.THEME_TABLE_NAME, cv, DatabaseHelper.ID + " =" + 1, null);
        dbOb.close();
    }

    public void initLiftData(String lft, int swt) {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int wu1 = (int)(5 * Math.round((swt/4.0)/5.0));
        int wu2 = (int)(5 * Math.round((swt/2.0)/5.0));
        int d1 = (int)(5 * Math.round((swt)/5.0));
        int d2 = (int)(5 * Math.round((swt*.9)/5.0));
        int d3 = (int)(5 * Math.round((swt*.8)/5.0));
        if (wu1 < 45)   {
            wu1 = 45;
            if (swt < 45)   {
                wu1 = swt;
            }
        }
        if (wu2 < 45)   {
            wu2 = 45;
            if (swt < 45)   {
                wu2 = swt;
            }
        }
        if (d1 < 45)    {
            d1 = 45;
            if (swt < 45)   {
                d1 = swt;
            }
        }
        if (d2 < 45)    {
            d2 = 45;
            if (swt < 45)   {
                d2 = swt;
            }
        }
        if (d3 < 45)    {
            d3 = 45;
            if (swt < 45)   {
                d3 = swt;
            }
        }
        if (lft.equals(getLiftNames(0)) || lft.equals(getLiftNames(1)) || lft.equals(getLiftNames(2))) {
            cv.put(DatabaseHelper.LIFT, lft);
            cv.put(DatabaseHelper.STARTWEIGHT, swt);
            cv.put(DatabaseHelper.NOWWEIGHT, swt);
            cv.put(DatabaseHelper.WU1WEIGHT, wu1);
            cv.put(DatabaseHelper.WU2WEIGHT, wu2);
            cv.put(DatabaseHelper.DAY1, d1);
            cv.put(DatabaseHelper.DAY2, d2);
            cv.put(DatabaseHelper.DAY3, d3);
            cv.put(DatabaseHelper.LIFTCYCLE, 1);

        } else {
            cv.put(DatabaseHelper.LIFT, lft);
            cv.put(DatabaseHelper.STARTWEIGHT, swt);
            cv.put(DatabaseHelper.NOWWEIGHT, swt);
            cv.put(DatabaseHelper.DAY1, d1);
            cv.put(DatabaseHelper.DAY2, d2);
            cv.put(DatabaseHelper.DAY3, d3);
            cv.put(DatabaseHelper.LIFTCYCLE, 1);
        }
        dbOb.insert(DatabaseHelper.LIFT_TABLE_NAME, null, cv);
        dbOb.close();
    }

    public void initProgData() {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.PROGCYCLE, 1);
        cv.put(DatabaseHelper.WEEK, 1);
        cv.put(DatabaseHelper.DAY, 1);
        cv.put(DatabaseHelper.SET, 1);
        cv.put(DatabaseHelper.LIFTNUM, 0);
        cv.put(DatabaseHelper.NEW_PROG, 0);
        dbOb.insert(DatabaseHelper.PROG_TABLE_NAME, null, cv);
        dbOb.close();
    }

    static class DatabaseHelper extends SQLiteOpenHelper   {
        private static final int DATABASE_VERSION = 3;
        private static final String ID = "_id";
        private static final String LIFT = "lift";
        private static final String STARTWEIGHT = "startWeight";
        private static final String NOWWEIGHT = "nowWeight";
        private static final String WU1WEIGHT = "wu1Weight";
        private static final String WU2WEIGHT = "wu2Weight";
        private static final String DAY1 = "day1";
        private static final String DAY2 = "day2";
        private static final String DAY3 = "day3";
        private static final String THEME = "theme";
        private static final String PROGCYCLE = "progCycle";
        private static final String LIFTCYCLE = "liftCycle";
        private static final String LIFTNUM = "liftnum";
        private static final String WEEK = "week";
        private static final String DAY = "day";
        private static final String SET = "setnum";
        private static final String DATABASE_NAME = "workout_data";
        private static final String LIFT_TABLE_NAME = "lift_table";
        private static final String PROG_TABLE_NAME = "prog_table";
        private static final String THEME_TABLE_NAME = "theme_table";
        private static final String NEW_PROG = "is_new_prog";
        // --Commented out by Inspection (8/4/15, 10:32 AM):private int isNewProg = 1;
        private final String CREATE_LIFT_QUERY = "CREATE TABLE " + LIFT_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LIFT + " TEXT, " + STARTWEIGHT + " INTEGER, " + NOWWEIGHT + " INTEGER, " + WU1WEIGHT + " INTEGER, " +
                WU2WEIGHT + " INTEGER, " + DAY1 + " INTEGER, " + DAY2 + " INTEGER, " + DAY3 + " INTEGER, " + LIFTCYCLE + " INTEGER);";
        private final String CREATE_PROG_QUERY = "CREATE TABLE " + PROG_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROGCYCLE + " INTEGER, " + WEEK + " INTEGER, " + DAY + " INTEGER, " + SET + " INTEGER, "
                + LIFTNUM + " INTEGER, " + NEW_PROG + " INTEGER);";
        private final String CREATE_THEME_QUERY = "CREATE TABLE " + THEME_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + THEME + " TEXT);";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)   {
            try{
                db.execSQL(CREATE_LIFT_QUERY);
                db.execSQL(CREATE_PROG_QUERY);
                db.execSQL(CREATE_THEME_QUERY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
            db.execSQL("DROP TABLE IF EXISTS " + LIFT_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + PROG_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + THEME_TABLE_NAME);
            onCreate(db);
        }

    }

    public void setProgStatus()    {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NEW_PROG, 1);
        dbOb.update(DatabaseHelper.PROG_TABLE_NAME, cv, DatabaseHelper.ID + " =" + 1, null);
        dbOb.close();
    }

    public int getProgStatus()    {
        int status = 1;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.NEW_PROG};
            Cursor cursor = dbOb.query(DatabaseHelper.PROG_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = 1", null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.NEW_PROG);
                status = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting program data");
        }
        return status;         // change to db select later
    }

// --Commented out by Inspection START (8/4/15, 10:26 AM):
//    public void setLiftData(int i, int n)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.NOWWEIGHT, n);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + i, null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:26 AM)

    public int getLiftData(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.NOWWEIGHT};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.NOWWEIGHT);
                value = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting lift data");
        }
        return value;         // change to db select later
    }

// --Commented out by Inspection START (8/4/15, 10:26 AM):
//    public void setLiftStart(int i, int n)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.STARTWEIGHT, n);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + i, null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:26 AM)

    public int getLiftStart(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.STARTWEIGHT};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.STARTWEIGHT);
                value = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting lift data");
        }
        return value;         // change to db select later
    }

    public int getWeek()    {
        int value = 1;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.WEEK};
            Cursor cursor = dbOb.query(DatabaseHelper.PROG_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + 1, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.WEEK);
                value = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting week number");
        }
        return value;         // change to db select later
    }

    public int getDay()    {
        int value = 1;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.DAY};
            Cursor cursor = dbOb.query(DatabaseHelper.PROG_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + 1, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.DAY);
                value = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting day number");
        }
        return value;         // change to db select later
    }

    public int getSet()    {
        int value = 1;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.SET};
            Cursor cursor = dbOb.query(DatabaseHelper.PROG_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + 1, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.SET);
                value = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting set number");
        }
        return value;         // change to db select later
    }

    public int getLiftNum()    {
        int value = 1;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.LIFTNUM};
            Cursor cursor = dbOb.query(DatabaseHelper.PROG_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + 1, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.LIFTNUM);
                value = cursor.getInt(colIndex);
            }
            dbOb.close();
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting lift number");
        }
        return value;         // change to db select later
    }

    private int getLiftCycle(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.LIFTCYCLE};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.LIFTCYCLE);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting lift cycle data");
        }
        return value;         // change to db select later
    }

    public int getWu1(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.WU1WEIGHT};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.WU1WEIGHT);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting warmup1 data");
        }
        return value;         // change to db select later
    }

    public int getWu2(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.WU2WEIGHT};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.WU2WEIGHT);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting warmup1 data");
        }
        return value;         // change to db select later
    }

    public int getDay1(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.DAY1};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.DAY1);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting Day1 data");
        }
        return value;         // change to db select later
    }

    public int getDay2(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.DAY2};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.DAY2);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting Day2 data");
        }
        return value;         // change to db select later
    }

    public int getDay3(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.DAY3};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.DAY3);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting Day3 data");
        }
        return value;         // change to db select later
    }

    private int getNowWt(int i)    {
        int value = 0;
        try {
            SQLiteDatabase dbOb = dbHelper.getReadableDatabase();
            String[] columns = {DatabaseHelper.NOWWEIGHT};
            Cursor cursor = dbOb.query(DatabaseHelper.LIFT_TABLE_NAME, columns, " " + DatabaseHelper.ID + " = " + i, null,
                    null, null, null);
            while (cursor.moveToNext()) {
                int colIndex = cursor.getColumnIndex(DatabaseHelper.NOWWEIGHT);
                value = cursor.getInt(colIndex);
            }
            cursor.close();
        } catch (Exception e)   {
            Message.message(context, "error getting now wt data");
        }
        return value;         // change to db select later
    }

    public void setDay(int d)    {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.DAY, d);
        dbOb.update(DatabaseHelper.PROG_TABLE_NAME, cv, DatabaseHelper.ID + " = " + 1, null);
        dbOb.close();
    }

    public void setWeek(int w)    {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.WEEK, w);
        dbOb.update(DatabaseHelper.PROG_TABLE_NAME, cv, DatabaseHelper.ID + " = " + 1, null);
        dbOb.close();
    }

    public void setSet(int s)    {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.SET, s);
        dbOb.update(DatabaseHelper.PROG_TABLE_NAME, cv, DatabaseHelper.ID + " = " + 1, null);
        dbOb.close();
    }

    public void setLiftNum(int l)    {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.LIFTNUM, l);
        dbOb.update(DatabaseHelper.PROG_TABLE_NAME, cv, DatabaseHelper.ID + " = " + 1, null);
        dbOb.close();
    }

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setLiftCycle(int[] liftCyc)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        for (int i = 0; i < liftCyc.length; i++) {
//            cv.put(DatabaseHelper.LIFTCYCLE, (getLiftCycle(i+1) + liftCyc[i]));
//            dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (i+1), null);
//        }
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setNowWt(int l, int wt)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.NOWWEIGHT, wt);
//            dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (l + 1), null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setDay1(int l, int wt)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.DAY1, wt);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (l + 1), null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setDay2(int l, int wt)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.DAY2, wt);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (l + 1), null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setDay3(int l, int wt)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.DAY3, wt);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (l + 1), null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setWU1(int l, int wt)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.WU1WEIGHT, wt);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (l + 1), null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

// --Commented out by Inspection START (8/4/15, 10:27 AM):
//    public void setWU2(int l, int wt)    {
//        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.WU2WEIGHT, wt);
//        dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (l + 1), null);
//        dbOb.close();
//    }
// --Commented out by Inspection STOP (8/4/15, 10:27 AM)

    public void updateLiftData(int[] success) {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv2 = new ContentValues();
        for (int i = 1; i < 7; i++) {
            cv2.put(DatabaseHelper.LIFTCYCLE, (getLiftCycle(i) + success[i-1]));
            dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv2, DatabaseHelper.ID + " = " + (i), null);
            int nowWt = getNowWt(i);
            int nwt = (int) (5 * Math.round((nowWt + (nowWt * (getLiftCycle(i) - 1) / 10.0)) / 5.0));
            int wu1 = (int) (5 * Math.round((nwt / 4.0) / 5.0));
            int wu2 = (int) (5 * Math.round((nwt / 2.0) / 5.0));
            int d1 = (int) (5 * Math.round((nwt) / 5.0));
            int d2 = (int) (5 * Math.round((nwt * .9) / 5.0));
            int d3 = (int) (5 * Math.round((nwt * .8) / 5.0));

            if (wu1 < 45) {
                wu1 = 45;
                if (nowWt < 45)   {
                    wu1 = nowWt;
                }
            }
            if (wu2 < 45) {
                wu2 = 45;
                if (nowWt < 45)   {
                    wu2 = nowWt;
                }
            }
            if (d1 < 45) {
                d1 = 45;
                if (nowWt < 45)   {
                    d1 = nowWt;
                }
            }
            if (d2 < 45) {
                d2 = 45;
                if (nowWt < 45)   {
                    d2 = nowWt;
                }
            }
            if (d3 < 45) {
                d3 = 45;
                if (nowWt < 45)   {
                    d3 = nowWt;
                }
            }

            if (i < 4) {
                cv.put(DatabaseHelper.NOWWEIGHT, nwt);
                cv.put(DatabaseHelper.WU1WEIGHT, wu1);
                cv.put(DatabaseHelper.WU2WEIGHT, wu2);
                cv.put(DatabaseHelper.DAY1, d1);
                cv.put(DatabaseHelper.DAY2, d2);
                cv.put(DatabaseHelper.DAY3, d3);

            } else {
                cv.put(DatabaseHelper.NOWWEIGHT, nwt);
                cv.put(DatabaseHelper.DAY1, d1);
                cv.put(DatabaseHelper.DAY2, d2);
                cv.put(DatabaseHelper.DAY3, d3);
            }
            dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (i), null);
        }
    }

    public void reconcileData(int[] values) {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (int i = 0; i < 6; i++) {
            int nowWt = values[i];
            int nwt = (int) (5 * Math.round((nowWt + (nowWt * (getLiftCycle(i) - 1) / 10.0)) / 5.0));
            int wu1 = (int) (5 * Math.round((nwt / 4.0) / 5.0));
            int wu2 = (int) (5 * Math.round((nwt / 2.0) / 5.0));
            int d1 = (int) (5 * Math.round((nwt) / 5.0));
            int d2 = (int) (5 * Math.round((nwt * .9) / 5.0));
            int d3 = (int) (5 * Math.round((nwt * .8) / 5.0));

            if (wu1 < 45) {
                wu1 = 45;
                if (nowWt < 45)   {
                    wu1 = nowWt;
                }
            }
            if (wu2 < 45) {
                wu2 = 45;
                if (nowWt < 45)   {
                    wu2 = nowWt;
                }
            }
            if (d1 < 45) {
                d1 = 45;
                if (nowWt < 45)   {
                    d1 = nowWt;
                }
            }
            if (d2 < 45) {
                d2 = 45;
                if (nowWt < 45)   {
                    d2 = nowWt;
                }
            }
            if (d3 < 45) {
                d3 = 45;
                if (nowWt < 45)   {
                    d3 = nowWt;
                }
            }

            if (i < 4) {
                cv.put(DatabaseHelper.NOWWEIGHT, nwt);
                cv.put(DatabaseHelper.WU1WEIGHT, wu1);
                cv.put(DatabaseHelper.WU2WEIGHT, wu2);
                cv.put(DatabaseHelper.DAY1, d1);
                cv.put(DatabaseHelper.DAY2, d2);
                cv.put(DatabaseHelper.DAY3, d3);

            } else {
                cv.put(DatabaseHelper.NOWWEIGHT, nwt);
                cv.put(DatabaseHelper.DAY1, d1);
                cv.put(DatabaseHelper.DAY2, d2);
                cv.put(DatabaseHelper.DAY3, d3);
            }
            dbOb.update(DatabaseHelper.LIFT_TABLE_NAME, cv, DatabaseHelper.ID + " = " + (i + 1), null);
        }
    }



    public String getLiftNames(int l) {
        return dbLiftNames[l];
    }

    public int getLiftsCount()  {
        return dbLiftNames.length;
    }

    public void resetData()   {
        SQLiteDatabase dbOb = dbHelper.getWritableDatabase();
        dbOb.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.LIFT_TABLE_NAME);
        dbOb.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.PROG_TABLE_NAME);
        dbOb.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.THEME_TABLE_NAME);
        dbHelper.onCreate(dbOb);
    }


}
