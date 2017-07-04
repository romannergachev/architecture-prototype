package com.rnergachev.proto.data;

import android.content.Context;

import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.data.network.JsonPlaceholderApi;
import com.rnergachev.proto.data.network.response.CommentsResponse;
import com.rnergachev.proto.data.network.response.PostResponse;
import com.rnergachev.proto.data.network.response.UserResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * {@link JsonPlaceholderRepo} unit test
 *
 * Created by rnergachev on 03/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class JsonPlaceholderRepoUnitTest {
    @Mock
    private JsonPlaceholderApi api;
    @Mock
    Context context;
    private JsonPlaceholderRepo repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repo = new JsonPlaceholderRepo(api, context);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Test
    public void repo_should_get_comments() {
        ArrayList<CommentsResponse> list = new ArrayList<>();
        list.add(new CommentsResponse());
        list.add(new CommentsResponse());

        when(api.getComments(0))
            .thenReturn(Single.just(list));

        TestObserver<Integer> testObserver = repo.getComments(0).test();
        testObserver
            .awaitTerminalEvent();
        testObserver
            .assertNoErrors()
            .assertValue(val -> val == 2);
    }

    @Test
    public void repo_should_get_posts() {
        ArrayList<PostResponse> postList = new ArrayList<>();
        postList.add(new PostResponse(1, 1, "title", ""));

        ArrayList<UserResponse> userList = new ArrayList<>();
        userList.add(new UserResponse(1, "", "user", ""));

        when(api.getPosts())
            .thenReturn(Single.just(postList));

        when(api.getUsers())
            .thenReturn(Single.just(userList));

        TestObserver<List<DetailedPost>> testObserver = repo.getPostsList().test();
        testObserver
            .awaitTerminalEvent();
        testObserver
            .assertNoErrors()
            .assertValue(list -> list.size() == 1)
            .assertValue(list -> "title".equals(list.get(0).getTitle()))
            .assertValue(list -> "user".equals(list.get(0).getUserName()));
    }

    @After
    public void cleanUp() {
        RxAndroidPlugins.reset();
    }
}
