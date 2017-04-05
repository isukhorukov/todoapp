package com.app.registrumbeta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.registrumbeta.adapters.CompleteAdapter;
import com.app.registrumbeta.adapters.CriteriaAdapter;
import com.app.registrumbeta.adapters.TaskAdapter;
import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 05.03.2017.
 */

public class CompleteFragment extends Fragment {

    public CompleteFragment () {

    }

   // static public TaskAdapter compTaskAdapter;
    static public CompleteAdapter compTaskAdapter;
    //static public TaskAdapter mTaskAdapter;

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complete, container, false);

        //Find the listView
        listView = (ListView) rootView.findViewById(R.id.listview_done);

        //Get DBHelper to read from database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();


        Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COLUMN_TASK,
                        TaskContract.TaskEntry.COLUMN_IMPORTANT,
                        TaskContract.TaskEntry.COLUMN_QUICK,
                        TaskContract.TaskEntry.COLUMN_CLEAR,
                        TaskContract.TaskEntry.COLUMN_DONE},
                TaskContract.TaskEntry.COLUMN_DONE + " = ?", new String[] { "1" } , null, null, null);

        //Create a new TaskAdapter and bind it to ListView
        compTaskAdapter = new CompleteAdapter(getActivity(), cursor);
        listView.setAdapter(compTaskAdapter);

        return rootView;
    }
}
