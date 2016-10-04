package apps.xenione.com.demoloader.presentation.fragments;

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

import apps.xenione.com.demoloader.Domain.Note;
import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.presenters.NewNotePresenter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */

public interface NoteViewContract {

    void showProgress();

    void showContinue();

    void showEditNote();

    void showError();

    public class NewNoteDialog extends DialogFragment implements NoteViewContract {

        public interface OnNewNoteAddCallback {
            void onNewNoteAdded();
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
            getDialog().setTitle("Add new Note");
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
            presenter = new NewNotePresenter(getActivity());
            presenter.attach(this);
            presenter.init();
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            presenter.attach(this);
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
                String title = titleText.getText().toString();
                String body = bodyText.getText().toString();
                presenter.save(new Note(title, body));
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
                mOnNewNoteAddCallback.onNewNoteAdded();
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
}
