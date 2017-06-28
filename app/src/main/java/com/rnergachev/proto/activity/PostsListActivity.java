package com.rnergachev.proto.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rnergachev.proto.PostViewModel;
import com.rnergachev.proto.R;
import com.rnergachev.proto.databinding.PostsActivityBinding;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class PostsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PostViewModel viewModel = new PostViewModel();
        PostsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.posts_activity);
        binding.setModel(viewModel);
        viewModel.sampleString.set("sample2");
    }
}
