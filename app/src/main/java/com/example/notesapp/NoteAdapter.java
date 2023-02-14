package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<note, NoteAdapter.NoteViewHolder> {
    Context context;


    public NoteAdapter(@NonNull FirestoreRecyclerOptions<note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull note note) {
        holder.titletextview.setText((note.title));
        holder.contenttextview.setText((note.content));
        holder.timestamptextview.setText(utility.timestampToString(note.timestamp));
        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context,NoteDetailsActivity.class);
            intent.putExtra("title",note.title);
            intent.putExtra("content",note.content);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);
        });

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView titletextview, contenttextview, timestamptextview;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titletextview = itemView.findViewById(R.id.note_title_text_view);
            contenttextview = itemView.findViewById(R.id.note_content_text_view);
            timestamptextview = itemView.findViewById(R.id.note_timestamp_view);

        }
    }
}
