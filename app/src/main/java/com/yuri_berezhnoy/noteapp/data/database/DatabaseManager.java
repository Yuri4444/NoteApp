package com.yuri_berezhnoy.noteapp.data.database;

import static android.provider.BaseColumns._ID;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.TABLE_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

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

    public Observable<List<NoteUi>> notes() {
        readDatabase();
        ArrayList<NoteUi> newList = new ArrayList<>();

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
            NoteUi noteUi = new NoteUi(itemId, itemContent);
            newList.add(noteUi);
        }
        cursor.close();

        return Observable.fromCallable(() -> newList);
    }

    public NoteUi noteById(int id) {
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

        NoteUi noteUi;
        if (cursor.moveToFirst()) {
            String itemContent = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CONTENT));
            noteUi = new NoteUi(id, itemContent);
        } else {
            noteUi = null;
        }
        cursor.close();

        return noteUi;
    }

    public void add(NoteUi noteUi) {
        writeDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CONTENT, noteUi.content);

        database.insert(TABLE_NAME, null, values);
    }

    public void update(NoteUi noteUi) {
        writeDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CONTENT, noteUi.content);

        String selection = _ID + " = ?";
        String[] selectionArgs = {String.valueOf(noteUi.id)};

        database.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public void delete(int id) {
        writeDatabase();
        String selection = _ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.delete(TABLE_NAME, selection, selectionArgs);
    }

    public void close() {
        databaseHelper.close();
    }
}
