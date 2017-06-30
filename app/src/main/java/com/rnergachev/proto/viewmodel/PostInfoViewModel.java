package com.rnergachev.proto.viewmodel;

import android.databinding.ObservableField;

import com.rnergachev.proto.base.BaseViewModel;
import com.rnergachev.proto.data.PostInfo;

/**
 * Created by rnergachev on 29/06/2017.
 */

public class PostInfoViewModel implements BaseViewModel {
    public final ObservableField<PostInfo> postInfo;

    public PostInfoViewModel() {
        postInfo = new ObservableField<>(new PostInfo(1, "test"));
    }

    @Override
    public void clear() { }
}