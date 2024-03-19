package com.yuri_berezhnoy.noteapp.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yuri_berezhnoy.noteapp.data.repository.NoteRepository;
import com.yuri_berezhnoy.noteapp.ui.base.AbsViewModel;
import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

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

    private final MutableLiveData<List<Note>> mutableLiveDataNotes = new MutableLiveData<>();
    LiveData<List<Note>> liveData = mutableLiveDataNotes;

    MutableLiveData<Note> note = new MutableLiveData<>();

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

    void add(Note note) {
        noteRepository.add(note);
    }

    void update(Note note) {
        noteRepository.update(note);
    }

    void fetchNoteById(int id) {
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
