package apps.xenione.com.demoloader.presentation.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteDao;
import apps.xenione.com.demoloader.loaders.UseCaseLoader;
import apps.xenione.com.demoloader.presentation.App;
import apps.xenione.com.demoloader.presentation.adapters.NoteAdapter;
import apps.xenione.com.demoloader.usecases.GetNoteUseCase;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class NoteListFragment extends Fragment  {

    public static final String TAG = "NoteListFragment";

    public static final int LOAD_NOTE_LIST_LOADER_ID = 1;

    public interface OnNoteListCallback {
        void onAddNoteClick();
    }

    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private NoteAdapter adapter;
    private List<Note> notes = new ArrayList<>();
    private NoteDao mNoteDao;
    private OnNoteListCallback mOnNoteListCallback;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoteDao = App.getNoteDao(getContext());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnNoteListCallback = (OnNoteListCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent activity must implement OnNoteListCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(notes);
        list.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do you want to add new note?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mOnNoteListCallback.onAddNoteClick();
                            }
                        }).show();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOAD_NOTE_LIST_LOADER_ID, null, noteListLoaderCallback);
    }

    public void refreshList() {
        getLoaderManager().restartLoader(LOAD_NOTE_LIST_LOADER_ID, null, noteListLoaderCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private UseCaseLoader.UseCaseLoaderCallback<List<Note>> noteListLoaderCallback = new UseCaseLoader.UseCaseLoaderCallback<List<Note>>() {

        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(List<Note> data) {
            adapter.setNote(data);
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(getContext(), "something goes wronggg!!!", Toast.LENGTH_LONG).show();
        }

        @Override
        public UseCaseLoader<List<Note>> onCreateUseCaseLoader(Bundle args) {
            return new UseCaseLoader<>(new GetNoteUseCase(mNoteDao));
        }
    };
}
