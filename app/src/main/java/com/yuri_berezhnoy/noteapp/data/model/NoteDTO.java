package com.yuri_berezhnoy.noteapp.data.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class NoteDTO {
    public int id;
    public String content;

    public NoteDTO(int id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteDTO NoteDTO)) return false;

        if (id != NoteDTO.id) return false;
        return Objects.equals(content, NoteDTO.content);
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
        return "NoteDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
