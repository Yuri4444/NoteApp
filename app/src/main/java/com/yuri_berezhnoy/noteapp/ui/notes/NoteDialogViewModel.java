package com.yuri_berezhnoy.noteapp.ui.notes;

import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.base.AbsViewModel;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import javax.inject.Inject;

public class NoteDialogViewModel extends AbsViewModel {

    NoteRepository noteRepository;

    @Inject
    public NoteDialogViewModel(
            NoteRepository noteRepository
    ) {
        this.noteRepository = noteRepository;
    }

    void add(NoteUi noteUi) {
        noteRepository.add(noteUi);
    }

    void editNote(int id, String content) {
        noteRepository.edit(new NoteUi(id, content));
    }

}
