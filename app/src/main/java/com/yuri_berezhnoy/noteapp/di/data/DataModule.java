package com.yuri_berezhnoy.noteapp.di.data;

import android.content.Context;

import com.yuri_berezhnoy.noteapp.core.NoteApplication;
import com.yuri_berezhnoy.noteapp.data.database.DatabaseHelper;
import com.yuri_berezhnoy.noteapp.data.database.DatabaseManager;
import com.yuri_berezhnoy.noteapp.di.RepositoryModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                RepositoryModule.class
        }
)
public class DataModule {

    @Singleton
    @Provides
    Context provideContext(NoteApplication application) {
        return application.getApplicationContext();
    }

    DatabaseHelper provideDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

    @Singleton
    @Provides
    DatabaseManager provideDatabaseManager(DatabaseHelper databaseHelper) {
        return new DatabaseManager(databaseHelper);
    }

}
