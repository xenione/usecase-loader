package apps.xenione.com.demoloader.presentation;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;

import apps.xenione.com.demoloader.data.NoteDao;

/**
 * Created by Eugeni on 17/09/2016.
 */
public class App extends Application {

    private NoteDao mNoteDao;

    @Override
    public void onCreate() {
        super.onCreate();
        mNoteDao = new NoteDao();
    }

    public static NoteDao getNoteDao(Context context) {
        return ((App) context.getApplicationContext()).mNoteDao;
    }

    public static LoaderManager getLoaderManager(FragmentActivity activity) {
        return activity.getSupportLoaderManager();
    }
}
