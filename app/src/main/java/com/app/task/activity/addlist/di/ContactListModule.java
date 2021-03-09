package com.app.task.activity.addlist.di;

import com.app.task.activity.addlist.mvp.ContactListAdapter;
import com.app.task.activity.addlist.mvp.ContactListModel;
import com.app.task.activity.addlist.mvp.ContactListPresenter;
import com.app.task.activity.addlist.mvp.ContactListView;
import com.app.task.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ContactListModule {


    private final AppCompatActivity activity;

    @Activity
    public ContactListModule(AppCompatActivity activity) {
        this.activity = activity;

    }

    @Activity
    @Provides
    public ContactListView contactListView() {
        return new ContactListView(activity);
    }

    @Activity
    @Provides
    public ContactListModel contactListModel() {
        return new ContactListModel(activity);
    }

    @Activity
    @Provides
    public ContactListPresenter loginPresenter(ContactListView contactListView, ContactListModel contactListModel) {
        return new ContactListPresenter(contactListView, contactListModel, activity);
    }

}
