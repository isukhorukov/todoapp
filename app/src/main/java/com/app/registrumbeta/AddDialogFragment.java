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

public class AddDialogFragment extends DialogFragment {

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
                        System.out.println(inputTask);

                    //    Bundle args = new Bundle();
                     //   args.putString("inputTask", inputTask);
                     //   LaterFragment newFragment = new LaterFragment();
                     //   newFragment.setArguments(args);

                    }
        }).setNegativeButton("Cancel", null).create();

    }
}
