package apps.xenione.com.demoloader.presentation.notes.notelist;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteRepository;
import apps.xenione.com.demoloader.presentation.notes.Navigation;

public class NoteListPresenter implements NoteListContract.Presenter {

    private NoteRepository mNoteRepository;

    private Navigation mNavigation;

    private NoteListContract.View mView;

    public NoteListPresenter(NoteRepository repository, Navigation navigation) {
        mNoteRepository = repository;
        mNavigation = navigation;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void setFavorite(Note note) {
        mNoteRepository.setFavorite(note);
    }

    @Override
    public void unsetFavorite(Note note) {
        mNoteRepository.unsetFavorite(note);
    }

    @Override
    public void showDetail(Note note) {
        mNavigation.showNoteDetail(note.getId());
    }

    @Override
    public void setView(NoteListContract.View view) {
        mView = view;
    }

}
