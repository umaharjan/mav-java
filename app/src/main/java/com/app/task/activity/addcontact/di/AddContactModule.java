package com.app.task.activity.addcontact.di;


import com.app.task.activity.addcontact.mvp.AddContactModel;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.addcontact.mvp.SuccessDialogView;
import com.app.task.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module

public class AddContactModule {


    private final AppCompatActivity activity;

    @Activity
    public AddContactModule(AppCompatActivity activity) {
        this.activity = activity;

    }
    @Activity
    @Provides
    public AddContactView addContactView(SuccessDialogView successDialogView) {
        return new AddContactView(activity,successDialogView);
    }

    @Activity
    @Provides
    public AddContactModel addContactModel() {
        return new AddContactModel(activity);
    }

    @Activity
    @Provides
    public AddContactPresenter loginPresenter(AddContactView addContactView, AddContactModel addContactModel) {
        return new AddContactPresenter(addContactView, addContactModel,activity);
    }

    @Activity
    @Provides
    public SuccessDialogView errrorDialogView() {
        return new SuccessDialogView(activity);
    }

}
