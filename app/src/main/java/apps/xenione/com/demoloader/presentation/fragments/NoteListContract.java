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
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import apps.xenione.com.demoloader.Domain.Note;
import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.adapters.NoteAdapter;
import apps.xenione.com.demoloader.presentation.presenters.NoteListPresenter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 04/10/2016.
 */
public interface NoteListContract {

    void showProgress();

    void listNotes(List<Note> notes);

    public class NoteListFragment extends Fragment implements NoteListContract {

        public static final String TAG = "NoteListFragment";

        public interface OnNoteListCallback {
            void onAddNoteClick();
        }

        @Bind(R.id.list)
        RecyclerView list;
        @Bind(R.id.fab)
        FloatingActionButton fab;
        @Bind(R.id.progress)
        ProgressBar progress;

        private NoteAdapter adapter;
        private OnNoteListCallback mOnNoteListCallback;
        private NoteListPresenter presenter;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
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
            adapter = new NoteAdapter(new ArrayList<Note>());
            list.setAdapter(adapter);
            fab.setOnClickListener(onAddNoteClickListener);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            presenter = new NoteListPresenter(getActivity());
            presenter.attach(this);
            init();
        }

        public void init() {
            presenter.init();
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

        @Override
        public void showProgress() {
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        public void listNotes(List<Note> notes) {
            progress.setVisibility(View.INVISIBLE);
            adapter.setNote(notes);
        }

        private View.OnClickListener onAddNoteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Do you want to add new note?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", onConfirmAddNoteClickListener).show();
            }
        };

        private View.OnClickListener onConfirmAddNoteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnNoteListCallback.onAddNoteClick();
            }
        };

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
            presenter.detach(this);
        }
    }
}