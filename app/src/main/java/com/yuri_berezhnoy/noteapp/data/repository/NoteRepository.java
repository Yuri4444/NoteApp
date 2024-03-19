package com.yuri_berezhnoy.noteapp.data.repository;

import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface NoteRepository {
    Observable<List<Note>> notes();

    Single<Note> noteById(int id);

    void add(Note note);

    void update(Note note);

    void delete(int id);
}
