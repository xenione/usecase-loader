package apps.xenione.com.demoloader.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.viewModel.NoteViewModel;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private NoteViewModel notes;

    public NoteAdapter() {
    }

    public void setNote(NoteViewModel notes) {
        this.notes = notes;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(notes.getTitleFor(position));
        holder.body.setText(notes.getDescriptionFor(position));
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.note_title)
        public TextView title;
        @Bind(R.id.note_body)
        public TextView body;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
