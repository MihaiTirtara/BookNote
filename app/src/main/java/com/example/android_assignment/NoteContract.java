package com.example.android_assignment;

import android.provider.BaseColumns;

public class NoteContract
{

    private NoteContract(){}

    public static final class NoteEntry implements BaseColumns
    {
        public static final String TABLE_NAME="noteList";
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_TEXT="text";
    }
}
