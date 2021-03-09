package com.app.task.activity.detail.mvp;

import com.app.task.activity.dbmanager.ContactInfoModel;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

public class DetailPresenter {


    private DetaiiModel detaiiModel;
    private DetailView detailView;
    private AppCompatActivity appCompatActivity;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Realm realm;
    ContactInfoModel data;
    public DetailPresenter(DetailView detailView, DetaiiModel detaiiModel, AppCompatActivity appCompatActivity) {
        this.detailView = detailView;
        this.detaiiModel = detaiiModel;
        this.appCompatActivity = appCompatActivity;
    }


    public void  onCreate(ContactInfoModel result){
        data=result;
        detailView.setInfo(result);
        onclick();
    }


    public void onclick(){
        detailView.getButtonObservable().doOnNext(o->detaiiModel.getEditView(data)).subscribe();
        detailView.getBackObservable().doOnNext(o->detaiiModel.getFinished()).subscribe();
        detailView.getbackObservable().doOnNext(o->detaiiModel.getFinished()).subscribe();
    }
}

