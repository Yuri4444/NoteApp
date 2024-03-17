package com.yuri_berezhnoy.noteapp.ui.base.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class AbsDaggerDialogFragment<VM extends ViewModel> extends DialogFragment {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    protected VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        AndroidSupportInjection.inject(this);

        Class<VM> viewModelClass = provideViewModelClass();
        if (viewModelClass != null) {
            viewModel = new ViewModelProvider(this, viewModelFactory).get(viewModelClass);
        }
    }

    protected abstract Class<VM> provideViewModelClass();
}