package com.rnergachev.proto.base;

import android.os.Bundle;
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
        for (String id : attachedViews) {
            BaseViewModel viewModel = container.get(id);
            viewModel.clear();
            container.remove(id);
        }
    }

    public void attach(String id, BaseViewModel viewModel) {
        container.put(id, viewModel);
    }

    public void detach(String id) {
        BaseViewModel viewModel = container.get(id);
        if (viewModel != null) {
            viewModel.clear();
            container.remove(id);
        }
    }

    @Nullable
    public BaseViewModel getViewModel(String id) {
        return container.get(id);
    }
}
