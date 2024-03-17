package com.yuri_berezhnoy.noteapp.ui.notes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.yuri_berezhnoy.noteapp.R;
import com.yuri_berezhnoy.noteapp.databinding.ItemNoteBinding;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

public class NoteAdapter extends ListAdapter<NoteUi, NoteViewHolder> {

    private Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public NoteAdapter() {
        super(new NoteDiffUtil());
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(ItemNoteBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, final int position) {
        holder.bind(getItem(position), listener);
    }
}