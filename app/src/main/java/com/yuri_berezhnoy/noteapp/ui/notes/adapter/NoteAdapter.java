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

    private final OnEditClickListener onEditClickListener;
    private final OnDeleteClickListener onDeleteClickListener;

    public interface OnEditClickListener {
        void onEditClick(int id);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int id);
    }

    public NoteAdapter(
            OnEditClickListener onEditClickListener,
            OnDeleteClickListener onDeleteClickListener
    ) {
        super(new NoteDiffUtil());
        this.onEditClickListener = onEditClickListener;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(ItemNoteBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, final int position) {
        holder.bind(getItem(position), onEditClickListener, onDeleteClickListener);
    }
}