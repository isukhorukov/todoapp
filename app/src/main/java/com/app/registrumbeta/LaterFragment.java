package com.app.registrumbeta;
import com.app.registrumbeta.data.TaskDBHelper;
import com.app.registrumbeta.data.TaskContract;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.String;

import android.widget.ListView;




/**
 * Created by Илья on 05.03.2017.
 */

public class LaterFragment extends Fragment implements OnAddTaskListener{

    public LaterFragment() {
        // Required empty public constructor
    }

	TaskAdapter mTaskAdapter;
	 
	// These indices are tied to TASKS_COLUMNS.  If TASKS_COLUMNS changes, these must change.
    static final int COL_TASK_ID = 0;
    static final int COL_TASK_NAME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_later, container, false);

		//Find the listView
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tasks);

        //Get DBHelper to read from database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //Query database to get any existing data
        Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID, 
							  TaskContract.TaskEntry.COLUMN_TASK,
							  TaskContract.TaskEntry.COLUMN_IMPORTANT,
							  TaskContract.TaskEntry.COLUMN_QUICK,
							  TaskContract.TaskEntry.COLUMN_CLEAR,
							  TaskContract.TaskEntry.COLUMN_DONE},
                null, null, null, null, null);

        //Create a new TaskAdapter and bind it to ListView
        mTaskAdapter = new TaskAdapter(getActivity(), cursor);
        listView.setAdapter(mTaskAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getFragmentManager();
                AddDialogFragment dialog = new AddDialogFragment();
                dialog.setTargetFragment(new LaterFragment(), 0);
                dialog.show(manager, "Task Name");
                onAddTaskSubmit("task");
            }
        });


        return rootView;
    }
    @Override
    public void onAddTaskSubmit(String inputTask) {
        //System.out.println(inputTask);
		//Get DBHelper to write to database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();

        //Put in the values within a ContentValues.
        ContentValues values = new ContentValues();
        values.clear();
        values.put(TaskContract.TaskEntry.COLUMN_TASK, inputTask);
		values.put(TaskContract.TaskEntry.COLUMN_IMPORTANT, 0);
		values.put(TaskContract.TaskEntry.COLUMN_QUICK, 0);
		values.put(TaskContract.TaskEntry.COLUMN_CLEAR, 0);
		values.put(TaskContract.TaskEntry.COLUMN_DONE, 0);

        //Insert the values into the Table for Tasks
        db.insertWithOnConflict(
			TaskContract.TaskEntry.TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_IGNORE);

                        //Query database again to get updated data
            Cursor cursor = db.query(TaskContract.TaskEntry.TABLE_NAME,
            new String[]{ TaskContract.TaskEntry._ID, 
							  TaskContract.TaskEntry.COLUMN_TASK,
							  TaskContract.TaskEntry.COLUMN_IMPORTANT,
							  TaskContract.TaskEntry.COLUMN_QUICK,
							  TaskContract.TaskEntry.COLUMN_CLEAR,
							  TaskContract.TaskEntry.COLUMN_DONE},
            null, null, null, null, null);

            //Swap old data with new data for display
            mTaskAdapter.swapCursor(cursor);
    }
}
