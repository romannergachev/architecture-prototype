package com.rnergachev.proto.viewmodel;

import com.rnergachev.proto.data.JsonPlaceholderRepo;
import com.rnergachev.proto.data.model.DetailedPost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Single;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link PostListViewModel} unit test
 *
 * Created by rnergachev on 03/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class PostListViewModelUnitTest {
    @Mock
    private JsonPlaceholderRepo repo;
    private PostListViewModel vm;
    private ArrayList<DetailedPost> data;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vm = new PostListViewModel(repo);
        data = new ArrayList<>();
        data.add(new DetailedPost());
        data.add(new DetailedPost());
    }

    @Test
    public void vm_should_load_posts() throws InterruptedException {
        when(repo.getPostsList())
            .thenReturn(Single.just(data));

        vm.loadPosts();

        verify(repo, atLeastOnce()).getPostsList();
        assertEquals(2, vm.postsList.size());
    }
}
