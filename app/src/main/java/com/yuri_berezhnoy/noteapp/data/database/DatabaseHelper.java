package com.yuri_berezhnoy.noteapp.data.database;

import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.DATABASE_NAME;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.DATABASE_VERSION;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.SQL_CREATE_ENTRIES;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.SQL_DELETE_ENTRIES;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import javax.inject.Inject;

public class DatabaseHelper extends SQLiteOpenHelper {

    @Inject
    public DatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
