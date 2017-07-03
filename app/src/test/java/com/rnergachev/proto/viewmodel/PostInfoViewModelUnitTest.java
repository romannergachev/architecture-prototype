package com.rnergachev.proto.viewmodel;

import com.rnergachev.proto.data.JsonPlaceholderRepo;
import com.rnergachev.proto.data.model.DetailedPost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.internal.operators.single.SingleSubscribeOn;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rnergachev on 03/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class PostInfoViewModelUnitTest {

    @Mock
    private JsonPlaceholderRepo repo;
    private PostInfoViewModel vm;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vm = new PostInfoViewModel(repo);
        vm.postInfo = new DetailedPost(0, "", "", "", "");
    }

    @Test
    public void vm_should_load_comments() {
        when(repo.getComments(0))
            .thenReturn(Single.just(10));

        vm.loadComments();

        verify(repo, atLeastOnce()).getComments(0);
        assertEquals(10, vm.postInfo.getNumberOfComments());
    }
}
