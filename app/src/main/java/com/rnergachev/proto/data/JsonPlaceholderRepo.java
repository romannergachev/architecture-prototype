package com.rnergachev.proto.data;

import android.content.Context;

import com.rnergachev.proto.R;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.data.network.JsonPlaceholderApi;
import com.rnergachev.proto.data.network.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by rnergachev on 30/06/2017.
 */

public class JsonPlaceholderRepo {
    private JsonPlaceholderApi api;
    private Context context;
    private Scheduler observeScheduler;
    private Scheduler subscribeScheduler;

    @Inject
    public JsonPlaceholderRepo(
        JsonPlaceholderApi api,
        Context context,
        @Named("observe") Scheduler observeScheduler,
        @Named("subscribe") Scheduler subscribeScheduler
    ) {
        this.api = api;
        this.context = context;
        this.observeScheduler = observeScheduler;
        this.subscribeScheduler = subscribeScheduler;
    }

    public Single<List<DetailedPost>> getPostsList() {
        return api.getPosts()
            .zipWith(api.getUsers(), (posts, users) ->
                Observable.fromIterable(posts)
                    .map(post -> {
                        UserResponse user = Observable.fromIterable(users)
                            .filter(it -> it.getId() == post.getUserId())
                            .blockingFirst();

                        return new DetailedPost(
                            post.getId(),
                            post.getTitle(),
                            post.getBody(),
                            user.getUsername(),
                            getImageUrl(user.getEmail()));
                    })
                    .toList()
                    .blockingGet())
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler);
    }

    public Single<Integer> getComments(int postId) {
        return api.getComments(postId)
            .map(ArrayList::size)
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler);

    }

    private String getImageUrl(String email) {
        return context.getString(R.string.avatar_url) + email;
    }
}
