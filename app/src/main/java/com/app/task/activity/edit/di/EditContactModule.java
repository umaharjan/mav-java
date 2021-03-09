package com.app.task.activity.edit.di;

import com.app.task.activity.addcontact.mvp.AddContactModel;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.addcontact.mvp.SuccessDialogView;
import com.app.task.activity.edit.mvp.EditContactModel;
import com.app.task.activity.edit.mvp.EditContactPresetnter;
import com.app.task.activity.edit.mvp.EditContactView;
import com.app.task.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module
public
class EditContactModule {


    private final AppCompatActivity activity;

    @Activity
    public EditContactModule(AppCompatActivity activity) {
        this.activity = activity;

    }
    @Activity
    @Provides
    public EditContactView editContactView(SuccessDialogView successDialogView) {
        return new EditContactView(activity,successDialogView);
    }

    @Activity
    @Provides
    public EditContactModel editContactModel() {
        return new EditContactModel(activity);
    }

    @Activity
    @Provides
    public EditContactPresetnter loginPresenter(EditContactView editContactView, EditContactModel editContactModel) {
        return new EditContactPresetnter(editContactView, editContactModel,activity);
    }

    @Activity
    @Provides
    public SuccessDialogView errrorDialogView() {
        return new SuccessDialogView(activity);
    }
}
