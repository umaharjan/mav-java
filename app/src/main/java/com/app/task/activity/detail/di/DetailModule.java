package com.app.task.activity.detail.di;

import com.app.task.activity.addcontact.mvp.AddContactModel;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.addcontact.mvp.SuccessDialogView;
import com.app.task.activity.detail.mvp.DetaiiModel;
import com.app.task.activity.detail.mvp.DetailPresenter;
import com.app.task.activity.detail.mvp.DetailView;
import com.app.task.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module
public
class DetailModule {


    private final AppCompatActivity activity;

    @Activity
    public DetailModule(AppCompatActivity activity) {
        this.activity = activity;

    }
    @Activity
    @Provides
    public DetailView detailView() {
        return new DetailView(activity);
    }

    @Activity
    @Provides
    public DetaiiModel detaiiModel() {
        return new DetaiiModel(activity);
    }

    @Activity
    @Provides
    public DetailPresenter loginPresenter(DetailView detailView, DetaiiModel detaiiModel) {
        return new DetailPresenter(detailView, detaiiModel,activity);
    }
}
