package com.app.task.activity.edit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.app.task.activity.addcontact.di.AddContactModule;
import com.app.task.activity.addcontact.di.DaggerAddContactComponent;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.app.task.activity.edit.di.DaggerEditContactComponent;
import com.app.task.activity.edit.di.EditContactModule;
import com.app.task.activity.edit.mvp.EditContactPresetnter;
import com.app.task.activity.edit.mvp.EditContactView;
import com.app.task.app.AppApplication;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class EditContactActivity extends AppCompatActivity {

    @Inject
    EditContactView editContactView;

    @Inject
    EditContactPresetnter editContactPresetnter;

    public  static ContactInfoModel info;
    public static void start(Context context,ContactInfoModel result) {
        info=result;
        context.startActivity(new Intent(context, EditContactActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerEditContactComponent.builder().appComponent(AppApplication.get(this).appComponent())
                .editContactModule(new EditContactModule(this))
                .build()
                .inject(this);
        setContentView(editContactView);
        editContactPresetnter.onCreate(info);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       // addContactPresenter.onDestroy();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {

            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    editContactPresetnter.setImage(selectedImage);
                }

                break;
        }
    }


}
