package com.rnergachev.proto.viewmodel;

import android.databinding.ObservableField;

import com.rnergachev.proto.base.BaseViewModel;
import com.rnergachev.proto.data.PostInfo;
import com.rnergachev.proto.data.model.DetailedPost;

/**
 * Created by rnergachev on 29/06/2017.
 */

public class PostInfoViewModel implements BaseViewModel {
    public final ObservableField<DetailedPost> postInfo;

    public PostInfoViewModel(DetailedPost post) {
        postInfo = new ObservableField<>(post);
    }

    @Override
    public void clear() { }
}