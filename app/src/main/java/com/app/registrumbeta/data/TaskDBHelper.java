package com.app.registrumbeta.data;
import com.app.registrumbeta.data.TaskContract.TaskEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME  = "task.db";
    private static final int DATABASE_VERSION = 1;
/*
    private static TaskDBHelper sInstance;

    public static synchronized TaskDBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new TaskDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }
*/
    public TaskDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASKS_TABLE =
                "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" +
                        TaskEntry._ID + " INTEGER PRIMARY KEY, " +
                        TaskEntry.COLUMN_TASK + " TEXT NOT NULL, " +
						TaskEntry.COLUMN_IMPORTANT + " INTEGER NOT NULL, " +
						TaskEntry.COLUMN_QUICK + " INTEGER NOT NULL, " +
						TaskEntry.COLUMN_CLEAR + " INTEGER NOT NULL, " +
						TaskEntry.COLUMN_DONE + " INTEGER NOT NULL" +
                        " );";
        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME);
        onCreate(db);
    }
}