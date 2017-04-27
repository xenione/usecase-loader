package apps.xenione.com.demoloader.data.source.in_memory.di;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.source.DataSource;
import apps.xenione.com.demoloader.data.source.in_memory.InMemoryNoteDataSource;
import dagger.Module;
import dagger.Provides;


@Module
public class InMemoryNoteDataModule{

    public InMemoryNoteDataModule() {
    }

    @Provides
    public DataSource<Note> providesInMemoryNoteDataSource() {
        return new InMemoryNoteDataSource();
    }
}
