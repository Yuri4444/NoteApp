package com.yuri_berezhnoy.noteapp.ui.notes;

import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.base.AbsViewModel;

import javax.inject.Inject;

public class NoteDialogViewModel extends AbsViewModel {

    NoteRepository noteRepository;

    @Inject
    public NoteDialogViewModel(
            NoteRepository noteRepository
    ) {
        this.noteRepository = noteRepository;
    }


}
