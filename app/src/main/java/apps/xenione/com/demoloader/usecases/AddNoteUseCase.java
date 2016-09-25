package apps.xenione.com.demoloader.usecases;


import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteDao;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class AddNoteUseCase implements Runnable {

    private NoteDao mNoteDao;
    private Note mNote;

    public AddNoteUseCase(NoteDao noteDao, Note note) {
        mNoteDao = noteDao;
        mNote = note;
    }

    @Override
    public void run() {
        // TODO: remove when ready
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mNoteDao.save(mNote);
    }
}
