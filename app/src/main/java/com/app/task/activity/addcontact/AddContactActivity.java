package com.app.task.activity.addcontact;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;


import com.app.task.activity.addcontact.di.AddContactModule;
import com.app.task.activity.addcontact.di.DaggerAddContactComponent;
import com.app.task.activity.addcontact.mvp.AddContactPresenter;
import com.app.task.activity.addcontact.mvp.AddContactView;
import com.app.task.activity.utils.ImagePicker;
import com.app.task.app.AppApplication;


import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AddContactActivity extends AppCompatActivity {

    @Inject
    AddContactView addContactView;

    @Inject
    AddContactPresenter addContactPresenter;


    public static void start(Context context) {
        context.startActivity(new Intent(context, AddContactActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAddContactComponent.builder().appComponent(AppApplication.get(this).appComponent())
                .addContactModule(new AddContactModule(this))
                .build()
                .inject(this);
        setContentView(addContactView);
        addContactPresenter.onCreate();

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
                  // File imageFile = new File(getRealPathFromURI(selectedImage));



                    addContactPresenter.setImage(selectedImage);
                }

                break;
        }
    }







    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
