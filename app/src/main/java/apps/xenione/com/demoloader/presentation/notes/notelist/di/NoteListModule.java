package apps.xenione.com.demoloader.presentation.notes.notelist.di;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteRepository;
import apps.xenione.com.demoloader.presentation.notes.Navigation;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListAdapter;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListContract;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListPresenter;
import apps.xenione.com.demoloader.presentation.notes.notelist.note_item.ItemViewHolderFactory;
import apps.xenione.com.demoloader.presentation.notes.notelist.note_item.NoteItemContract;
import apps.xenione.com.demoloader.presentation.notes.notelist.note_item.NoteItemPresenter;
import apps.xenione.com.demoloader.presentation.use_cases.GetNotesUseCase;
import apps.xenione.com.demoloader.presentation.use_cases.SetFavoriteNotesUseCase;
import apps.xenione.com.demoloader.presentation.use_cases.UseCase;
import dagger.Module;
import dagger.Provides;

/*
Copyright 27/04/2017 Eugeni Josep Senent i Gabriel

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
@Module
public class NoteListModule {

    @Provides
    public LinearLayoutManager providesLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    public RecyclerView.ItemDecoration providesItemDecorator(Context context, LinearLayoutManager linearLayoutManager) {
        return new DividerItemDecoration(context,
                linearLayoutManager.getOrientation());
    }

    @Provides
    public NoteListAdapter providesAdapter(ItemViewHolderFactory factory) {
        return new NoteListAdapter(factory, new ArrayList<Note>());
    }

    @Provides
    public ItemViewHolderFactory providesItemView(NoteItemContract.Presenter presenter) {
        return new ItemViewHolderFactory.Impl(presenter);
    }

    @Provides
    public UseCase<Void, List<Note>> providesGetNotesUseCase(NoteRepository repository) {
        return new GetNotesUseCase(repository);
    }

    @Provides
    public UseCase<SetFavoriteNotesUseCase.SetFavoriteInput, Void> providesSetFavoriteNoteUseCase(NoteRepository repository) {
        return new SetFavoriteNotesUseCase(repository);
    }

    @Provides
    NoteListContract.Presenter providerNoteListPresenter(LoaderManager loaderManager, UseCase<Void, List<Note>> useCase, Navigation navigation) {
        return new NoteListPresenter(loaderManager, useCase, navigation);
    }

    @Provides
    NoteItemContract.Presenter providerNoteItemPresenter(LoaderManager loaderManager,UseCase<SetFavoriteNotesUseCase.SetFavoriteInput, Void> useCase) {
        return new NoteItemPresenter(loaderManager, useCase);
    }
}
