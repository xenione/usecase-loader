package apps.xenione.com.demoloader.presentation.view.contracts;

import apps.xenione.com.demoloader.presentation.viewModel.NoteViewModel;

/**
 * Created by Eugeni on 05/10/2016.
 */
public interface NoteListContract {

    void showProgress();

    void listNotes(NoteViewModel notes);

    void update();
}
