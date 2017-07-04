package com.rnergachev.proto.viewmodel;

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.rnergachev.proto.base.BaseViewModel;
import com.rnergachev.proto.data.JsonPlaceholderRepo;
import com.rnergachev.proto.data.model.DetailedPost;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Post list view model
 *
 * Created by rnergachev on 29/06/2017.
 */

public class PostListViewModel implements BaseViewModel {
    public final ObservableArrayList<DetailedPost> postsList;
    private final JsonPlaceholderRepo repo;
    private final CompositeDisposable subscriptions;

    @Inject
    public PostListViewModel(JsonPlaceholderRepo repo) {
        postsList = new ObservableArrayList<>();
        this.repo = repo;
        this.subscriptions = new CompositeDisposable();
    }

    /**
     * Loads posts into the list
     */
    public void loadPosts() {
        subscriptions.add(repo.getPostsList().subscribe(
            list -> {
                postsList.clear();
                postsList.addAll(list);
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