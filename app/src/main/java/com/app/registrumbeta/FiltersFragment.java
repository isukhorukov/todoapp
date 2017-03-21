package com.app.registrumbeta;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.app.registrumbeta.adapters.FiltersAdapter;

import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 05.03.2017.
 */

public class FiltersFragment extends Fragment {

    public FiltersFragment() {

    }

    //numbers of same columns for binding view
    static public final int COL_TASK_ID = 0;
    static public final int COL_TASK_NAME = 1;
    static public final int COL_TASK_IMPORTANT = 2;
    static public final int COL_TASK_DONE = 5;

    public static FiltersAdapter fTaskAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filters, container, false);

        //Find the listView
        ListView listView = (ListView) rootView.findViewById(R.id.listview_filters);

        //Get DBHelper to read from database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //Query database to get any existing data
        /*
        Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COLUMN_TASK,
                        TaskContract.TaskEntry.COLUMN_IMPORTANT,
                        TaskContract.TaskEntry.COLUMN_QUICK,
                        TaskContract.TaskEntry.COLUMN_CLEAR,
                        TaskContract.TaskEntry.COLUMN_DONE},
                null, null, null, null, null);
        */
        Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COLUMN_TASK,
                        TaskContract.TaskEntry.COLUMN_IMPORTANT,
                        TaskContract.TaskEntry.COLUMN_QUICK,
                        TaskContract.TaskEntry.COLUMN_CLEAR,
                        TaskContract.TaskEntry.COLUMN_DONE},
                TaskContract.TaskEntry.COLUMN_DONE + " = ?", new String[] { "0" } , null, null, null);

        //Create a new TaskAdapter and bind it to ListView
        fTaskAdapter = new FiltersAdapter(getActivity(), cursor);
        // cTaskAdapter.swapCursor(cursor);
        listView.setAdapter(fTaskAdapter);

        return rootView;
    }
}
