package com.app.task.activity.addlist.mvp;

import com.app.task.activity.addcontact.AddContactActivity;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.app.task.activity.detail.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

public class ContactListModel {

    private final AppCompatActivity activity;

    public ContactListModel(AppCompatActivity activity) {
        this.activity = activity;

    }


    public void getDetailView(ContactInfoModel info){
        DetailActivity.start(activity,info) ;
    }
    public void getAddView(){
        AddContactActivity.start(activity) ;
    }



}
