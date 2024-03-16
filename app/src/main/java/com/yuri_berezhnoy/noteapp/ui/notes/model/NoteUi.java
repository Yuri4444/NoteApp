package com.yuri_berezhnoy.noteapp.ui.notes.model;

import androidx.annotation.NonNull;

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
        if (!(o instanceof NoteUi)) return false;

        NoteUi noteUi = (NoteUi) o;

        if (id != noteUi.id) return false;
        return content != null ? content.equals(noteUi.content) : noteUi.content == null;
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
