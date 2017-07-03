package com.rnergachev.proto.activity;

import android.content.Intent;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rnergachev.proto.ProtoApplication;
import com.rnergachev.proto.R;
import com.rnergachev.proto.adapter.PostListAdapter;
import com.rnergachev.proto.base.BaseActivity;
import com.rnergachev.proto.handler.PostListAdapterHandler;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.viewmodel.PostListItemViewModel;
import com.rnergachev.proto.viewmodel.PostViewModel;

import javax.inject.Inject;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class PostsListActivity extends BaseActivity<PostViewModel> implements PostListAdapterHandler {

    @Inject
    PostListItemViewModel listViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ProtoApplication) getApplication()).appComponent.inject(this);

        RecyclerView view = (RecyclerView) findViewById(R.id.postsList);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new PostListAdapter(listViewModel, this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.posts_activity;
    }

    @Override
    protected PostViewModel createViewModel() {
        return new PostViewModel();
    }

    @Override
    public void onClick(DetailedPost post) {
        Intent intent = new Intent(this, PostInfoActivity.class);
        intent.putExtra(getString(R.string.DETAILED_POST), post);
        startActivity(intent);
    }
}
