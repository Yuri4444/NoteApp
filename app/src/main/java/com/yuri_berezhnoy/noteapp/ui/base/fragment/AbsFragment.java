package com.yuri_berezhnoy.noteapp.ui.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

public abstract class AbsFragment<VM extends ViewModel, VB extends ViewBinding> extends AbsDaggerFragment<VM> {

    public VB binding;

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
