package com.yuri_berezhnoy.noteapp.ui.notes;

import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

public interface OnNoteActionListener {
    void onNoteAdded(Note note);
    void onNoteUpdated(int position, Note note);
}
