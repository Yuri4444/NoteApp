package com.yuri_berezhnoy.noteapp.ui.notes;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuri_berezhnoy.noteapp.R;
import com.yuri_berezhnoy.noteapp.databinding.DialogNoteBinding;
import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.base.dialog.AbsDialogFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.Objects;

public class NoteDialogFragment extends AbsDialogFragment<NoteDialogViewModel, FragmentNotesBinding> {

    private DialogNoteBinding binding;

    @Override
    protected FragmentNotesBinding getViewBinding() {
        return FragmentNotesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<NoteDialogViewModel> provideViewModelClass() {
        return NoteDialogViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogNoteBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int id = getArguments().getInt(ID_NOTE);

        viewModel.note.observe(getViewLifecycleOwner(), observer -> {
            binding.etContent.setText(observer.content);
        });

        binding.btnSave.setOnClickListener(v -> {
            String inputContent = binding.etContent.getText().toString();
            if (inputContent.isBlank()) {
                Toast.makeText(getContext(), "Please enter text", Toast.LENGTH_LONG).show();
            } else if (id == 0) {
                viewModel.add(new NoteUi(0, binding.etContent.getText().toString()));
                dismiss();
            } else {
                viewModel.update(new NoteUi(id, binding.etContent.getText().toString()));
                dismiss();
            }
        });

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, dm.widthPixels, dm.heightPixels);
        float percentWidth = rect.width() * 0.9f;

        Window window = Objects.requireNonNull(getDialog()).getWindow();

        if (window != null) {
            window.setLayout((int) percentWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(R.drawable.dialog_rounded_bg);
        }

        if (id != 0) {
            binding.tvTitle.setText(R.string.update_your_note);
            viewModel.fetchMyNoteById(id);
        }
    }

    private static final String ID_NOTE = "id_note";

    public static NoteDialogFragment newInstance(int id) {
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ID_NOTE, id);
        noteDialogFragment.setArguments(bundle);
        return noteDialogFragment;
    }

}