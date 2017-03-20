package com.app.registrumbeta.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	//Java values for database columns
    public static final String TABLE_TASKS      = "tasks";
    public static final String COLUMN_ID        = "_id";
    public static final String COLUMN_TASK      = "task";
	public static final String COLUMN_IMPORTANT = "important";
	public static final String COLUMN_QUICK     = "quick";
	public static final String COLUMN_CLEAR     = "clear";
	public static final String COLUMN_DONE      = "done";

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_TASKS + "( " 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_TASK + " text not null, " 
			+ COLUMN_IMPORTANT + " integer not null, "
			+ COLUMN_QUICK + " integer not null, "
			+ COLUMN_CLEAR + " integer not null, "
			+ COLUMN_DONE + " integer not null);";

	//constructor
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	//"creating database" sql-query 
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

}
