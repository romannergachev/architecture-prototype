package com.rnergachev.proto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rnergachev.proto.ProtoApplication;
import com.rnergachev.proto.R;
import com.rnergachev.proto.adapter.PostListAdapter;
import com.rnergachev.proto.base.BaseActivity;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.handler.PostListAdapterHandler;
import com.rnergachev.proto.viewmodel.PostListViewModel;

/**
 * Post List activity
 *
 * Created by rnergachev on 28/06/2017.
 */

public class PostsListActivity extends BaseActivity<PostListViewModel> implements PostListAdapterHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView view = (RecyclerView) findViewById(R.id.postsList);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new PostListAdapter(getViewModel(), this));
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getViewModel().loadPosts();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.posts_activity;
    }

    @Override
    public void onClick(DetailedPost post) {
        Intent intent = new Intent(this, PostInfoActivity.class);
        intent.putExtra(getString(R.string.detailed_post), post);
        startActivity(intent);
    }

    @Override
    protected void injectDependencies() {
        ((ProtoApplication) getApplication()).appComponent.inject(this);
    }
}
