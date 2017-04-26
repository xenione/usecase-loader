package apps.xenione.com.demoloader.presentation.notes.di;

import android.app.Activity;

import apps.xenione.com.demoloader.data.NoteRepository;
import apps.xenione.com.demoloader.data.source.in_memory.di.InMemoryNoteDataModule;
import apps.xenione.com.demoloader.presentation.notes.Navigation;
import apps.xenione.com.demoloader.presentation.notes.notedetail.NoteDetailContract;
import apps.xenione.com.demoloader.presentation.notes.notedetail.NoteDetailPresenter;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListContract;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListPresenter;
import apps.xenione.com.demoloader.presentation.view.Notifier;
import dagger.Module;
import dagger.Provides;

@NoteScope
@Module(includes = InMemoryNoteDataModule.class)
public class NoteModule {

    private Activity mActivity;

    public NoteModule(Activity activity) {
        mActivity = activity;
    }

    @NoteScope
    @Provides
    public Navigation providesNavigation() {
        return (Navigation) mActivity;
    }

    @NoteScope
    @Provides
    Notifier providesNotifier() {
        return new Notifier(mActivity);
    }


    @NoteScope
    @Provides
    NoteListContract.Presenter providerNoteListPresenter(NoteRepository noteRepository, Navigation navigation) {
        return new NoteListPresenter(noteRepository, navigation);
    }

    @NoteScope
    @Provides
    NoteDetailContract.Presenter providerNoteDetailPresenter(NoteRepository noteRepository) {
        return new NoteDetailPresenter(noteRepository);
    }
}
