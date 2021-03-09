package com.app.task.activity.addlist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.app.task.activity.addcontact.di.AddContactModule;
import com.app.task.activity.addcontact.di.DaggerAddContactComponent;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.addlist.di.ContactListModule;
import com.app.task.activity.addlist.di.DaggerContactListComponent;
import com.app.task.activity.addlist.mvp.ContactListPresenter;
import com.app.task.activity.addlist.mvp.ContactListView;
import com.app.task.app.AppApplication;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ContactListActivity extends AppCompatActivity {

    @Inject
    ContactListView contactListView;

    @Inject
    ContactListPresenter contactListPresenter;


    public static void start(Context context) {
        context.startActivity(new Intent(context, ContactListActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerContactListComponent.builder().appComponent(AppApplication.get(this).appComponent())
                .contactListModule(new ContactListModule(this))
                .build()
                .inject(this);
        setContentView(contactListView);
        contactListPresenter.onCreate();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       // addContactPresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
