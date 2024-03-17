package com.yuri_berezhnoy.noteapp.data.repository;

import com.yuri_berezhnoy.noteapp.data.database.DatabaseManager;
import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.List;

import javax.inject.Inject;

public class NoteRepositoryImpl implements NoteRepository {

    DatabaseManager databaseManager;

    @Inject
    public NoteRepositoryImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public List<NoteUi> notes() {
        return databaseManager.notes();
    }

    @Override
    public void add(NoteUi noteUi) {
        databaseManager.add(noteUi);
    }

    @Override
    public void update(String content) {

    }

    @Override
    public void remove(String content) {

    }


}
