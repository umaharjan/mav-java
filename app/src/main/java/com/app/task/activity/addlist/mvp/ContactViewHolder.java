package com.app.task.activity.addlist.mvp;

import android.view.View;
import android.widget.TextView;

import com.app.task.R;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvFullName)
    public TextView tvFullName;

    public ContactViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
