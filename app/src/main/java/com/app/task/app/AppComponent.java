package com.app.task.app;

import android.content.Context;
import dagger.Component;


@AppScope
@Component(modules = {AppModule.class})

public interface AppComponent {
    Context context();

}
