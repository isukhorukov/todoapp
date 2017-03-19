package com.app.registrumbeta;

/**
 * Created by Илья on 05.03.2017.
 */
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.content.DialogInterface;

public class AddDialogFragment extends DialogFragment  {
    private OnAddTaskListener callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (OnAddTaskListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    public static final String TAG_TASK_INPUT = "task";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final EditText inputField = new EditText(getActivity());

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.task_name).setView(inputField)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Get user input
                        String inputTask = inputField.getText().toString();
                       // System.out.println(inputTask);
                        //Resources().getString(R.string.inputTask) = inputTask;
                       // callback.onAddTaskSubmit(inputTask);


                    }
        }).setNegativeButton("Cancel", null).create();

    }
}
