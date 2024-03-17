package com.yuri_berezhnoy.noteapp.data.database;

import static android.provider.BaseColumns._ID;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT;
import static com.yuri_berezhnoy.noteapp.data.database.FeedReaderContract.FeedEntry.TABLE_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.ArrayList;

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

    public void add(NoteUi noteUi) {
        writeDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CONTENT, noteUi.content);

        database.insert(TABLE_NAME, null, values);
    }

    public ArrayList<NoteUi> notes() {
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

        return newList;
    }

    public void close() {
        databaseHelper.close();
    }
}
