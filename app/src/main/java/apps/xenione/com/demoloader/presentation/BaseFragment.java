package apps.xenione.com.demoloader.presentation;

import android.support.v4.app.Fragment;

import apps.xenione.com.demoloader.presentation.di.AppComponent;
import apps.xenione.com.demoloader.presentation.notes.NoteActivity;
import apps.xenione.com.demoloader.presentation.notes.di.NoteComponent;

public class BaseFragment extends Fragment {


    public AppComponent getAppComponent() {
        return ((NoteActivity) getActivity()).getAppComponent();
    }


    public NoteComponent getNoteComponent() {
        return ((NoteActivity) getActivity()).getNoteComponent();
    }
}
