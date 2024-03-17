package com.yuri_berezhnoy.noteapp.ui.notes;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuri_berezhnoy.noteapp.R;
import com.yuri_berezhnoy.noteapp.databinding.DialogNoteBinding;
import com.yuri_berezhnoy.noteapp.databinding.FragmentNotesBinding;
import com.yuri_berezhnoy.noteapp.ui.NotesViewModel;
import com.yuri_berezhnoy.noteapp.ui.base.dialog.AbsDialogFragment;
import com.yuri_berezhnoy.noteapp.ui.notes.model.NoteUi;

import java.util.Objects;

public class NoteDialogFragment extends AbsDialogFragment<NotesViewModel, FragmentNotesBinding> {

    private DialogNoteBinding binding;

    @Override
    protected FragmentNotesBinding getViewBinding() {
        return FragmentNotesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<NotesViewModel> provideViewModelClass() {
        return NotesViewModel.class;
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

        binding.btnSave.setOnClickListener(v -> {
            viewModel.add(new NoteUi(0, binding.etContent.getText().toString()));
            dismiss();
        });

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, dm.widthPixels, dm.heightPixels);
        float percentWidth = rect.width() * 0.9f;

        Window window = Objects.requireNonNull(getDialog()).getWindow();

        if (window != null) {
            window.setLayout((int) percentWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(R.drawable.dialog_rounded_bg);
        }
    }
}