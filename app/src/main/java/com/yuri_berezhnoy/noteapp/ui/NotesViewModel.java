package com.yuri_berezhnoy.noteapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yuri_berezhnoy.noteapp.domain.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.base.AbsViewModel;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotesViewModel extends AbsViewModel {

    NoteRepository noteRepository;

    @Inject
    public NotesViewModel(
            NoteRepository noteRepository
    ) {
        this.noteRepository = noteRepository;
    }

    private final MutableLiveData<List<NoteUi>> mutableLiveDataNotes = new MutableLiveData<>();
    LiveData<List<NoteUi>> liveData = mutableLiveDataNotes;

    void notes() {
        disposable.add(noteRepository.notes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mutableLiveDataNotes::setValue,
                        error -> {

                        })
        );
    }

    void deleteNote(int id) {
        noteRepository.delete(id);
    }
}
