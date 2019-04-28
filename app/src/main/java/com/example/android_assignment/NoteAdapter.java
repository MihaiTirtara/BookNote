package com.example.android_assignment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>
{
    private ArrayList<Note> notes;

    public NoteAdapter(ArrayList<Note> notes)
    {
        this.notes = notes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView ;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
             itemView = inflater.inflate(R.layout.list_notes, viewGroup, false);
             return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {

        viewHolder.text.setText(notes.get(i).getText());

    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView text;
     ViewHolder(View itemView)
     {
         super(itemView);
         text = itemView.findViewById(R.id.text);
     }

    }
}
