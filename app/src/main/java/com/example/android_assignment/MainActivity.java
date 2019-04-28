package com.example.android_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btn1 = (Button) findViewById(R.id.add_Note);
        Button btn2 = (Button) findViewById(R.id.notes);
        btn1.setOnClickListener(addNoteClicked);
        btn2.setOnClickListener(notesClicked);

    }


    View.OnClickListener addNoteClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);

        }
    };

    View.OnClickListener notesClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            Toast.makeText(MainActivity.this,"what's poopin",Toast.LENGTH_LONG);
            startActivity(intent);


        }
    };
}
