package com.app.task.activity.edit.mvp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.app.task.activity.dbmanager.ContactInfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditContactPresetnter {


    private EditContactModel editContactModel;
    private EditContactView editContactView;
    private AppCompatActivity appCompatActivity;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Realm realm;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public static final int CameraPhotoRequest = 1;
    public String imagePath,primaryId;

    public EditContactPresetnter(EditContactView editContactView, EditContactModel editContactModel, AppCompatActivity appCompatActivity) {
        this.editContactView = editContactView;
        this.editContactModel = editContactModel;
        this.appCompatActivity = appCompatActivity;
    }

    @SuppressLint("CheckResult")
    public void onclick() {

        editContactView.getButtonObservable().subscribe(o -> {
            insertContactDb();
        });

        editContactView.getImageObservable().doOnNext(o->{
            if( checkAndRequestPermissions()==true){
                editContactModel.imagePicker(CameraPhotoRequest);
            }

        })
                .subscribe();

        editContactView.getOKObservable().doOnNext(o->
        {editContactModel. getListView();
                editContactView.dismissDailog();}
    ).subscribe();
        editContactView.getbackObservable().doOnNext(o->editContactModel.finished()).subscribe();
    }

    public void onCreate(ContactInfoModel result) {
        checkAndRequestPermissions();
        String realmName = "Contract.db";
        onclick();
        primaryId=result.getId();
        realm = Realm.getDefaultInstance();
        editContactView.setInfo(result);
    }

    public void onDestroy() {

    }


    public void setImage(Uri selectdImages){
        editContactView.setImages(selectdImages);
        imagePath = selectdImages.toString();

    }


    public void insertContactDb(){

        Realm myRealm = Realm.getDefaultInstance();
        myRealm.beginTransaction();
        ContactInfoModel contact = new ContactInfoModel();
        contact.setFirstname(editContactView.getFistName());
        contact.setLastname(editContactView.getLastName());
        contact.setAddress(editContactView.getAddress());
        contact.setEmail(editContactView.getEmail());
        contact.setDescription(editContactView.getDescription());
        contact.setGender(editContactView.genderType());
        contact.setImageUrl(imagePath);
        contact.setId(primaryId);
        myRealm.insertOrUpdate(contact);
        myRealm.commitTransaction();
        myRealm.close();
        editContactView.showSuccessDailog();
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
