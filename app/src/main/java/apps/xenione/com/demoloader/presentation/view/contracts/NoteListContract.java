package apps.xenione.com.demoloader.presentation.view.contracts;

import java.util.List;

import apps.xenione.com.demoloader.domain.Note;

/**
 * Created by Eugeni on 05/10/2016.
 */
public interface NoteListContract {

    void showProgress();

    void listNotes(List<Note> notes);

    void update();
}
