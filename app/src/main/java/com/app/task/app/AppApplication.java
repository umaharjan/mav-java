package com.app.task.app;

import android.app.Activity;

import android.app.Application;
import android.content.Context;

import com.app.task.BuildConfig;

import androidx.multidex.MultiDex;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static io.realm.RealmConfiguration.*;


public class AppApplication extends Application {
    private AppComponent appComponent;

    private static AppApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(getApplicationContext());
        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                         .allowWritesOnUiThread(true)
                        .build();

        Realm.setDefaultConfiguration(config);

        if (BuildConfig.DEBUG) {

        }
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppApplication get(Activity activity) {
        return (AppApplication) activity.getApplication();
    }

    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    public AppComponent appComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
