package apps.xenione.com.demoloader.presentation.view.fragments;

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

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.adapters.NoteAdapter;
import apps.xenione.com.demoloader.presentation.presenters.NoteListPresenter;
import apps.xenione.com.demoloader.presentation.view.contracts.NoteListContract;
import apps.xenione.com.demoloader.presentation.viewModel.NoteViewModel;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 04/10/2016.
 */

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
        adapter = new NoteAdapter();
        list.setAdapter(adapter);
        fab.setOnClickListener(onAddNoteClickListener);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new NoteListPresenter(getActivity());
        presenter.attach(this);
        presenter.init();
    }

    @Override
    public void update() {
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
    public void listNotes(NoteViewModel notes) {
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
