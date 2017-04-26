package apps.xenione.com.demoloader.presentation.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context mContext;

    public AppModule(Application app) {
        mContext = app;
    }

    @Singleton
    @Provides
    public Context providesContext() {
        return mContext;
    }

}
