package com.rnergachev.proto.viewmodel;

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.rnergachev.proto.base.BaseViewModel;
import com.rnergachev.proto.data.JsonPlaceholderRepo;
import com.rnergachev.proto.data.model.DetailedPost;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by rnergachev on 29/06/2017.
 */

public class PostListItemViewModel implements BaseViewModel {
    public final ObservableArrayList<DetailedPost> titleList;
    private final JsonPlaceholderRepo repo;
    private final CompositeDisposable subscriptions;

    @Inject
    public PostListItemViewModel(JsonPlaceholderRepo repo) {
        titleList = new ObservableArrayList<>();
        this.repo = repo;
        this.subscriptions = new CompositeDisposable();

        init();
    }

    private void init() {
        subscriptions.add(repo.getPostsList().subscribe(
            list -> {
                Log.d(getClass().getName(), "Update loaded: " + list.size());
                titleList.clear();
                titleList.addAll(list);
            }, e -> {
                Log.e(getClass().getName(), "Update failed", e);
            }
        ));
    }


    @Override
    public void clear() {
        subscriptions.clear();
    }
}