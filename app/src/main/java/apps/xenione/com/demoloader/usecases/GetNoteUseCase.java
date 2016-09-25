package apps.xenione.com.demoloader.usecases;

import java.util.List;
import java.util.concurrent.Callable;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.NoteDao;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class GetNoteUseCase implements Callable<List<Note>> {

    private NoteDao mNoteDao;

    public GetNoteUseCase(NoteDao noteDao) {
        mNoteDao = noteDao;
    }

    @Override
    public List<Note> call() throws Exception {
        return mNoteDao.getAllOrderByDate();
    }
}
