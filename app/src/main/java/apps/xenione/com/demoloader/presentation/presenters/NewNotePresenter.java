package apps.xenione.com.demoloader.presentation.presenters;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import apps.xenione.com.demoloader.cuore.Note;
import apps.xenione.com.demoloader.infrastructure.loaders.UseCaseLoader;
import apps.xenione.com.demoloader.infrastructure.presenters.BasePresenter;
import apps.xenione.com.demoloader.presentation.App;
import apps.xenione.com.demoloader.presentation.view.contracts.AddNewNoteContract;

/**
 * Created by Eugeni on 03/10/2016.
 */
public class NewNotePresenter extends BasePresenter<AddNewNoteContract> {

    public static final int ADD_NEW_NOTE_LOADER_ID = 102;

    private LoaderManager mLoaderManager;
    private FragmentActivity mFa;
    private Note note;

    public NewNotePresenter(FragmentActivity fa) {
        mFa = fa;
        mLoaderManager = App.getLoaderManager(fa);
    }

    public void init() {
        mView.showEditNote();
        if (mLoaderManager.getLoader(ADD_NEW_NOTE_LOADER_ID) != null) {
            mView.showProgress();
            mLoaderManager.initLoader(ADD_NEW_NOTE_LOADER_ID, null, noteAddedLoaderCallback);
        }
    }

    public void finish() {
        mLoaderManager.destroyLoader(ADD_NEW_NOTE_LOADER_ID);
    }

    public void cancel() {
        Loader<Void> loader = mLoaderManager.getLoader(ADD_NEW_NOTE_LOADER_ID);
        if (loader != null) {
            loader.cancelLoad();
        }
    }

    public void save(Note note) {
        this.note = note;
        mLoaderManager.restartLoader(ADD_NEW_NOTE_LOADER_ID, null, noteAddedLoaderCallback);
    }

    private UseCaseLoader.UseCaseLoaderCallback<Void> noteAddedLoaderCallback = new UseCaseLoader.UseCaseLoaderCallback<Void>() {

        @Override
        public void onSuccess(Void aVoid) {
            mView.showContinue();
        }

        @Override
        public void onFailure(Exception exception) {
            mView.showError();
        }

        @Override
        public UseCaseLoader<Void> onCreateUseCaseLoader(Bundle args) {
            mView.showProgress();
            return new UseCaseLoader<>(App.getAddNoteUseCase(mFa, note));
        }
    };
}
