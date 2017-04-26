package apps.xenione.com.demoloader.presentation.notes.di;

import apps.xenione.com.demoloader.presentation.notes.notedetail.NoteDetailFragment;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListFragment;
import dagger.Subcomponent;

@NoteScope
@Subcomponent(modules = NoteModule.class)
public interface NoteComponent {

    void inject(NoteListFragment fragment);

    void inject(NoteDetailFragment fragment);

}
