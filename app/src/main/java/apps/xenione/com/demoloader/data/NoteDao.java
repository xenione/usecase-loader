package apps.xenione.com.demoloader.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Eugeni on 25/09/2016.
 */
public class NoteDao {

    private static List<Note> NOTES = new ArrayList<Note>() {{
        add(new Note("note 1", "this is the note 1"));
        add(new Note("note 2", "this is the note 2"));
        add(new Note("note 3", "this is the note 3"));
    }};

    private static Comparator<Note> DATE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note lhs, Note rhs) {
            return (int) (lhs.mCreateDate - rhs.mCreateDate);
        }
    };

    public List<Note> getAllOrderByDate() {
        Collections.sort(NOTES, DATE_COMPARATOR);
        return NOTES;
    }

    public boolean save(Note note) {
        return NOTES.add(note);
    }
}