package com.app.task.activity.detail.mvp;

import com.app.task.activity.addcontact.AddContactActivity;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.app.task.activity.detail.DetailActivity;
import com.app.task.activity.edit.EditContactActivity;

import androidx.appcompat.app.AppCompatActivity;

public class DetaiiModel {


    private final AppCompatActivity activity;

    public DetaiiModel(AppCompatActivity activity) {
        this.activity = activity;

    }


    public void getEditView(ContactInfoModel info){
        EditContactActivity.start(activity,info) ;
    }
    public void getFinished(){
        activity.finish();
    }
}
