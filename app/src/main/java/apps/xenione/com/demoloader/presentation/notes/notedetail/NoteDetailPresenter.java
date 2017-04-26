package apps.xenione.com.demoloader.presentation.notes.notedetail;

import apps.xenione.com.demoloader.data.NoteRepository;

public class NoteDetailPresenter implements NoteDetailContract.Presenter {

    private NoteRepository mNoteRepository;

    private NoteDetailContract.View mView;

    public NoteDetailPresenter(NoteRepository repository) {
        mNoteRepository = repository;
    }

    @Override
    public void fetchUsers(String uuid) {

    }

    @Override
    public void setView(NoteDetailContract.View view) {
        mView = view;
    }
}
