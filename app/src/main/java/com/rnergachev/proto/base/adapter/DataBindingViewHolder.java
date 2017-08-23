package com.rnergachev.proto.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rnergachev.proto.BR;

/**
 * Extension for the view holder to handle data binding under the hood
 */
public class DataBindingViewHolder<T> extends RecyclerView.ViewHolder {

    protected T item;

    public DataBindingViewHolder(View itemView) {
        super(itemView);
    }

    public void setItem(T item) {
        this.item = item;
        ViewDataBinding binding = DataBindingUtil.bind(itemView);
        binding.setVariable(BR.item, item);
    }
}
