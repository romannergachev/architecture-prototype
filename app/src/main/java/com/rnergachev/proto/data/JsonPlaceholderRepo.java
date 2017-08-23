package com.rnergachev.proto.data;

import android.content.Context;

import com.rnergachev.proto.R;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.data.network.JsonPlaceholderApi;
import com.rnergachev.proto.data.network.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Main repo
 */
public class JsonPlaceholderRepo {
    private JsonPlaceholderApi api;
    private Context context;

    @Inject
    public JsonPlaceholderRepo(JsonPlaceholderApi api, Context context) {
        this.api = api;
        this.context = context;
    }

    /**
     * Load posts and users combined
     * @return {@link Single} list of posts
     */
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

    /**
     * Load comments
     * @param postId id of the commented post
     * @return {@link Single} number of comments
     */
    public Single<Integer> getComments(int postId) {
        return api.getComments(postId)
            .map(ArrayList::size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    }

    private String getImageUrl(String email) {
        return context.getString(R.string.avatar_url, email);
    }
}
