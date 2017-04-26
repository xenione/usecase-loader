package apps.xenione.com.demoloader.data.source.in_memory.di;

import apps.xenione.com.demoloader.data.NoteRepository;
import apps.xenione.com.demoloader.data.di.DataModule;
import apps.xenione.com.demoloader.data.source.in_memory.InMemoryNoteDataSource;
import dagger.Module;
import dagger.Provides;


@Module
public class InMemoryNoteDataModule implements DataModule<InMemoryNoteDataSource> {

    public InMemoryNoteDataModule() {
    }

    @Provides
    public InMemoryNoteDataSource providesInMemoryNoteDataSource() {
        return new InMemoryNoteDataSource();
    }

    @Provides
    @Override
    public NoteRepository providesNoteRepository(InMemoryNoteDataSource dataSource) {
        return new NoteRepository.Impl(dataSource);
    }
}
