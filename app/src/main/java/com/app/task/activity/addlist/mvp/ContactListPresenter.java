package com.app.task.activity.addlist.mvp;

import com.app.task.activity.dbmanager.ContactInfoModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.realm.Realm;
import io.realm.RealmResults;

public class ContactListPresenter {


    private ContactListModel contactListModel;
    private ContactListView contactListView;
    private AppCompatActivity appCompatActivity;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Realm realm;


    public ContactListPresenter(ContactListView contactListView, ContactListModel contactListModel, AppCompatActivity appCompatActivity) {
        this.contactListModel = contactListModel;
        this.contactListView = contactListView;
        this.appCompatActivity = appCompatActivity;
    }


    public void onCreate() {
        realm = Realm.getDefaultInstance();
        if (getContacts().size() <= 0) {
            contactListView.hideRecycleView();
        } else {
            contactListView.showRecycleView();
            contactListView.showRequestList(getContacts());
            onItemClick();
        }


        onclick();
        SwipeTodelete();
    }

    public void SwipeTodelete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(contactListView.recyclerView);


    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            delete(position);

            if (getContacts().size() <= 0) {
                contactListView.hideRecycleView();
            } else {
                contactListView.showRecycleView();
                contactListView.showRequestList(getContacts());
               // onItemClick();
            }


        }
    };


    public void delete(Integer position) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ContactInfoModel> result = realm.where(ContactInfoModel.class).findAll();
                result.deleteFromRealm(position);
            }
        });
    }

    public void onclick() {
        contactListView.getButtonObservable().subscribe(o -> {
            contactListModel.getAddView();
        });
    }


    public RealmResults<ContactInfoModel> getContacts() {

        return realm.where(ContactInfoModel.class).findAll();
    }


    private void onItemClick() {
        try{
        DisposableObserver<ContactInfoModel> disposableObserver = getItemClickObserver();
        contactListView.getClickObservable().subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);}
        catch (Exception ex){

        }
    }


    private DisposableObserver<ContactInfoModel> getItemClickObserver() {
        return new DisposableObserver<ContactInfoModel>() {
            @Override
            public void onNext(ContactInfoModel pullRequestModel) {


                contactListModel.getDetailView(pullRequestModel);
            }

            @Override
            public void onError(Throwable e) {
                //  accountDetailView.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                // Timber.e("Clicked");
            }
        };
    }
}
