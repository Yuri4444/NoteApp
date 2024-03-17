package com.yuri_berezhnoy.noteapp.domain;

import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.List;

public interface NoteRepository {
    List<NoteUi> notes();
    void add(NoteUi noteUi);
    void update(String content);
    void remove(String content);
}
