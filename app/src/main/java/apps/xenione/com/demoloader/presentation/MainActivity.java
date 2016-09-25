package apps.xenione.com.demoloader.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.fragments.NoteListFragment;

public class MainActivity extends AppCompatActivity {

    NoteListFragment mNoteListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (mNoteListFragment == null) {
            mNoteListFragment = (NoteListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        }
    }
}
