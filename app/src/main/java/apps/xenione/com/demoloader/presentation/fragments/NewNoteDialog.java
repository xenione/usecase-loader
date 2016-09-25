package apps.xenione.com.demoloader.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteDao;
import apps.xenione.com.demoloader.loaders.LoaderUseCase;
import apps.xenione.com.demoloader.presentation.App;
import apps.xenione.com.demoloader.usecases.AddNoteUseCase;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class NewNoteDialog extends DialogFragment {

    public static final int ADD_NEW_NOTE_LOADER_ID = 2;

    public interface OnNewNoteCallback {
        void onNoteAdded();
    }

    public static DialogFragment newInstance() {
        return new NewNoteDialog();
    }

    @Bind(R.id.add_note_title)
    TextView titleText;
    @Bind(R.id.add_note_body)
    TextView bodyText;
    @Bind(R.id.add_note_progress)
    ProgressBar progressBar;
    @Bind(R.id.add_note_info)
    TextView infoText;
    @Bind(R.id.add_new_cancel)
    Button cancelBtn;
    @Bind(R.id.add_new_save)
    Button saveBtn;
    @Bind(R.id.add_new_continue)
    Button continueBtn;

    private NoteDao mNoteDao;
    private LoaderManager mLoaderManager;
    private Note note;
    private OnNewNoteCallback mOnNewNoteCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoteDao = App.getNoteDao(getActivity());
        mLoaderManager = App.getLoaderManager(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLoaderManager.getLoader(ADD_NEW_NOTE_LOADER_ID) != null) {
            mLoaderManager.initLoader(ADD_NEW_NOTE_LOADER_ID, null, noteAddedLoaderCallback);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnNewNoteCallback = (OnNewNoteCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent must implement OnNewNoteCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_new_note, container, false);
        ButterKnife.bind(this, view);
        initViews(savedInstanceState);
        return view;
    }

    private void initViews(Bundle savedInstanceState) {
        cancelBtn.setOnClickListener(cancelAction);
        saveBtn.setOnClickListener(saveAction);
        continueBtn.setOnClickListener(continueAction);
        if (savedInstanceState != null) {
            boolean inProgress = savedInstanceState.getBoolean("isInProgress", false);
            if (inProgress) {
                showProgress();
            } else {
                showNote();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isInProgress", isProgress());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private View.OnClickListener cancelAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            mLoaderManager.destroyLoader(ADD_NEW_NOTE_LOADER_ID);
        }
    };

    private View.OnClickListener saveAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = titleText.getText().toString();
            String body = bodyText.getText().toString();
            note = new Note(title, body);
            mLoaderManager.restartLoader(ADD_NEW_NOTE_LOADER_ID, null, noteAddedLoaderCallback);
        }
    };

    private View.OnClickListener continueAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            mLoaderManager.destroyLoader(ADD_NEW_NOTE_LOADER_ID);
        }
    };

    private LoaderUseCase.LoaderUseCaseCallback<Void> noteAddedLoaderCallback = new LoaderUseCase.LoaderUseCaseCallback<Void>() {

        @Override
        public void onStart() {
            showProgress();
        }

        @Override
        public void onSuccess(Void aVoid) {
            showContinue();
            mOnNewNoteCallback.onNoteAdded();
        }

        @Override
        public void onFailure(Exception exception) {
            showError();
        }

        @Override
        public LoaderUseCase<Void> onCreateUseCaseLoader(Bundle args) {
            return new LoaderUseCase<>(new AddNoteUseCase(mNoteDao, note));
        }
    };

    private boolean isProgress() {
        return progressBar.isShown();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        titleText.setVisibility(View.GONE);
        bodyText.setVisibility(View.GONE);
        infoText.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.GONE);
        saveBtn.setVisibility(View.GONE);
        continueBtn.setVisibility(View.GONE);
    }

    public void showContinue() {
        progressBar.setVisibility(View.GONE);
        titleText.setVisibility(View.GONE);
        bodyText.setVisibility(View.GONE);
        infoText.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.GONE);
        saveBtn.setVisibility(View.GONE);
        continueBtn.setVisibility(View.VISIBLE);
    }

    public void showNote() {
        progressBar.setVisibility(View.GONE);
        titleText.setVisibility(View.VISIBLE);
        bodyText.setVisibility(View.VISIBLE);
        infoText.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.VISIBLE);
        continueBtn.setVisibility(View.GONE);
    }

    public void showError() {
        progressBar.setVisibility(View.GONE);
        titleText.setVisibility(View.GONE);
        bodyText.setVisibility(View.GONE);
        infoText.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.GONE);
        saveBtn.setVisibility(View.GONE);
        continueBtn.setVisibility(View.VISIBLE);
    }
}
