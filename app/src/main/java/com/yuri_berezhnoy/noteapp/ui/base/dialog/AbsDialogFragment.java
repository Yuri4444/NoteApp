package com.yuri_berezhnoy.noteapp.ui.base.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

public abstract class AbsDialogFragment<VM extends ViewModel, VB extends ViewBinding> extends AbsDaggerDialogFragment<VM> {

    public VB binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding();
        return binding.getRoot();
    }

    protected abstract VB getViewBinding();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
