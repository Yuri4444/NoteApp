package com.yuri_berezhnoy.noteapp.domain;

import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface NoteRepository {
    Observable<List<NoteUi>> notes();

    Single<NoteUi> noteById(int id);

    void add(NoteUi noteUi);

    void update(NoteUi noteUi);

    void delete(int id);
}
