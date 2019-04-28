package com.example.android_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android_assignment.NoteContract.*;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class NoteHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "notelist.db";
    public static final int DATABASE_VERSION=1;
    private SQLiteDatabase db;

    public NoteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE_NAME;

    public NoteHelper open() throws SQLException
    {
        db = getWritableDatabase();
        return this;

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String SQL_CREATE_NOTELIST_TABLE = "CREATE TABLE " + NoteContract.NoteEntry.TABLE_NAME + " ("
                + NoteContract.NoteEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NoteContract.NoteEntry.COLUMN_TEXT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_NOTELIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_NOTE_TABLE);
        onCreate(db);
    }

    public void addNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NoteContract.NoteEntry.COLUMN_TEXT,note.getText());
        db.insert(NoteContract.NoteEntry.TABLE_NAME,null,cv);
        db.close();
    }

    public ArrayList<Note> getAllNotes()
    {
        String[] columns =
                {NoteEntry.COLUMN_TEXT};
        ArrayList<Note> noteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NoteEntry.TABLE_NAME,columns,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do
                {
                    Note note= new Note();
                    note.setText(cursor.getString(cursor.getColumnIndex(NoteEntry.COLUMN_TEXT)));
                    noteList.add(note);
            }while (cursor.moveToNext());
            }
            cursor.close();
        db.close();
        return noteList;
        }

    }
