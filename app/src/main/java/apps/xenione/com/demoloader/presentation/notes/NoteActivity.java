package apps.xenione.com.demoloader.presentation.notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import apps.xenione.com.demoloader.App;
import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.di.AppComponent;
import apps.xenione.com.demoloader.presentation.notes.di.NoteComponent;
import apps.xenione.com.demoloader.presentation.notes.di.NoteModule;
import apps.xenione.com.demoloader.presentation.notes.notedetail.NoteDetailFragment;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListFragment;

public class NoteActivity extends AppCompatActivity implements Navigation {

    private NoteComponent mNoteComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        Fragment userListFragment = fm.findFragmentByTag(NoteListFragment.TAG);
        if (userListFragment == null) {
            fm.beginTransaction()
                    .add(R.id.main_container, NoteListFragment.newInstance(), NoteListFragment.TAG)
                    .commit();
        }
    }

    public AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    public void createUserComponent() {
        mNoteComponent = getAppComponent().plus(new NoteModule(this));
    }

    public NoteComponent getNoteComponent() {
        if (mNoteComponent == null) {
            createUserComponent();
        }
        return mNoteComponent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseNoteComponent();
    }

    public void releaseNoteComponent() {
        mNoteComponent = null;
    }

    private void replace(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.main_container, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showNoteDetail(int id) {
        replace(NoteDetailFragment.newInstance(id), NoteDetailFragment.TAG);
    }
}
