package apps.xenione.com.demoloader.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.data.Note;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public void setNote(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.title.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getItem(int position) {
        return notes.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.note_title)
        public TextView title;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
