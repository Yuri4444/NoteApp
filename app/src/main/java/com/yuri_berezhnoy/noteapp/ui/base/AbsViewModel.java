package com.yuri_berezhnoy.noteapp.ui.base;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class AbsViewModel extends ViewModel {
    protected ArrayList<Disposable> disposable = new ArrayList<>();

    @Override
    protected void onCleared() {
        for (Disposable d: disposable) {
            d.dispose();
        }
        super.onCleared();
    }
}
