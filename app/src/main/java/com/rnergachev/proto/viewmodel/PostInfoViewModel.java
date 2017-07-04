package com.rnergachev.proto.viewmodel;

import android.util.Log;

import com.rnergachev.proto.base.BaseViewModel;
import com.rnergachev.proto.data.JsonPlaceholderRepo;
import com.rnergachev.proto.data.model.DetailedPost;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Post info view model
 *
 * Created by rnergachev on 29/06/2017.
 */

public class PostInfoViewModel implements BaseViewModel {
    public DetailedPost postInfo;
    private final JsonPlaceholderRepo repo;
    private final CompositeDisposable subscriptions;

    @Inject
    public PostInfoViewModel(JsonPlaceholderRepo repo) {
        postInfo = null;
        this.repo = repo;
        this.subscriptions = new CompositeDisposable();
    }

    /**
     * Loads comments and calculates their quantity
     */
    public void loadComments() {
        subscriptions.add(repo.getComments(postInfo.getId()).subscribe(
            size -> {
                postInfo.setNumberOfComments(size);
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