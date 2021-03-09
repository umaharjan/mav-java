package com.app.task.activity.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.app.task.activity.addcontact.di.AddContactModule;
import com.app.task.activity.addcontact.di.DaggerAddContactComponent;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.app.task.activity.detail.di.DaggerDetailComponent;
import com.app.task.activity.detail.di.DetailModule;
import com.app.task.activity.detail.mvp.DetailPresenter;
import com.app.task.activity.detail.mvp.DetailView;
import com.app.task.app.AppApplication;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {

    @Inject
    DetailView detailView;

    @Inject
    DetailPresenter detailPresenter;

    public  static ContactInfoModel info;


    public static void start(Context context,ContactInfoModel result) {
        info=result;
        context.startActivity(new Intent(context, DetailActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDetailComponent.builder().appComponent(AppApplication.get(this).appComponent())
                .detailModule(new DetailModule(this))
                .build()
                .inject(this);
        setContentView(detailView);
        detailPresenter.onCreate(info);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       // addContactPresenter.onDestroy();
    }



}
