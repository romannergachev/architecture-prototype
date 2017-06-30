package com.rnergachev.proto.data.network;

import com.rnergachev.proto.data.network.response.CommentsResponse;
import com.rnergachev.proto.data.network.response.PostResponse;
import com.rnergachev.proto.data.network.response.UserResponse;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rnergachev on 30/06/2017.
 */

public interface JsonPlaceholderApi {
    /**
     * Returns all posts
     *
     * @return {@link Single<ArrayList<PostResponse>>}
     */
    @GET("posts")
    Single<ArrayList<PostResponse>> getPosts();

    /**
     * Returns all users
     *
     * @return {@link Single<ArrayList< UserResponse >>}
     */
    @GET("users")
    Single<ArrayList<UserResponse>> getUsers();

    /**
     * Returns comments by post id
     *
     * @param postId post id
     * @return {@link Single<ArrayList<CommentsResponse>>}
     */
    @GET("comments")
    Single<ArrayList<CommentsResponse>> getComments(@Query("postId") int postId);
}
