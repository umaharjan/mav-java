package com.app.task.activity.addlist.di;


import com.app.task.activity.addlist.ContactListActivity;
import com.app.task.app.Activity;
import com.app.task.app.AppComponent;

import dagger.Component;

@Activity
@Component(modules = ContactListModule.class, dependencies = AppComponent.class)

public interface ContactListComponent {
    void inject(ContactListActivity contactListActivity);
}
