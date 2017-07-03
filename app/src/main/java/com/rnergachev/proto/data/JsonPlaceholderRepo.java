package com.rnergachev.proto.data;

import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.data.network.JsonPlaceholderApi;
import com.rnergachev.proto.data.network.response.UserResponse;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rnergachev on 30/06/2017.
 */

public class JsonPlaceholderRepo {
    private JsonPlaceholderApi api;

    @Inject
    public JsonPlaceholderRepo(JsonPlaceholderApi api) {
        this.api = api;
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    private String getImageUrl(String email) {
        return "https://api.adorable.io/avatar/256/" + email;
    }
}
