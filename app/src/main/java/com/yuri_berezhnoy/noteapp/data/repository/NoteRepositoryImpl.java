package com.yuri_berezhnoy.noteapp.data.repository;

import com.yuri_berezhnoy.noteapp.data.database.DatabaseManager;
import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

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
    public Observable<List<NoteUi>> notes() {
        return databaseManager.notes();
    }

    public Single<NoteUi> noteById(int id) {
        return databaseManager.noteById(id);
    }

    @Override
    public void add(NoteUi noteUi) {
        databaseManager.add(noteUi);
    }

    @Override
    public void update(NoteUi noteUi) {
        databaseManager.update(noteUi);
    }

    @Override
    public void delete(int id) {
        databaseManager.delete(id);
    }

}
