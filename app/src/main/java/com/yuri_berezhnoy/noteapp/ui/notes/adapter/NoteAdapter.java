package com.yuri_berezhnoy.noteapp.ui.notes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuri_berezhnoy.noteapp.R;
import com.yuri_berezhnoy.noteapp.databinding.ItemNoteBinding;
import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final OnEditClickListener onEditClickListener;
    private final OnDeleteClickListener onDeleteClickListener;
    ArrayList<Note> currentList = new ArrayList<>();

    public NoteAdapter(
            OnEditClickListener onEditClickListener,
            OnDeleteClickListener onDeleteClickListener
    ) {
        this.onEditClickListener = onEditClickListener;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnEditClickListener {
        void onEditClick(int id, int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int id, int position);
    }

    public void setData(List<Note> newList) {
        currentList.clear();
        currentList.addAll(newList);
        notifyDataSetChanged();
    }

    public void addItem(Note note) {
        currentList.add(note);
    }

    public void updateItem(int position, Note updatedNote) {
        currentList.set(position, updatedNote);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        currentList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return currentList.size();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(ItemNoteBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, final int position) {
        holder.bind(currentList.get(position), position, onEditClickListener, onDeleteClickListener);
    }
}