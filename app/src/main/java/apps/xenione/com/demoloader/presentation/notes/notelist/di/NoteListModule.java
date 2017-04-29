package apps.xenione.com.demoloader.presentation.notes.notelist.di;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteRepository;
import apps.xenione.com.demoloader.presentation.notes.Navigation;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListAdapter;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListContract;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListPresenter;
import apps.xenione.com.demoloader.presentation.use_cases.GetNotesUseCase;
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
    public NoteListAdapter providesAdapter(NoteListContract.Presenter presenter, Context context) {
        return new NoteListAdapter(presenter, new ArrayList<Note>());
    }

    @Provides
    public Callable<List<Note>> providesGetNotesUseCase(NoteRepository repository) {
        return new GetNotesUseCase(repository);
    }

    @Provides
    NoteListContract.Presenter providerNoteListPresenter(LoaderManager loaderManager, Callable<List<Note>> useCase, Navigation navigation) {
        return new NoteListPresenter(loaderManager, useCase, navigation);
    }
}
