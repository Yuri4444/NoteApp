package com.yuri_berezhnoy.noteapp.ui.notes.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuri_berezhnoy.noteapp.databinding.ItemNoteBinding;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final ItemNoteBinding binding;


    public NoteViewHolder(@NonNull ItemNoteBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(NoteUi item) {
        binding.tvTitle.setText(item.content);
    }
}
