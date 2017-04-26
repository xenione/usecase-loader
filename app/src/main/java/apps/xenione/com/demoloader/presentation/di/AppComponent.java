package apps.xenione.com.demoloader.presentation.di;

import android.content.Context;

import javax.inject.Singleton;

import apps.xenione.com.demoloader.presentation.notes.NoteActivity;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    //NoteComponent plus(NoteModule module);

    void inject(NoteActivity activity);

    Context context();
}
