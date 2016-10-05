package apps.xenione.com.demoloader.presentation.view.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.App;
import apps.xenione.com.demoloader.presentation.view.contracts.NoteListContract;
import apps.xenione.com.demoloader.presentation.view.fragments.AddNewNoteDialog;
import apps.xenione.com.demoloader.presentation.view.fragments.NoteListFragment;


public class MainActivity extends AppCompatActivity implements NoteListFragment.OnNoteListCallback,
        AddNewNoteDialog.OnNewNoteAddCallback {

    NoteListContract mNoteListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteListFragment = (NoteListContract) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    @Override
    public void onAddNoteClick() {
        FragmentManager fm = App.getFragmentManager(this);
        DialogFragment prevDialog = (DialogFragment) fm.findFragmentByTag("dialog");
        if (prevDialog != null) {
            prevDialog.dismiss();
        }
        AddNewNoteDialog.newInstance().show(fm, "dialog");
    }

    @Override
    public void onNewNoteAdded() {
        mNoteListFragment.update();
    }
}
