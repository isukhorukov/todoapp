package com.app.registrumbeta.data;

import android.provider.BaseColumns;

public class TaskContract {

    //Each of xxxEntry corresponds to a table in the database.
    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME       = "tasks";
        public static final String COLUMN_TASK      = "task";
		public static final String COLUMN_IMPORTANT = "important";
		public static final String COLUMN_QUICK     = "quick";
		public static final String COLUMN_CLEAR     = "clear";
		public static final String COLUMN_DONE      = "done";
    }
}
