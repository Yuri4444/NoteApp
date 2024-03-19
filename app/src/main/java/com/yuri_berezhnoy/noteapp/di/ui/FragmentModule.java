package com.yuri_berezhnoy.noteapp.di.ui;

import com.yuri_berezhnoy.noteapp.ui.notes.NotesFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.NoteDialogFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract NotesFragment contributeNotesFragment();

    @ContributesAndroidInjector
    abstract NoteDialogFragment contributeNoteDialogFragment();
}
