package apps.xenione.com.demoloader.usecases;

import java.util.List;
import java.util.concurrent.Callable;

import apps.xenione.com.demoloader.Domain.Note;
import apps.xenione.com.demoloader.Domain.NoteRepository;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class GetNoteUseCase implements Callable<List<Note>> {

    private NoteRepository mNoteRepository;

    public GetNoteUseCase(NoteRepository noteRepository) {
        mNoteRepository = noteRepository;
    }

    @Override
    public List<Note> call() throws Exception {
        return mNoteRepository.getAllOrderByDate();
    }
}
