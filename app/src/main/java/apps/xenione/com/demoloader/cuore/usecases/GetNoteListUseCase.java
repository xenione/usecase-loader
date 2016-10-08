package apps.xenione.com.demoloader.cuore.usecases;

import java.util.List;
import java.util.concurrent.Callable;

import apps.xenione.com.demoloader.cuore.Note;
import apps.xenione.com.demoloader.cuore.NoteRepository;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class GetNoteListUseCase implements Callable<List<Note>> {

    private NoteRepository mNoteRepository;

    public GetNoteListUseCase(NoteRepository noteRepository) {
        mNoteRepository = noteRepository;
    }

    @Override
    public List<Note> call() throws Exception {
        return mNoteRepository.getAllOrderByDate();
    }
}