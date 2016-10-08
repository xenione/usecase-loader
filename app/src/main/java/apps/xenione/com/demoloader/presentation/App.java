package apps.xenione.com.demoloader.presentation;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;

import apps.xenione.com.demoloader.cuore.Note;
import apps.xenione.com.demoloader.cuore.NoteRepository;
import apps.xenione.com.demoloader.cuore.NoteRepositoryImpl;
import apps.xenione.com.demoloader.cuore.usecases.AddNoteUseCase;
import apps.xenione.com.demoloader.cuore.usecases.GetNoteListUseCase;

/**
 * Created by Eugeni on 17/09/2016.
 */
public class App extends Application {

    private NoteRepository mNoteRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mNoteRepository = new NoteRepositoryImpl();
    }

    public static NoteRepository getNoteRepository(Context context) {
        return ((App) context.getApplicationContext()).mNoteRepository;
    }

    public static LoaderManager getLoaderManager(FragmentActivity activity) {
        return activity.getSupportLoaderManager();
    }

    public static FragmentManager getFragmentManager(FragmentActivity activity) {
        return activity.getSupportFragmentManager();
    }

    public static AddNoteUseCase getAddNoteUseCase(FragmentActivity activity, Note note) {
        return new AddNoteUseCase(getNoteRepository(activity), note);
    }

    public static GetNoteListUseCase getGetNoteUseCase(FragmentActivity activity) {
        return new GetNoteListUseCase(getNoteRepository(activity));
    }
}
