package com.yuri_berezhnoy.noteapp.ui.notes;

import androidx.lifecycle.MutableLiveData;

import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.base.AbsViewModel;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteDialogViewModel extends AbsViewModel {

    NoteRepository noteRepository;

    @Inject
    public NoteDialogViewModel(
            NoteRepository noteRepository
    ) {
        this.noteRepository = noteRepository;
    }

    MutableLiveData<NoteUi> note = new MutableLiveData<>();

    void add(NoteUi noteUi) {
        noteRepository.add(noteUi);
    }

    void update(NoteUi noteUi) {
        noteRepository.update(noteUi);
    }

    void fetchMyNoteById(int id) {
        disposable.add(noteRepository.noteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> note.setValue(result),
                        error -> {

                        }
                )
        );
    }
}
