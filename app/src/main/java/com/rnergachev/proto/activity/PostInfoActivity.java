package com.rnergachev.proto.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rnergachev.proto.ProtoApplication;
import com.rnergachev.proto.R;
import com.rnergachev.proto.base.BaseActivity;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.viewmodel.PostInfoViewModel;

/**
 * Post Info Activity
 *
 * Created by rnergachev on 29/06/2017.
 */

public class PostInfoActivity extends BaseActivity<PostInfoViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.post_info_activity;
    }

    @Override
    protected PostInfoViewModel createViewModel() {
        DetailedPost post = getIntent().getParcelableExtra(getString(R.string.detailed_post));
        PostInfoViewModel vm = viewModelProvider.get();
        vm.postInfo = post;
        return vm;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getViewModel().loadComments();
    }

    @Override
    protected void injectDependencies() {
        ((ProtoApplication) getApplication()).appComponent.inject(this);
    }
}
