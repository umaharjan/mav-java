package com.app.task.activity.addlist.mvp;

import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.task.R;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class ContactListView extends FrameLayout {

    private AppCompatActivity appCompatActivity;
    private ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.MyProgressDialogStyle);

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.btnAdd)
    FloatingActionButton btnAdd;

    @BindView(R.id.tvNoList)
    TextView tvNoList;


    ContactListAdapter contactListAdapter;

    public ContactListView(AppCompatActivity activity) {
        super(activity);
        this.appCompatActivity = activity;
        inflate(activity, R.layout.list_layout, this);
        ButterKnife.bind(this);
    }

     public void setAdapter(){

     }

    public void showRequestList(RealmResults<ContactInfoModel> requestList){
        contactListAdapter= new ContactListAdapter(requestList);
        LinearLayoutManager layoutManager= new LinearLayoutManager(appCompatActivity);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(contactListAdapter);
        contactListAdapter.notifyDataSetChanged();


    }


    public RecyclerView getRecycleView(){
        return recyclerView;
    }




    public void hideRecycleView(){
        recyclerView.setVisibility(GONE);
        tvNoList.setVisibility(VISIBLE);
    }
    public void showRecycleView(){
        recyclerView.setVisibility(VISIBLE);
        tvNoList.setVisibility(GONE);
    }


    public Observable<ContactInfoModel> getClickObservable(){
        return contactListAdapter.getViewClickedObservable();
    }


    public Observable<Object> getButtonObservable() {
        return RxView.clicks(  btnAdd);
    }

}
