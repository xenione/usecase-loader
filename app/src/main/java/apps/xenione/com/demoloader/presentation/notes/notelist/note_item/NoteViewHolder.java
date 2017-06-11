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

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.presentation.notes.notelist.NoteListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteViewHolder extends NoteItemContract.View {

    @BindView(R.id.note_list_row_name)
    public TextView nameView;

    @BindView(R.id.note_list_row_favorite)
    public CheckBox favoriteView;

    @BindView(R.id.progress)
    public ProgressBar progressBar;

    private NoteItemContract.Presenter mPresenter;
    private NoteListAdapter mAdapter;
    private Note note;
    private boolean showProgress;

    public NoteViewHolder(NoteItemContract.Presenter presenter, NoteListAdapter adapter, View v) {
        super(v);
        mPresenter = presenter;
        mAdapter = adapter;
        ButterKnife.bind(this, v);
    }

    @Override
    public void set(final Note note) {
        this.note = note;
        mPresenter.setView(this);
        mPresenter.start();
        nameView.setText(note.getTitle() + "\n" + note.getAuthor());
        favoriteView.setOnCheckedChangeListener(null);
        favoriteView.setChecked(note.isFavorite());
        favoriteView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPresenter.setFavorite(note);
                } else {
                    mPresenter.unsetFavorite(note);
                }
            }
        });
        progressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public Note getNote() {
        return note;
    }

    @Override
    public void update(Note note) {
        mAdapter.updateDate(note);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        showProgress = active;
        mAdapter.updateDate(note);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}