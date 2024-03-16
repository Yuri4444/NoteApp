package com.yuri_berezhnoy.noteapp.ui.notes;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.yuri_berezhnoy.noteapp.R;
import com.yuri_berezhnoy.noteapp.databinding.DialogNoteBinding;

import java.util.Objects;

public class NoteDialogFragment extends DialogFragment {

    private DialogNoteBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
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
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, dm.widthPixels, dm.heightPixels);
        float percentWidth = rect.width() * 0.9f;

        Objects.requireNonNull(getDialog().getWindow()).setLayout((int) percentWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_bg);
    }
}