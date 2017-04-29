package apps.xenione.com.demoloader.presentation.notes.di;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import apps.xenione.com.demoloader.data.NoteRepository;
import apps.xenione.com.demoloader.presentation.notes.notelist.di.NoteListComponent;
import apps.xenione.com.demoloader.presentation.notes.notelist.di.NoteListModule;
import dagger.Subcomponent;

@NoteScope
@Subcomponent(modules = NoteModule.class)
public interface NoteComponent {

    NoteListComponent plus(NoteListModule module);

    // void inject(NoteDetailFragment fragment);

    Context context();

    NoteRepository noteRepository();

    LoaderManager loaderManager();

}
