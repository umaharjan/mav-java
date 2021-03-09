package com.app.task.activity.addcontact.mvp;

import android.app.Activity;
import android.content.Intent;

import com.app.task.activity.addlist.ContactListActivity;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.app.task.activity.detail.DetailActivity;
import com.app.task.activity.utils.ImagePicker;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.RealmResults;

public class AddContactModel {

    private final AppCompatActivity activity;

    public AddContactModel(AppCompatActivity activity) {
        this.activity = activity;

    }


    public void  imagePicker(Integer intentId){
       Intent chooseImageIntent =new Intent() ;
        chooseImageIntent=  ImagePicker.getPickImageIntent(activity);
        activity.startActivityForResult(chooseImageIntent, intentId);
    }


    public void getDetailView(){

        ContactListActivity.start(activity) ;
        activity.finish();
    }

    public void  finished(){
        activity.finish();
    }


}
