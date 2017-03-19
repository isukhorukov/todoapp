package com.app.registrumbeta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.registrumbeta.adapters.CriteriaAdapter;

import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 05.03.2017.
 */

public class CriteriaFragment extends Fragment {

    public CriteriaFragment () {

    }

   // LaterFragment.mTaskAdapter.notifyDataSetChanged();

    static public final int COL_TASK_ID = 0;
    static public final int COL_TASK_NAME = 1;

    static CriteriaAdapter cTaskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_criteria, container, false);

        //Find the listView
        ListView listView = (ListView) rootView.findViewById(R.id.listview_make_important);

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
        cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
        cTaskAdapter.swapCursor(cursor);
        listView.setAdapter(cTaskAdapter);

        return rootView;
    }
}
