package com.app.registrumbeta.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.registrumbeta.CriteriaFragment;
import com.app.registrumbeta.FiltersFragment;
import com.app.registrumbeta.LaterFragment;
import com.app.registrumbeta.R;
import com.app.registrumbeta.data.TaskContract;
import com.app.registrumbeta.data.TaskDBHelper;

/**
 * Created by Илья on 21.03.2017.
 */

public class CompleteAdapter extends CursorAdapter {

    private static Context context;
    TaskDBHelper helper;

    public CompleteAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
        helper = new TaskDBHelper(context);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_done, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find Views to populate in inflated template
        TextView textView = (TextView) view.findViewById(R.id.list_item_task_done);

        // Extract properties from cursor
        final String task = cursor.getString(LaterFragment.COL_TASK_NAME);

        // Populate views with extracted properties
        textView.setText(task);

    }
}
