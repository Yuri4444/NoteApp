package com.yuri_berezhnoy.noteapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yuri_berezhnoy.noteapp.ui.NotesViewModel;
import com.yuri_berezhnoy.noteapp.ui.notes.NoteDialogViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract public class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel.class)
    abstract ViewModel provideNoteViewModel(NotesViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NoteDialogViewModel.class)
    abstract ViewModel provideNoteDialogViewModel(NoteDialogViewModel viewModel);
}
