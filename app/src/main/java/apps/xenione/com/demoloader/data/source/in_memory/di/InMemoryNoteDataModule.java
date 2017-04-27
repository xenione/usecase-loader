package apps.xenione.com.demoloader.data.source.in_memory.di;

import apps.xenione.com.demoloader.data.di.DataModule;
import apps.xenione.com.demoloader.data.source.in_memory.InMemoryNoteDataSource;
import dagger.Module;
import dagger.Provides;


@Module
public class InMemoryNoteDataModule extends DataModule<InMemoryNoteDataSource> {

    public InMemoryNoteDataModule() {
    }

    @Provides
    public InMemoryNoteDataSource providesInMemoryNoteDataSource() {
        return new InMemoryNoteDataSource();
    }


}
