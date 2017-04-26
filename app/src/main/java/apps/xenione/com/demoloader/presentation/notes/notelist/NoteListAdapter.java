package apps.xenione.com.demoloader.presentation.notes.notelist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.data.Note;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.UserViewHolder> {

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_list_row_name)
        public TextView nameView;

        @BindView(R.id.user_list_row_email)
        public TextView emailView;

        @BindView(R.id.user_list_row_delete)
        public View deleteView;

        @BindView(R.id.user_list_row_favorite)
        public CheckBox favoriteView;

        @BindView(R.id.user_list_row_picture)
        public ImageView pictureView;

        public UserViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    private List<Note> mNotes;
    private NoteListContract.Presenter mPresenter;

    public NoteListAdapter(NoteListContract.Presenter presenter, List<Note> notes) {
        mPresenter = presenter;
        mNotes = notes;
    }

    public void setUsers(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        this.notifyDataSetChanged();
    }

    @Override
    public NoteListAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final Note user = mNotes.get(position);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
