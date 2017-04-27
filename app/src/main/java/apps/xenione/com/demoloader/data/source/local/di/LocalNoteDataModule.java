package apps.xenione.com.demoloader.data.source.local.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.di.DataModule;
import apps.xenione.com.demoloader.data.source.DataSource;
import apps.xenione.com.demoloader.data.source.NoteMapper;
import apps.xenione.com.demoloader.data.source.local.LocalNoteDataSource;
import apps.xenione.com.demoloader.data.source.local.LocalNoteMapper;
import dagger.Module;
import dagger.Provides;


@Module
public class LocalNoteDataModule extends DataModule<LocalNoteDataSource> {

    public LocalNoteDataModule() {
    }

    @Provides
    public Gson providesGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd' 'HH:mm:ss")
                .create();
    }

    @Provides
    public SharedPreferences providesSharePreference(Context context) {
        return context.getSharedPreferences("notes", Context.MODE_PRIVATE);
    }

    @Provides
    public NoteMapper<String> providesNoteMapper(Gson gson) {
        return new LocalNoteMapper(gson);
    }

    @Provides
    public DataSource<Note> providesLocalNoteDataSource(SharedPreferences shPreferences, NoteMapper<String> noteMapper) {
        return new LocalNoteDataSource(shPreferences, noteMapper);
    }

}
