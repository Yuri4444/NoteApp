package com.yuri_berezhnoy.noteapp.data.database;

import static android.provider.BaseColumns._ID;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.TABLE_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class DatabaseManager {
    private final DatabaseHelper databaseHelper;

    public DatabaseManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    SQLiteDatabase database;

    public void writeDatabase() {
        database = databaseHelper.getWritableDatabase();
    }

    public void readDatabase() {
        database = databaseHelper.getReadableDatabase();
    }

    public Observable<List<Note>> notes() {
        readDatabase();
        LinkedList<Note> newList = new LinkedList<>();

        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String itemContent = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CONTENT));
            Note note = new Note(itemId, itemContent);
            newList.add(0, note);
        }
        cursor.close();

        return Observable.fromCallable(() -> newList);
    }

    public Single<Note> noteById(int id) {
        readDatabase();

        String selection = _ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Note note;
        if (cursor.moveToFirst()) {
            String itemContent = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CONTENT));
            note = new Note(id, itemContent);
        } else {
            note = null;
        }
        cursor.close();

        return Single.fromCallable(() -> note);
    }

    public void add(Note note) {
        writeDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CONTENT, note.content);

        database.insert(TABLE_NAME, null, values);
    }

    public void update(Note note) {
        writeDatabase();
        ContentValues values = new ContentValues();
        values.put(_ID, note.id);
        values.put(COLUMN_NAME_CONTENT, note.content);

        String selection = _ID + " = ?";
        String[] selectionArgs = {String.valueOf(note.id)};

        database.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public void delete(int id) {
        writeDatabase();
        String selection = _ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.delete(TABLE_NAME, selection, selectionArgs);
    }
}
