package com.app.task.activity.detail.di;


import com.app.task.activity.addcontact.di.AddContactModule;
import com.app.task.activity.detail.DetailActivity;
import com.app.task.app.Activity;
import com.app.task.app.AppComponent;

import dagger.Component;

@Activity
@Component(modules = DetailModule.class, dependencies = AppComponent.class)
 public interface DetailComponent {
 void inject(DetailActivity detailActivity);
}
