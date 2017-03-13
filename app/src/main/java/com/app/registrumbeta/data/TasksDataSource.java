package com.app.registrumbeta.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TasksDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
									MySQLiteHelper.COLUMN_TASK,
									MySQLiteHelper.COLUMN_IMPORTANT,
									MySQLiteHelper.COLUMN_QUICK,
									MySQLiteHelper.COLUMN_CLEAR,
									MySQLiteHelper.COLUMN_DONE };

    public TasksDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
	
	//put data in database using String from user. String inputs by user in DialogFragment.
	//all boolean fields is false by default
    public Task createTask(String task) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TASK, task);
		values.put(MySQLiteHelper.COLUMN_IMPORTANT, 0);
		values.put(MySQLiteHelper.COLUMN_CLEAR, 0);
		values.put(MySQLiteHelper.COLUMN_QUICK, 0);
		values.put(MySQLiteHelper.COLUMN_DONE, 0);
		
		//get database-row id 
        long insertId = database.insert(MySQLiteHelper.TABLE_TASKS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }
	
	//delete task from database by Java-object task-name by its id
    public void deleteTask(Task task) {
        long id = task.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_TASKS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

	//convert all database rows to List of Java objects and return it
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
		
        // make sure to close the cursor
        cursor.close();
        return tasks;
    }
	
	//database-object to Java-object converter 
    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTask(cursor.getString(1));
        task.setImportantCriterion(cursor.getInt(2));
        task.setQuickCriterion(cursor.getInt(3));
        task.setClearCriterion(cursor.getInt(4));
        task.setDoneStatus(cursor.getInt(5));
        return task;
    }
}
