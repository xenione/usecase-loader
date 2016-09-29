package apps.xenione.com.demoloader.Domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Eugeni on 25/09/2016.
 */
public class NoteRepository {

    private static List<Note> NOTES = new ArrayList<Note>() {{
        add(new Note("Note 1", "This is the note 1"));
        add(new Note("Note 2", "This is the note 2"));
        add(new Note("Note 3", "This is the note 3"));
        add(new Note("Note 4", "This is the note 4"));
        add(new Note("Note 5", "This is the note 5"));
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