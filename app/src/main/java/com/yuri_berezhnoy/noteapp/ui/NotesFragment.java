package com.yuri_berezhnoy.noteapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.notes.NoteDialogFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.adapter.NoteAdapter;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.Arrays;

public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    NoteUi[] noteArray = new NoteUi[3];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        noteArray[0] = new NoteUi(1, "Заметка 1");
        noteArray[1] = new NoteUi(2, "Заметка 2");
        noteArray[2] = new NoteUi(3, "Заметка 3");

        NoteAdapter noteAdapter = new NoteAdapter();
        binding.rvNotes.setAdapter(noteAdapter);
        noteAdapter.submitList(Arrays.asList(noteArray));

        binding.fabNote.setOnClickListener(v -> {
            NoteDialogFragment dialog = new NoteDialogFragment();
            dialog.show(getChildFragmentManager(), "");
        });
    }
}
