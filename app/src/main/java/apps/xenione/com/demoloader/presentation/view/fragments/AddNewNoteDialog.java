package apps.xenione.com.demoloader.presentation.view.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import apps.xenione.com.demoloader.cuore.Note;
import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.App;
import apps.xenione.com.demoloader.presentation.presenters.NewNotePresenter;
import apps.xenione.com.demoloader.presentation.view.contracts.AddNewNoteContract;
import apps.xenione.com.demoloader.presentation.view.widgets.FieldView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */

public class AddNewNoteDialog extends DialogFragment implements AddNewNoteContract {

    public interface OnNewNoteAddCallback {
        void onNewNoteAdded();
    }

    public static DialogFragment newInstance() {
        return new AddNewNoteDialog();
    }

    @Bind(R.id.title_fieldView)
    FieldView titleText;
    @Bind(R.id.value_fieldView)
    FieldView bodyText;
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

    private NewNotePresenter presenter;
    private OnNewNoteAddCallback mOnNewNoteAddCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_new_note, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        cancelBtn.setOnClickListener(cancelAction);
        saveBtn.setOnClickListener(saveAction);
        continueBtn.setOnClickListener(continueAction);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setTitle("Add New Note");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnNewNoteAddCallback = (OnNewNoteAddCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent must implement OnNewNoteListCallback");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new NewNotePresenter(App.getLoaderManager(getActivity()));
        presenter.bind(this);
        presenter.init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbind();
        ButterKnife.unbind(this);
    }

    private View.OnClickListener cancelAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            presenter.finish();
        }
    };

    private View.OnClickListener saveAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = titleText.getText();
            String body = bodyText.getText();
            presenter.execute(App.getAddNoteUseCase(getActivity(), new Note(title, body)));
        }
    };

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        presenter.cancel();
    }

    private View.OnClickListener continueAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            presenter.finish();
        }
    };

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

        mOnNewNoteAddCallback.onNewNoteAdded();
    }

    public void showEditNote() {
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

