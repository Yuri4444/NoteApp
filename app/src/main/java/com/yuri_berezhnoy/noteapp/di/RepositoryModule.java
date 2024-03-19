package com.yuri_berezhnoy.noteapp.di;

import com.yuri_berezhnoy.noteapp.data.database.DatabaseManager;
import com.yuri_berezhnoy.noteapp.data.repository.NoteRepositoryImpl;
import com.yuri_berezhnoy.noteapp.data.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    NoteRepository provideNoteRepository(DatabaseManager databaseManager) {
        return new NoteRepositoryImpl(databaseManager);
    }

}
