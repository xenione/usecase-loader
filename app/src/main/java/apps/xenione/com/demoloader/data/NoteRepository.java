package apps.xenione.com.demoloader.data;


import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import apps.xenione.com.demoloader.data.source.DataSource;

public interface NoteRepository {

    boolean setFavorite(Note note);

    boolean unsetFavorite(Note note);

    List<Note> getAll();

    Note getById(int id);

    List<Note> getByTitle(String title);

    class Impl implements NoteRepository {

        private DataSource<Note> mDataSource;

        private SparseArray<Note> mCache = new SparseArray<>();

        public Impl(DataSource<Note> dataSource) {
            mDataSource = dataSource;
        }

        private void loadDataIfNeeded() {
            if (mCache.size() != 0) {
                return;
            }
            for (Note note : mDataSource.getAll()) {
                mCache.put(note.getId(), note);
            }
        }

        @Override
        public List<Note> getAll() {
            loadDataIfNeeded();
            List<Note> notes = new ArrayList<>(mCache.size());
            for (int i = 0; i < mCache.size(); i++) {
                notes.add(mCache.valueAt(i));
            }
            return Collections.unmodifiableList(notes);
        }

        @Override
        public Note getById(int id) {
            loadDataIfNeeded();
            return mCache.get(id);
        }

        @Override
        public List<Note> getByTitle(String title) {
            loadDataIfNeeded();
            List<Note> notes = new ArrayList<>();
            for (int i = 0; i < mCache.size(); i++) {
                Note selectedNote = mCache.valueAt(i);
                if (selectedNote.mTitle.contains(title)) {
                    notes.add(selectedNote);
                }
            }
            return Collections.unmodifiableList(notes);
        }

        @Override
        public boolean unsetFavorite(Note note) {
            note.mFavorite = false;
            mDataSource.update(note);
            return true;
        }

        @Override
        public boolean setFavorite(Note note) {
            note.mFavorite = true;
            mDataSource.update(note);
            return true;
        }
    }
}
