package apps.xenione.com.demoloader;


import android.app.Application;

import apps.xenione.com.demoloader.presentation.di.AppComponent;
import apps.xenione.com.demoloader.presentation.di.AppModule;
import apps.xenione.com.demoloader.presentation.di.DaggerAppComponent;


public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
