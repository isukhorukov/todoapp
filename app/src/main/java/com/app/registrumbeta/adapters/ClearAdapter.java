package com.app.registrumbeta.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.registrumbeta.FiltersFragment;
import com.app.registrumbeta.CriteriaFragment;
import com.app.registrumbeta.LaterFragment;
import com.app.registrumbeta.R;
import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 19.03.2017.
 */

public class ClearAdapter extends CursorAdapter {

    private static Context context;
    TaskDBHelper helper;

    public ClearAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
        helper = new TaskDBHelper(context);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_clear, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find Views to populate in inflated template
        TextView textView = (TextView) view.findViewById(R.id.list_item_criteria_textview);
        CheckBox checkBoxClear = (CheckBox) view.findViewById(R.id.checkbox_clear);
        //  Button delete_button = (Button) view.findViewById(R.id.list_item_task_delete_button);

        // Extract properties from cursor
        final String id = cursor.getString(CriteriaFragment.COL_TASK_ID);
        final String task = cursor.getString(CriteriaFragment.COL_TASK_NAME);

        // Populate views with extracted properties
        textView.setText(task);

        //trick for making chekbox automaticly checked
        checkBoxClear.setChecked(cursor.getInt(CriteriaFragment.COL_TASK_CLEAR) == 0 ? false : true);

        //check for make done
        checkBoxClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                //Create a SQL command for deleting a particular ID.
                //boolean checked = isChecked();
                if(checked) {
                    ContentValues values = new ContentValues();
                    values.clear();
                    values.put(TaskContract.TaskEntry.COLUMN_CLEAR, 1);
                    SQLiteDatabase sqlDB = helper.getWritableDatabase();
                    sqlDB.update(TaskContract.TaskEntry.TABLE_NAME, values, TaskContract.TaskEntry._ID + " = ?",
                            new String[] { id });
                    /*
                    //Query database for updated data
                    Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                            new String[]{ TaskContract.TaskEntry._ID,
                                    TaskContract.TaskEntry.COLUMN_TASK,
                                    TaskContract.TaskEntry.COLUMN_IMPORTANT,
                                    TaskContract.TaskEntry.COLUMN_QUICK,
                                    TaskContract.TaskEntry.COLUMN_CLEAR,
                                    TaskContract.TaskEntry.COLUMN_DONE},
                            null,null,null,null,null);
                    //Instance method with TaskAdapter so no need to use adapter.swapCursor()
                    CriteriaFragment.cTaskAdapter.swapCursor(cursor); // update data for Ctritera
                     */

                }   else {
                    ContentValues values = new ContentValues();
                    values.clear();
                    values.put(TaskContract.TaskEntry.COLUMN_CLEAR, 0);
                    SQLiteDatabase sqlDB = helper.getWritableDatabase();
                    sqlDB.update(TaskContract.TaskEntry.TABLE_NAME, values, TaskContract.TaskEntry._ID + " = ?",
                            new String[] { id });
                    /*
                    //Query database for updated data
                    Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                            new String[]{ TaskContract.TaskEntry._ID,
                                    TaskContract.TaskEntry.COLUMN_TASK,
                                    TaskContract.TaskEntry.COLUMN_IMPORTANT,
                                    TaskContract.TaskEntry.COLUMN_QUICK,
                                    TaskContract.TaskEntry.COLUMN_CLEAR,
                                    TaskContract.TaskEntry.COLUMN_DONE},
                            null,null,null,null,null);
                    //Instance method with TaskAdapter so no need to use adapter.swapCursor()
                    CriteriaFragment.cTaskAdapter.swapCursor(cursor); // update data for Ctritera
                    */
                }
                //System.out.println("checked");
            }
        });
    }
}
