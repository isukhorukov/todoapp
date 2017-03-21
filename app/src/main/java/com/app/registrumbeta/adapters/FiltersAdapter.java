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

import com.app.registrumbeta.CompleteFragment;
import com.app.registrumbeta.LaterFragment;
import com.app.registrumbeta.CriteriaFragment;
import com.app.registrumbeta.FiltersFragment;
import com.app.registrumbeta.R;
import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 21.03.2017.
 */

public class FiltersAdapter extends CursorAdapter {
    private static Context context;
    public static TaskDBHelper helper;

    public FiltersAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
        helper = new TaskDBHelper(context);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_filters, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find Views to populate in inflated template
        TextView textView = (TextView) view.findViewById(R.id.list_item_filters_textview);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_done);



        // Extract properties from cursor
        final String id = cursor.getString(FiltersFragment.COL_TASK_ID);
        final String task = cursor.getString(FiltersFragment.COL_TASK_NAME);

        // Populate views with extracted properties
        textView.setText(task);
    //    checkBox.setChecked(cursor.getInt(CriteriaFragment.COL_TASK_IMPORTANT) == 0 ? false : true);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                //Create a SQL command for deleting a particular ID.
                //boolean checked = isChecked();
                if(checked) {
                    ContentValues values = new ContentValues();
                    values.clear();
                    values.put(TaskContract.TaskEntry.COLUMN_DONE, 1);
                    SQLiteDatabase sqlDB = helper.getWritableDatabase();
                    sqlDB.update(TaskContract.TaskEntry.TABLE_NAME, values, TaskContract.TaskEntry._ID + " = ?",
                            new String[] { id });

                    //Query database for updated data
                    Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                            new String[]{ TaskContract.TaskEntry._ID,
                                    TaskContract.TaskEntry.COLUMN_TASK,
                                    TaskContract.TaskEntry.COLUMN_IMPORTANT,
                                    TaskContract.TaskEntry.COLUMN_QUICK,
                                    TaskContract.TaskEntry.COLUMN_CLEAR,
                                    TaskContract.TaskEntry.COLUMN_DONE},
                            TaskContract.TaskEntry.COLUMN_DONE + " = ?", new String[] { "0" } , null, null, null);
                    //Instance method with TaskAdapter so no need to use adapter.swapCursor()
                    FiltersFragment.fTaskAdapter.swapCursor(cursor); // update data for Ctritera
                    LaterFragment.mTaskAdapter.swapCursor(cursor);
                    CriteriaFragment.cTaskAdapter.swapCursor(cursor);

                    //cursor.close();
                    cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                            new String[]{ TaskContract.TaskEntry._ID,
                                    TaskContract.TaskEntry.COLUMN_TASK,
                                    TaskContract.TaskEntry.COLUMN_IMPORTANT,
                                    TaskContract.TaskEntry.COLUMN_QUICK,
                                    TaskContract.TaskEntry.COLUMN_CLEAR,
                                    TaskContract.TaskEntry.COLUMN_DONE},
                            TaskContract.TaskEntry.COLUMN_DONE + " = ?", new String[] { "1" } , null, null, null);
                    CompleteFragment.compTaskAdapter.swapCursor(cursor);
                }   else {

                }
                //System.out.println("checked");
            }
        });


    }
}
