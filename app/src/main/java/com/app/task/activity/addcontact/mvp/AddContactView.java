package com.app.task.activity.addcontact.mvp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.task.R;
import com.app.task.activity.utils.Apputils;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class AddContactView extends FrameLayout {
    private AppCompatActivity appCompatActivity;
    private ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.MyProgressDialogStyle);


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.etFirstName)
    EditText etFirstName;

    @BindView(R.id.etLastname)
    EditText etLastname;

    @BindView(R.id.etEmailAddress)
    EditText etEmailAddress;

    @BindView(R.id.etAddressName)
    EditText etAddressName;

    @BindView(R.id.etDescription)
    EditText etDescription;

    @BindView(R.id.tlFirstName)
    TextInputLayout tlFirstName;

    @BindView(R.id.tlLastName)
    TextInputLayout tlLastName;

    @BindView(R.id.tlEmailAddress)
    TextInputLayout tlEmailAddress;

    @BindView(R.id.tlAddressName)
    TextInputLayout tlAddressName;

    @BindView(R.id.tlDescription)
    TextInputLayout tlDescription;

    @BindView(R.id.btnContinue)
    Button btnContinue;

    @BindView(R.id.radioType)
    RadioGroup radioType;

    @BindView(R.id.radioMale)
    RadioButton radioMale;

    @BindView(R.id.radioFemale)
    RadioButton radioFemale;

    @BindView(R.id.ivCamera)
    ImageView ivCamera;

    @BindView(R.id.ivBack)
    ImageView ivBack;




    @BindView(R.id.profile)
    CircleImageView profile;

    boolean isfirstValid, isLastValid, isemailValid, isAddresvalid, isdescriptionValid, isImageSelected = false;
    String imagePath;
  private  SuccessDialogView successDialogView;

    public AddContactView(AppCompatActivity activity,SuccessDialogView successDialogView) {
        super(activity);
        this.appCompatActivity = activity;
        this.successDialogView=successDialogView;
        inflate(activity, R.layout.add_contact_layout, this);

        ButterKnife.bind(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Processing..");
        btnContinue.setEnabled(false);
        firstNameListner();
        lastNameListner();
        emailListner();
        addressListner();
        descritionListner();
    }




    public String getFistName() {
        return etFirstName.getText().toString();
    }

    public String getEmail() {
        return etEmailAddress.getText().toString();
    }

    public String getAddress() {
        return etAddressName.getText().toString();
    }


    public String getDescription() {
        return etDescription.getText().toString();
    }

    public String getLastName() {
        return etLastname.getText().toString();
    }

    public Observable<Object> getButtonObservable() {
        return RxView.clicks(btnContinue);
    }

    public Observable<Object> getImageObservable() {
        return RxView.clicks(ivCamera);
    }
    public Observable<Object> getbackObservable() {
        return RxView.clicks(ivBack);
    }



    public void setFirstValidation(Boolean abool) {
        if (abool) {
            tlFirstName.setErrorEnabled(true);
            tlFirstName.setError(appCompatActivity.getString(R.string.invalid_first_name));
        } else {
            tlFirstName.setErrorEnabled(false);
        }

    }

    public void setlastValidation(Boolean abool) {
        if (abool) {
            tlLastName.setErrorEnabled(true);
            tlLastName.setError(appCompatActivity.getString(R.string.invalid_last_name));
        } else {
            tlLastName.setErrorEnabled(false);

        }

    }


    public void setEmailValidation(Boolean abool) {
        if (abool) {
            tlEmailAddress.setErrorEnabled(true);
            tlEmailAddress.setError(appCompatActivity.getString(R.string.invalid_email));
        } else {
            tlEmailAddress.setErrorEnabled(false);

        }

    }

    public void setAddressValidation(Boolean abool) {
        if (abool) {
            tlAddressName.setErrorEnabled(true);
            tlAddressName.setError(appCompatActivity.getString(R.string.invalid_address));
        } else {
            tlAddressName.setErrorEnabled(false);
        }

    }


    public void setDescriptionValidation(Boolean abool) {
        if (abool) {
            tlDescription.setErrorEnabled(true);
            tlDescription.setError(appCompatActivity.getString(R.string.invalid_description));
        } else {
            tlDescription.setErrorEnabled(false);

        }

    }


    public void firstNameListner() {
        RxTextView.textChanges(etFirstName).skip(1)
                .map(CharSequence::toString)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> {
                    if (getFistName().isEmpty()) {
                        isfirstValid = false;
                        setFirstValidation(true);
                        btnContinue.setEnabled(false);
                    } else {
                        isfirstValid = true;

                        if(isValid()){
                            btnContinue.setEnabled(true);
                        }else{
                            btnContinue.setEnabled(false);
                        }
                        setFirstValidation(false);

                    }
                })
                .subscribe();
    }


    public void lastNameListner() {
        RxTextView.textChanges(etLastname).skip(1)
                .map(CharSequence::toString)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> {
                    if (getLastName().isEmpty()) {
                        isLastValid = false;
                        setlastValidation(true);
                        btnContinue.setEnabled(false);
                    } else {
                        isLastValid = true;

                        if(isValid()){
                            btnContinue.setEnabled(true);
                        }else{
                            btnContinue.setEnabled(false);
                        }
                        setlastValidation(false);

                    }
                })
                .subscribe();
    }

    public void addressListner() {
        RxTextView.textChanges(etAddressName).skip(1)
                .map(CharSequence::toString)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> {
                    if (getAddress().isEmpty()) {
                        isAddresvalid = false;
                        setAddressValidation(true);
                        btnContinue.setEnabled(false);
                    } else {
                        isAddresvalid = true;
                        setAddressValidation(false);

                        if(isValid()){
                            btnContinue.setEnabled(true);
                        }else{
                            btnContinue.setEnabled(false);
                        }
                    }
                })
                .subscribe();
    }


    public void emailListner() {
        RxTextView.textChanges(etEmailAddress).skip(1)
                .map(CharSequence::toString)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> {
                    if (Apputils.emailValidation(getEmail())) {
                        setEmailValidation(false);
                        isemailValid = true;

                        if(isValid()){
                            btnContinue.setEnabled(true);
                        }else{
                            btnContinue.setEnabled(false);
                        }
                    } else {

                        isemailValid = false;
                        setEmailValidation(true);
                        btnContinue.setEnabled(false);

                    }
                })
                .subscribe();
    }

    public void descritionListner() {
        RxTextView.textChanges(etDescription).skip(1)
                .map(CharSequence::toString)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> {
                    if (getDescription().isEmpty()) {
                        isdescriptionValid = false;
                        setDescriptionValidation(true);
                        btnContinue.setEnabled(false);
                    } else {
                        isdescriptionValid = true;
                        setDescriptionValidation(false);

                        if(isValid()){
                            btnContinue.setEnabled(true);
                        }else{
                            btnContinue.setEnabled(false);
                        }
                    }
                })
                .subscribe();
    }


    public void showLoading(boolean isLoading) {
        if (isLoading)
            progressDialog.show();
        else
            progressDialog.cancel();
    }


    public String genderType() {
        Integer selecteAdslType = radioType.getCheckedRadioButtonId();
        if (selecteAdslType == R.id.radioMale) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public void setImages(Uri selectedimage) {

        profile.setVisibility(VISIBLE);
        profile.setImageURI(selectedimage);

        imagePath = selectedimage.getPath();
        System.out.println("imagepaht==" + selectedimage.toString());

        if (imagePath.isEmpty()) {
            isImageSelected = false;
        } else {
            isImageSelected = true;

            if(isValid()){
                btnContinue.setEnabled(true);
            }else{
                btnContinue.setEnabled(false);
            }
        }
    }


    public String getImage(){
        return imagePath;
    }

    public Boolean isValid() {
        if (isfirstValid && isLastValid && isemailValid && isAddresvalid && isdescriptionValid && isImageSelected) {
            btnContinue.setEnabled(true);
            return true;
        } else return false;
    }

     public void showSuccessDailog(){
        successDialogView.showDialog();
        successDialogView.setSuccessmsg("Task has been added successfully");
     }

    public Observable<Object> getOKObservable() {
        return successDialogView.okButtonObservable();
    }





    public void dismissDailog(){
        successDialogView.dismissDialog();
    }

}
