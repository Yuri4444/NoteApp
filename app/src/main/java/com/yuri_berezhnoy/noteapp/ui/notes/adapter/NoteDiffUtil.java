package com.yuri_berezhnoy.noteapp.ui.notes.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

public class NoteDiffUtil extends DiffUtil.ItemCallback<NoteUi> {

    @Override
    public boolean areItemsTheSame(@NonNull NoteUi oldItem, @NonNull NoteUi newItem) {
        return oldItem.match(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull NoteUi oldItem, @NonNull NoteUi newItem) {
        return oldItem.equals(newItem);
    }
}
