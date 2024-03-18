package com.yuri_berezhnoy.noteapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.base.fragment.AbsFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.NoteDialogFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.adapter.NoteAdapter;

public class NotesFragment extends AbsFragment<NotesViewModel, FragmentNotesBinding> {

    @Override
    protected FragmentNotesBinding getViewBinding() {
        return FragmentNotesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class provideViewModelClass() {
        return NotesViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NoteAdapter adapter = new NoteAdapter(
                onEditClick -> NoteDialogFragment.newInstance(onEditClick).show(getChildFragmentManager(), ""),
                onDeleteClick -> viewModel.deleteNote(onDeleteClick)
        );

        binding.rvNotes.setAdapter(adapter);

        viewModel.notes.observe(getViewLifecycleOwner(), observer -> {
            adapter.submitList(observer);
        });

        binding.fabNote.setOnClickListener(v -> {
            NoteDialogFragment.newInstance(0).show(getChildFragmentManager(), "");
        });

        viewModel.notes();
    }
}
