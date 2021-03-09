package com.app.task.activity.addlist.mvp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.task.R;
import com.app.task.activity.dbmanager.ContactInfoModel;
import com.jakewharton.rxbinding2.view.RxView;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.realm.RealmResults;

public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    RealmResults<ContactInfoModel> requestList;
    private PublishSubject<ContactInfoModel> clickSubject = PublishSubject.create();

    public ContactListAdapter(RealmResults<ContactInfoModel> requestList) {
        this.requestList = requestList;


    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);

        RxView.clicks(view)
                .takeUntil(RxView.detaches(parent))
                .map(aVoid -> requestList.get(viewHolder.getAdapterPosition()))
                .subscribe(clickSubject);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactInfoModel requestModel = requestList.get(position);
        holder.tvFullName.setText(requestModel.getFirstname());
    }


    @Override
    public int getItemCount() {
        System.out.println("size==" + requestList.size());
        return requestList.size();

    }

    public Observable<ContactInfoModel> getViewClickedObservable() {
        return clickSubject;
    }

}
