package com.yuri_berezhnoy.noteapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.base.fragment.AbsFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.NoteDialogFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.adapter.NoteAdapter;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

public class NotesFragment extends AbsFragment<NotesViewModel, FragmentNotesBinding> {

    NoteAdapter adapter;

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

        adapter = new NoteAdapter(
                onEditClick -> NoteDialogFragment.newInstance(onEditClick).show(getChildFragmentManager(), ""),
                (id, position) -> {
                    viewModel.deleteNote(id);
                    removeItem(position);
                }
        );

        binding.rvNotes.setAdapter(adapter);

        viewModel.liveData.observe(getViewLifecycleOwner(),
                adapter::submitList
        );

        binding.fabNote.setOnClickListener(v -> NoteDialogFragment.newInstance(0).show(getChildFragmentManager(), ""));

        viewModel.notes();
    }

    private void addItem(NoteUi noteUi) {
        adapter.addItem(noteUi);
    }

    private void removeItem(int position) {
        adapter.removeItem(position);
    }

}
