package com.app.task.activity.detail.mvp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.task.R;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;

public class DetailView extends FrameLayout {
    private AppCompatActivity appCompatActivity;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvemail)
    TextView tvemail;

    @BindView(R.id.tvGender)
    TextView tvGender;

    @BindView(R.id.tvDescripiton)
    TextView tvDescripiton;

    @BindView(R.id.profile)
    CircleImageView profile;

    @BindView(R.id.btnBack)
    Button btnBack;

    @BindView(R.id.btnEdit)
    Button btnEdit;

    @BindView(R.id.ivBack)
    ImageView ivBack;

    public DetailView(AppCompatActivity activity) {
        super(activity);
        this.appCompatActivity = activity;
        inflate(activity, R.layout.detail_layout, this);
        ButterKnife.bind(this);
    }


    public void setInfo(ContactInfoModel info) {
        tvName.setText(info.getFirstname() + " " + info.getLastname());
        tvAddress.setText(info.getAddress());
        tvemail.setText(info.getEmail());
        tvGender.setText(info.getGender());
        tvDescripiton.setText(info.getDescription());

        try {
           Uri image=Uri.parse(info.getImageUrl());
            profile.setImageURI(image);
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }
    public Observable<Object> getbackObservable() {
        return RxView.clicks(ivBack);
    }
    public Observable<Object> getButtonObservable() {
        return RxView.clicks(  btnEdit);
    }

    public Observable<Object> getBackObservable() {
        return RxView.clicks(  btnBack);
    }

}
