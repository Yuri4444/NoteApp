package com.yuri_berezhnoy.noteapp.data.repository;

import com.yuri_berezhnoy.noteapp.data.database.DatabaseManager;
import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class NoteRepositoryImpl implements NoteRepository {

    DatabaseManager databaseManager;

    @Inject
    public NoteRepositoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public Observable<List<Note>> notes() {
        return databaseManager.notes();
    }

    public Single<Note> noteById(int id) {
        return databaseManager.noteById(id);
    }

    @Override
    public void add(Note note) {
        databaseManager.add(note);
    }

    @Override
    public void update(Note note) {
        databaseManager.update(note);
    }

    @Override
    public void delete(int id) {
        databaseManager.delete(id);
    }

}
