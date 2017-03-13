package com.app.registrumbeta;
import com.app.registrumbeta.data.Task;
import com.app.registrumbeta.data.TasksDataSource;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.String;
import java.util.List;

import android.app.DialogFragment;
import android.widget.ListView;
import android.widget.Toast;



/**
 * Created by Илья on 05.03.2017.
 */

public class LaterFragment extends Fragment {

   // private static final String TASK_NAME = "Task Name";
   private TasksDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_later, container, false);



        //create database
        datasource = new TasksDataSource(getActivity());
        datasource.open();
        //pass database data to list
     //   List<Task> values = datasource.getAllTasks();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
     //   ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(getActivity(),
     //           R.layout.list_item_task,
     //           R.id.list_item_task_textview, values);
      //  ListView listView = (ListView) rootView.findViewById(R.id.listview_tasks);
     //   listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = null;

                FragmentManager manager = getFragmentManager();
                AddDialogFragment dialog = new AddDialogFragment();
                dialog.show(manager, "Task Name");


            }
        });

     //   Bundle mArgs = getArguments();
     //   String newTask = mArgs.getString("inputTask");

     //   task = datasource.createTask(newTask);
      //  adapter.add(task);

        return rootView;
    }
}
