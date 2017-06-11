package apps.xenione.com.demoloader.data;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apps.xenione.com.demoloader.data.source.DataSource;

public interface NoteRepository extends DataSource<Note> {

    List<Note> getAll();

    Note getById(int id);

    List<Note> getByTitle(String title);

    boolean setFavorite(Note note);

    boolean unsetFavorite(Note note);

    class Impl implements NoteRepository {

        private DataSource<Note> mDataSource;

        private Map<Integer, Note> mCache = new HashMap<>();

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
            return Collections.unmodifiableList(new ArrayList<>(mCache.values()));
        }

        @Override
        public void save(Note note) {
            mCache.put(note.getId(), note);
        }

        @Override
        public void update(Note note) {
            mDataSource.update(note);
        }

        @Override
        public void delete(Note note) {
            mCache.remove(note.getId());
            mDataSource.delete(note);
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
            for (Note note : mCache.values()) {
                if (note.getTitle().contains(title)) {
                    notes.add(note);
                }
            }
            return Collections.unmodifiableList(notes);
        }

        @Override
        public boolean unsetFavorite(Note note) {
            note.setFavorite(false);
            update(note);
            return true;
        }

        @Override
        public boolean setFavorite(Note note) {
            note.setFavorite(true);
            update(note);
            return true;
        }
    }
}
