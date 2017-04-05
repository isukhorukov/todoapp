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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.registrumbeta.adapters.ClearAdapter;
import com.app.registrumbeta.adapters.CompleteAdapter;
import com.app.registrumbeta.adapters.CriteriaAdapter;

import com.app.registrumbeta.adapters.QuickAdapter;
import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 05.03.2017.
 */

public class CriteriaFragment extends Fragment implements SeekBar.OnSeekBarChangeListener{

    public CriteriaFragment () {

    }

   // LaterFragment.mTaskAdapter.notifyDataSetChanged();
    //numbers of same columns for binding view
    static public final int COL_TASK_ID = 0;
    static public final int COL_TASK_NAME = 1;
    static public final int COL_TASK_IMPORTANT = 2;
    static public final int COL_TASK_QUICK = 3;
    static public final int COL_TASK_CLEAR = 4;

    ListView listView;
    TextView sTextView;
    SeekBar seekBar;
    Cursor cursor;
    View rootView;

    public static CriteriaAdapter cTaskAdapter;
    public static QuickAdapter quickAdapter;
    public static ClearAdapter clearAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_criteria, container, false);

        //Find the listView
        listView = (ListView) rootView.findViewById(R.id.listview_make_important);

        seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(0);
        //sTextView = (TextView) rootView.findViewById(R.id.seekTextView);
        //sTextView.setText("0");

        //Get DBHelper to read from database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //Query database to get any existing data
     /*   Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COLUMN_TASK,
                        TaskContract.TaskEntry.COLUMN_IMPORTANT,
                        TaskContract.TaskEntry.COLUMN_QUICK,
                        TaskContract.TaskEntry.COLUMN_CLEAR,
                        TaskContract.TaskEntry.COLUMN_DONE},
                null, null, null, null, null); */

        cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{ TaskContract.TaskEntry._ID,
                        TaskContract.TaskEntry.COLUMN_TASK,
                        TaskContract.TaskEntry.COLUMN_IMPORTANT,
                        TaskContract.TaskEntry.COLUMN_QUICK,
                        TaskContract.TaskEntry.COLUMN_CLEAR,
                        TaskContract.TaskEntry.COLUMN_DONE},
                TaskContract.TaskEntry.COLUMN_DONE + " = ?", new String[] { "0" } , null, null, null);

        //Create a new TaskAdapter and bind it to ListView
        cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
        //cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
        quickAdapter = new QuickAdapter(getActivity(), cursor);
        clearAdapter = new ClearAdapter(getActivity(), cursor);
       // cTaskAdapter.swapCursor(cursor);
        //listView.setAdapter(cTaskAdapter);
/*
        switch (seekBar.getProgress()) {
            case 0:
                //cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
                listView.setAdapter(cTaskAdapter);
                break;
               // return rootView;
            case 1:
                //quickAdapter = new QuickAdapter(getActivity(), cursor);
                listView.setAdapter(quickAdapter);
                break;
              //  return rootView;
            case 2:
                //clearAdapter = new ClearAdapter(getActivity(), cursor);
                listView.setAdapter(clearAdapter);
                break;
               // return rootView;
            default:
                //cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
                listView.setAdapter(cTaskAdapter);
                break;
               // return rootView;
        }
*/


        return rootView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (progress) {
            case 0:
                //cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
                listView.setAdapter(cTaskAdapter);
                break;
               // return rootView;
            case 1:
                //quickAdapter = new QuickAdapter(getActivity(), cursor);
                listView.setAdapter(quickAdapter);
                break;
               // return rootView;
            case 2:
                //clearAdapter = new ClearAdapter(getActivity(), cursor);
                listView.setAdapter(clearAdapter);
                break;
               // return rootView;
            default:
                //cTaskAdapter = new CriteriaAdapter(getActivity(), cursor);
                listView.setAdapter(cTaskAdapter);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
       // sTextView.setText(String.valueOf(seekBar.getProgress()));

    }

}
