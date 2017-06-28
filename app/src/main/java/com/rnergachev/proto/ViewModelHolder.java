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

    private HashMap<String, BaseViewModel> container;

    public ViewModelHolder() {
        container = new HashMap<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Set<String> attachedViews = container.keySet();
        for (String className : attachedViews) {
            BaseViewModel viewModel = container.get(className);
            viewModel.clear();
            container.remove(className);
        }
    }

    public void attach(Class viewClass, BaseViewModel viewModel) {
        container.put(viewClass.getCanonicalName(), viewModel);
    }

    public void detach(Class viewClass) {
        BaseViewModel viewModel = container.get(viewClass.getCanonicalName());
        if (viewModel != null) {
            viewModel.clear();
            container.remove(viewClass.getCanonicalName());
        }
    }

    @Nullable
    public BaseViewModel getViewModel(Class viewClass) {
        return container.get(viewClass.getCanonicalName());
    }
}
