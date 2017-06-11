package apps.xenione.com.demoloader.presentation.notes.notelist.note_item;
/*
Copyright 10/06/2017 Eugeni Josep Senent i Gabriel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import android.os.Bundle;
import android.support.v4.app.LoaderManager;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.presentation.use_cases.SetFavoriteNotesUseCase;
import apps.xenione.com.demoloader.presentation.use_cases.UseCase;
import apps.xenione.com.demoloader.presentation.use_cases.UseCaseLoader;

public class NoteItemPresenter implements NoteItemContract.Presenter {

    private UseCase<SetFavoriteNotesUseCase.SetFavoriteInput, Void> mSetFavoriteNoteUseCase;
    private LoaderManager mLoaderManager;
    private NoteItemContract.View mView;

    private UseCaseLoader.UseCaseLoaderCallback<Void> mSetFavoriteNoteCallback = new UseCaseLoader.UseCaseLoaderCallback<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            mView.setLoadingIndicator(false);
            //mView.update(null);
        }

        @Override
        public void onFailure(Exception e) {
            mView.setLoadingIndicator(false);
            mView.showError("fail to set/unsetFavorite");
        }

        @Override
        public UseCaseLoader<Void> onCreateUseCaseLoader(Bundle args) {
            return new UseCaseLoader<>(mSetFavoriteNoteUseCase);
        }
    };

    public NoteItemPresenter(LoaderManager loaderManager, UseCase<SetFavoriteNotesUseCase.SetFavoriteInput, Void> useCase) {
        mLoaderManager = loaderManager;
        mSetFavoriteNoteUseCase = useCase;
    }

    @Override
    public void start() {
        int id = mView.getNote().getId();
        if (mLoaderManager.getLoader(110 + id) != null) {
            mLoaderManager.initLoader(110 + id, null, mSetFavoriteNoteCallback);
        }
    }

    @Override
    public void setFavorite(Note note) {
        mView.setLoadingIndicator(true);
        mSetFavoriteNoteUseCase
                .<SetFavoriteNotesUseCase.SetFavoriteParamBuilder>getParamBuilder()
                .withFavorite(true)
                .withNote(note)
                .apply();
        mLoaderManager.restartLoader(110 + note.getId(), null, mSetFavoriteNoteCallback);
    }

    @Override
    public void unsetFavorite(Note note) {
        mView.setLoadingIndicator(true);
        mSetFavoriteNoteUseCase
                .<SetFavoriteNotesUseCase.SetFavoriteParamBuilder>getParamBuilder()
                .withFavorite(false)
                .withNote(note)
                .apply();
        mLoaderManager.restartLoader(110 + note.getId(), null, mSetFavoriteNoteCallback);
    }

    @Override
    public void setView(NoteItemContract.View view) {
        mView = view;
        int id = mView.getNote().getId();
        if (mLoaderManager.getLoader(110 + id) != null) {
            mLoaderManager.getLoader(110 + id).cancelLoad();
        }
    }
}
