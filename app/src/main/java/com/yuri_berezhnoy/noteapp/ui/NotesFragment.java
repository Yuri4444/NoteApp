package com.yuri_berezhnoy.noteapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.base.fragment.AbsFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.NoteDialogFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.OnNoteActionListener;
import com.yuri_berezhnoy.noteapp.ui.notes.adapter.NoteAdapter;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

public class NotesFragment extends AbsFragment<NotesViewModel, FragmentNotesBinding> implements OnNoteActionListener {

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
                (id, position) -> NoteDialogFragment.newInstance(id, position, this).show(getChildFragmentManager(), ""),
                (id, position) -> {
                    viewModel.deleteNote(id);
                    removeItem(position);
                }
        );

        binding.rvNotes.setAdapter(adapter);

        viewModel.liveData.observe(getViewLifecycleOwner(), observer -> adapter.setData(observer));

        binding.fabNote.setOnClickListener(v ->
                NoteDialogFragment.newInstance(0, 0, this).show(getChildFragmentManager(), "")
        );

        viewModel.notes();


    }

    @Override
    public void onNoteAdded(NoteUi noteUi) {
        adapter.addItem(noteUi);
        viewModel.notes();
    }

    @Override
    public void onNoteUpdated(int position, NoteUi noteUi) {
        adapter.updateItem(position, noteUi);
    }

    private void removeItem(int position) {
        adapter.removeItem(position);
    }
}
