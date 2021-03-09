package com.app.task.activity.edit.mvp;

import android.content.Intent;

import com.app.task.activity.addlist.ContactListActivity;
import com.app.task.activity.utils.ImagePicker;

import androidx.appcompat.app.AppCompatActivity;

public class EditContactModel {


    private final AppCompatActivity activity;

    public EditContactModel(AppCompatActivity activity) {
        this.activity = activity;

    }

    public void  imagePicker(Integer intentId){
        Intent chooseImageIntent =new Intent() ;
        chooseImageIntent=  ImagePicker.getPickImageIntent(activity);
        activity.startActivityForResult(chooseImageIntent, intentId);
    }

    public void getListView(){

        ContactListActivity.start(activity) ;
        activity.finish();
    }

    public void  finished(){
        activity.finish();
    }
}
