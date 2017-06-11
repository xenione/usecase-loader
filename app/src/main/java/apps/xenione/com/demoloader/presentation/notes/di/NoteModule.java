package apps.xenione.com.demoloader.presentation.notes.di;

import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;

import apps.xenione.com.demoloader.data.di.DataModule;
import apps.xenione.com.demoloader.presentation.notes.Navigation;
import apps.xenione.com.demoloader.presentation.view.Notifier;
import dagger.Module;
import dagger.Provides;

@NoteScope
@Module(includes = DataModule.class)
public class NoteModule {

    private AppCompatActivity mActivity;

    public NoteModule(AppCompatActivity activity) {
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
    LoaderManager providesLoaderManager() {
        return mActivity.getSupportLoaderManager();
    }
}
