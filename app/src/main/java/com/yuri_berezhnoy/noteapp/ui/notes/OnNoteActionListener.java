package com.yuri_berezhnoy.noteapp.ui.notes;

import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

public interface OnNoteActionListener {
    void onNoteAdded(NoteUi noteUi);
    void onNoteUpdated(int position, NoteUi noteUi);
}
