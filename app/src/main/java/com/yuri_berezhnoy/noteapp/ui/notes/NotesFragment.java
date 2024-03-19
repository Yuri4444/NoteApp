package com.yuri_berezhnoy.noteapp.ui.notes;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.base.fragment.AbsFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.adapter.NoteAdapter;
import com.yuri_berezhnoy.noteapp.ui.notes.model.Note;

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
    public void onNoteUpdated(int position, Note note) {
        adapter.updateItem(position, note);
    }

    private void removeItem(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new NoteAdapter(
                (idEdit, posEdit) -> NoteDialogFragment.newInstance(idEdit, posEdit, this).show(getChildFragmentManager(), ""),
                (idDelete, posDelete) -> {
                    viewModel.deleteNote(idDelete);
                    removeItem(posDelete);
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
    public void onNoteAdded(Note note) {
        adapter.addItem(note);
        viewModel.notes();
    }

}
