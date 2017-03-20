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
    //numbers of same columns for binding view
    static public final int COL_TASK_ID = 0;
    static public final int COL_TASK_NAME = 1;
    static public final int COL_TASK_IMPORTANT = 2;

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
       // cTaskAdapter.swapCursor(cursor);
        listView.setAdapter(cTaskAdapter);

        return rootView;
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Fragment 1", "onCreate");
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Toast.makeText(getActivity(), "FirstFragment.onAttach()",
                Toast.LENGTH_LONG).show();
        Log.d("Fragment 1", "onAttach");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("Fragment 1", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d("Fragment 1", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("Fragment 1", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("Fragment 1", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d("Fragment 1", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("Fragment 1", "onDestroyView");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("Fragment 1", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;

        Log.d("Fragment 1", "onDetach");
    }
*/
/*
    @Override
    public void onStart() {
        super.onStart();
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COLUMN_TASK,
                        TaskContract.TaskEntry.COLUMN_IMPORTANT,
                        TaskContract.TaskEntry.COLUMN_QUICK,
                        TaskContract.TaskEntry.COLUMN_CLEAR,
                        TaskContract.TaskEntry.COLUMN_DONE},
                null, null, null, null, null);
        cTaskAdapter.swapCursor(cursor);
    }
    */
}
