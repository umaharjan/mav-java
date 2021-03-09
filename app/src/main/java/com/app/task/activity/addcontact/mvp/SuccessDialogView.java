package com.app.task.activity.addcontact.mvp;


import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.app.task.R;
import com.jakewharton.rxbinding2.view.RxView;


import io.reactivex.Observable;

public class SuccessDialogView {

    Button btnOk;
    Dialog dialog;
    Activity activity;
    TextView tvMessage;

    public SuccessDialogView(Activity activity) {
        this.activity = activity;
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.success_layout);
        btnOk = (Button) dialog.findViewById(R.id.btnContinue);
        tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);
//        ButterKnife.bind(dialog);
    }

    public void showDialog() {

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.setCancelable(false);
            dialog.getWindow().setAttributes(lp);
            dialog.show();

    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    public Observable<Object> okButtonObservable() {
        return RxView.clicks(btnOk);
    }

    public void setSuccessmsg(String msg) {
        tvMessage.setText(msg);


    }


}
