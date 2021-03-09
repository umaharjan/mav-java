package com.app.task.activity.edit.di;


import com.app.task.activity.addcontact.di.AddContactModule;
import com.app.task.activity.edit.EditContactActivity;
import com.app.task.app.Activity;
import com.app.task.app.AppComponent;

import dagger.Component;

@Activity
@Component(modules = EditContactModule.class, dependencies = AppComponent.class)
public interface EditContactComponent {
    void inject(EditContactActivity editContactActivity);
}
