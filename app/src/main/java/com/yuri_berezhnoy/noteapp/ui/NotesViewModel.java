package com.yuri_berezhnoy.noteapp.ui;

import androidx.lifecycle.ViewModel;

import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.List;

import javax.inject.Inject;

public class NotesViewModel extends ViewModel {

    NoteRepository noteRepository;

    @Inject
    public NotesViewModel(
            NoteRepository noteRepository
    ) {
        this.noteRepository = noteRepository;
    }

    List<NoteUi> notes() {
        return noteRepository.notes();
    }

    public void add(NoteUi noteUi) {
        noteRepository.add(noteUi);
    }
}
