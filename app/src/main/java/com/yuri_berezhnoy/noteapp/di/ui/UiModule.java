package com.yuri_berezhnoy.noteapp.di.ui;

import com.yuri_berezhnoy.noteapp.di.ViewModelModule;

import dagger.Module;

@Module(
        includes = {
                FragmentModule.class,
                ViewModelModule.class
        }
)
public abstract class UiModule {
}
