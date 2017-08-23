package com.rnergachev.proto.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Set;

/**
 * Holder for view models
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
            if(viewModel != null) {
                viewModel.clear();
            }
            container.remove(id);
        }
    }

    /**
     * Puts view model into holder
     * @param id of the owner
     * @param viewModel to put
     */
    public void attach(String id, BaseViewModel viewModel) {
        container.put(id, viewModel);
    }

    /**
     * Removes view model from the holder
     * @param id of the owner
     */
    public void detach(String id) {
        BaseViewModel viewModel = container.get(id);
        if (viewModel != null) {
            viewModel.clear();
            container.remove(id);
        }
    }

    /**
     * Returns view model by id
     * @param id of the owner
     * @return {@link BaseViewModel} or null if model doesn't exist
     */
    @Nullable
    public BaseViewModel getViewModel(String id) {
        return container.get(id);
    }
}
