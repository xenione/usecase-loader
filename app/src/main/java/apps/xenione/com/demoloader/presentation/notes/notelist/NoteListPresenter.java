package apps.xenione.com.demoloader.presentation.notes.notelist;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;

import java.util.List;
import java.util.concurrent.Callable;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.presentation.notes.Navigation;
import apps.xenione.com.demoloader.presentation.use_cases.UseCaseLoader;

public class NoteListPresenter implements NoteListContract.Presenter {

    private Callable<List<Note>> mGetNoteUseCase;

    private Navigation mNavigation;

    private NoteListContract.View mView;

    private LoaderManager mLoaderManager;

    private UseCaseLoader.UseCaseLoaderCallback<List<Note>> mGetNotesCallback = new UseCaseLoader.UseCaseLoaderCallback<List<Note>>() {
        @Override
        public void onSuccess(List<Note> notes) {
            mView.set(notes);
            mView.setLoadingIndicator(false);
        }

        @Override
        public void onFailure(Exception e) {
            mView.showError("fail load notes, try later");
        }

        @Override
        public UseCaseLoader<List<Note>> onCreateUseCaseLoader(Bundle args) {
            return new UseCaseLoader<>(mGetNoteUseCase);
        }
    };

    public NoteListPresenter(LoaderManager loaderManager, Callable<List<Note>> useCase, Navigation navigation) {
        mLoaderManager = loaderManager;
        mGetNoteUseCase = useCase;
        mNavigation = navigation;
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(100, null, mGetNotesCallback);
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
