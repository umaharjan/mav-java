package com.app.task.activity.addcontact.di;

import com.app.task.activity.addcontact.AddContactActivity;
import com.app.task.app.Activity;
import com.app.task.app.AppComponent;

import dagger.Component;

@Activity
@Component(modules = AddContactModule.class, dependencies = AppComponent.class)
public interface AddContactComponent {
    void inject(AddContactActivity addContactActivity);
}
