package apps.xenione.com.demoloader.presentation;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.fragments.NewNoteDialog;
import apps.xenione.com.demoloader.presentation.fragments.NoteListFragment;

import static apps.xenione.com.demoloader.presentation.fragments.NoteListFragment.OnNoteListCallback;

public class MainActivity extends AppCompatActivity implements OnNoteListCallback, NewNoteDialog.OnNewNoteCallback {

    private NoteListFragment mNoteListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mNoteListFragment == null) {
            mNoteListFragment = (NoteListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        }
    }

    @Override
    public void onAddNoteClick() {
        FragmentManager fm = App.getFragmentManager(this);
        DialogFragment prevDialog = (DialogFragment) fm.findFragmentByTag("dialog");
        if (prevDialog != null) {
            prevDialog.dismiss();
        }
        NewNoteDialog.newInstance().show(fm, "dialog");
    }

    @Override
    public void onNoteAdded() {
        mNoteListFragment.refreshList();
    }
}
