package apps.xenione.com.demoloader.data.source.local;

import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.source.DataSource;
import apps.xenione.com.demoloader.data.source.NoteMapper;


public class LocalNoteDataSource implements DataSource<Note> {

    private SharedPreferences mShPref;

    private NoteMapper<String> mMapper;

    public LocalNoteDataSource(SharedPreferences shPref, NoteMapper<String> mapper) {
        mShPref = shPref;
        mMapper = mapper;
    }

    @Override
    public List<Note> getAll() {
        Map<String, String> map = (Map<String, String>) mShPref.getAll();
        List<Note> notes = new ArrayList<>(map.size());
        for (String value : map.values()) {
            notes.add(mMapper.from(value));
        }
        return Collections.unmodifiableList(notes);
    }

    @Override
    public void save(Note note) {
        String key = String.valueOf(note.getId());
        if (exist(key)) {
            throw new IllegalArgumentException("note already exist");
        }
        mShPref.edit().putString(key, mMapper.to(note)).apply();
    }

    @Override
    public void update(Note note) {
        String key = String.valueOf(note.getId());
        if (!exist(key)) {
            throw new IllegalArgumentException("note not exist");
        }
        mShPref.edit().putString(key, mMapper.to(note)).apply();
    }

    @Override
    public void delete(Note note) {
        String key = String.valueOf(note.getId());
        if (!exist(key)) {
            throw new IllegalArgumentException("note not exist");
        }
        mShPref.edit().remove(key).apply();
    }

    private boolean exist(String key) {
        String value = mShPref.getString(key, "");
        return !TextUtils.isEmpty(value);
    }
}
