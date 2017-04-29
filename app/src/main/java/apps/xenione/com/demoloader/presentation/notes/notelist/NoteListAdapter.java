package apps.xenione.com.demoloader.presentation.notes.notelist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.data.Note;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.note_list_row_name)
        public TextView nameView;

        @BindView(R.id.note_list_row_favorite)
        public CheckBox favoriteView;

        private NoteListContract.Presenter mPresenter;

        public NoteViewHolder(NoteListContract.Presenter presenter, View v) {
            super(v);
            mPresenter = presenter;
            ButterKnife.bind(this, v);
        }

        public void setData(final Note note) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.showDetail(note);
                }
            });
            nameView.setText(note.getTitle() + "\n" + note.getAuthor());
            favoriteView.setOnCheckedChangeListener(null);
            favoriteView.setChecked(note.isFavorite());
            favoriteView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mPresenter.setFavorite(note);
                }
            });
        }

    }

    private List<Note> mNotes;
    private NoteListContract.Presenter mPresenter;

    public NoteListAdapter(NoteListContract.Presenter presenter, List<Note> notes) {
        mPresenter = presenter;
        mNotes = notes;
    }

    public void setData(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        this.notifyDataSetChanged();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);
        return new NoteViewHolder(mPresenter, v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.setData(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
