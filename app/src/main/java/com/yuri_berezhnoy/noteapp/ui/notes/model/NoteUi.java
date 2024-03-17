package com.yuri_berezhnoy.noteapp.ui.notes.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class NoteUi {
    public int id;
    public String content;

    public NoteUi(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Boolean match(@NonNull NoteUi source) {
        return source.id == id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteUi noteUi)) return false;

        if (id != noteUi.id) return false;
        return Objects.equals(content, noteUi.content);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "NoteUi{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}