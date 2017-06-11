package apps.xenione.com.demoloader.presentation.notes.notelist;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.presentation.notes.notelist.note_item.ItemViewHolderFactory;
import apps.xenione.com.demoloader.presentation.notes.notelist.note_item.NoteItemContract;


public class NoteListAdapter extends RecyclerView.Adapter<NoteItemContract.View> {

    private List<Note> mNotes;
    private ItemViewHolderFactory mFactory;

    public NoteListAdapter(ItemViewHolderFactory factory, List<Note> notes) {
        mFactory = factory;
        mNotes = notes;
    }

    public void setData(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        this.notifyDataSetChanged();
    }

    public void updateDate(Note note) {
        int index = mNotes.indexOf(note);
        if (index < 0) {
            throw new IllegalArgumentException("Note can not be updated");
        }
        this.notifyItemChanged(index);
    }

    @Override
    public NoteItemContract.View onCreateViewHolder(ViewGroup parent, int viewType) {
        return mFactory.make(parent, this);
    }

    @Override
    public void onBindViewHolder(NoteItemContract.View holder, int position) {
        holder.set(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
