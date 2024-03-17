package com.yuri_berezhnoy.noteapp.di;

import com.yuri_berezhnoy.noteapp.core.NoteApplication;
import com.yuri_berezhnoy.noteapp.di.data.DataModule;
import com.yuri_berezhnoy.noteapp.di.ui.UiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules = {
                UiModule.class,
                DataModule.class,
                AndroidInjectionModule.class
        }
)
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(NoteApplication application);

        AppComponent build();
    }

    void inject(NoteApplication application);

}
