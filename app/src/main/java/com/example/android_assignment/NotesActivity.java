package com.example.android_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    RecyclerView noteBook;
    RecyclerView.Adapter noteAdapter;
    private NoteHelper noteHelper;
    ArrayList<Note> notes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        buildRecyclerView();

        Intent intentAddNoteActivity = getIntent();
        if(intentAddNoteActivity.hasExtra("TEXT"))
        {
            String text = getIntent().getExtras().getString("TEXT");
        }

    }


    private void buildRecyclerView()
    {
        noteBook = findViewById(R.id.rv);
        noteBook.hasFixedSize();
        noteBook.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(notes);
        noteHelper = new NoteHelper(NotesActivity.this);

        noteBook.setAdapter(noteAdapter);

        getDataFromSQLite();

    }

    private void getDataFromSQLite()
    {
       new AsyncTask<Void,Void,Void>()
       {

           @Override
           protected Void doInBackground(Void... voids) {
               notes.clear();
               notes.addAll(noteHelper.getAllNotes());

               return null;
           }

           protected void onPostExecute(Void aVoid)
           {
               super.onPostExecute(aVoid);
               noteAdapter.notifyDataSetChanged();
           }
       }.execute();


    }

}
