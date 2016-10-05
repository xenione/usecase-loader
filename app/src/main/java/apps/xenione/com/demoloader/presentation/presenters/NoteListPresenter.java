package apps.xenione.com.demoloader.presentation.presenters;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;

import java.util.List;

import apps.xenione.com.demoloader.Domain.Note;
import apps.xenione.com.demoloader.infrastructure.loaders.UseCaseLoader;
import apps.xenione.com.demoloader.infrastructure.presenters.BasePresenter;
import apps.xenione.com.demoloader.presentation.App;
import apps.xenione.com.demoloader.presentation.view.contracts.NoteListContract;

/**
 * Created by Eugeni on 04/10/2016.
 */
public class NoteListPresenter extends BasePresenter<NoteListContract> {

    public static final int LIST_NOTE_LOADER_ID = 101;

    private LoaderManager mLoaderManager;
    private FragmentActivity mFa;

    public NoteListPresenter(FragmentActivity fa) {
        mFa = fa;
        mLoaderManager = App.getLoaderManager(fa);
    }

    public void init() {
        mView.showProgress();
        mLoaderManager.initLoader(LIST_NOTE_LOADER_ID, null, getListNoteLoaderCallback);
    }

    private UseCaseLoader.UseCaseLoaderCallback<List<Note>> getListNoteLoaderCallback = new UseCaseLoader.UseCaseLoaderCallback<List<Note>>() {

        @Override
        public void onSuccess(List<Note> notes) {
            mView.listNotes(notes);
        }

        @Override
        public void onFailure(Exception exception) {
            mView.listNotes(null);
        }

        @Override
        public UseCaseLoader<List<Note>> onCreateUseCaseLoader(Bundle args) {
            return new UseCaseLoader<>(App.getGetNoteUseCase(mFa));
        }
    };
}
