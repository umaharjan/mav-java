package com.app.task.activity.addcontact.mvp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.app.task.activity.dbmanager.ContactInfoModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class AddContactPresenter {
    private AddContactModel addconatctModel;
    private AddContactView addContactView;
    private AppCompatActivity appCompatActivity;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Realm realm;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public static final int CameraPhotoRequest = 1;
    public String imagePath;
    public AddContactPresenter(AddContactView addContactView, AddContactModel addconatctModel, AppCompatActivity appCompatActivity) {
        this.addconatctModel = addconatctModel;
        this.addContactView = addContactView;
        this.appCompatActivity = appCompatActivity;
    }


    @SuppressLint("CheckResult")
    public void onclick() {

        addContactView.getButtonObservable().subscribe(o -> {
            insertContactDb();
        });

        addContactView.getImageObservable().doOnNext(o->{
            if( checkAndRequestPermissions()==true){
                addconatctModel.imagePicker(CameraPhotoRequest);
            }

        })
              .subscribe();

        addContactView.getOKObservable().doOnNext(o->

        { addconatctModel.getDetailView();
                addContactView.dismissDailog();
        }).subscribe();


        addContactView.getbackObservable().doOnNext(o->addconatctModel.finished()).subscribe();
    }

    public void onCreate() {
        checkAndRequestPermissions();
        String realmName = "Contract.db";
        onclick();
         realm = Realm.getDefaultInstance();
    }

    public void onDestroy() {

    }


    public void setImage(Uri selectdImages){
        addContactView.setImages(selectdImages);
         imagePath = selectdImages.toString();

    }


     public void insertContactDb(){

         Realm myRealm = Realm.getDefaultInstance();
         myRealm.beginTransaction();
         ContactInfoModel contact = new ContactInfoModel();
         contact.setFirstname(addContactView.getFistName());
         contact.setLastname(addContactView.getLastName());
         contact.setAddress(addContactView.getAddress());
         contact.setEmail(addContactView.getEmail());
         contact.setDescription(addContactView.getDescription());
         contact.setGender(addContactView.genderType());
         contact.setImageUrl(imagePath);
         contact.setId(UUID.randomUUID().toString());
         myRealm.insertOrUpdate(contact);
         myRealm.commitTransaction();
         myRealm.close();
         addContactView.showSuccessDailog();
     }

    public RealmResults<ContactInfoModel> getContacts() {

        return realm.where(ContactInfoModel.class).findAll();
    }






    private boolean checkAndRequestPermissions() {
        int permissionCamera = ContextCompat.checkSelfPermission(appCompatActivity,
                Manifest.permission.CAMERA);
        int permissionContact = ContextCompat.checkSelfPermission(appCompatActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE);


        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (permissionContact != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }


        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(appCompatActivity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);

        }
        return true;
    }
}
