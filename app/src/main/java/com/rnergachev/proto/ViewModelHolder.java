package com.rnergachev.proto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class ViewModelHolder extends Fragment {

    private HashMap<BaseView, BaseViewModel> container;

    public ViewModelHolder() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        container = new HashMap<>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Set<BaseView> attachedViews = container.keySet();
        for (BaseView view : attachedViews) {
            detach(view);
        }
    }

    public void attach(BaseView view, BaseViewModel viewModel) {
        container.put(view, viewModel);
    }

    public void detach(BaseView view) {
        BaseViewModel viewModel = container.get(view);
        if (viewModel != null) {
            viewModel.clear();
            container.remove(view);
        }

    }

    @Nullable
    public BaseViewModel getViewModel(BaseView view) {
        return container.get(view);
    }
}
