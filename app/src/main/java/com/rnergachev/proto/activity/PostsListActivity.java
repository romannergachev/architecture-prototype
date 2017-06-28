package com.rnergachev.proto.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.rnergachev.proto.BaseView;
import com.rnergachev.proto.PostViewModel;
import com.rnergachev.proto.R;
import com.rnergachev.proto.ViewModelHolder;
import com.rnergachev.proto.databinding.PostsActivityBinding;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class PostsListActivity extends AppCompatActivity implements BaseView {
    private static final String VIEW_HOLDER = "VIEW_HOLDER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PostViewModel viewModel = getViewModel();
        PostsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.posts_activity);
        binding.setModel(viewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private ViewModelHolder getViewModelHolder() {
        FragmentManager fm = getSupportFragmentManager();
        ViewModelHolder vh = (ViewModelHolder) fm.findFragmentByTag(VIEW_HOLDER);
        if (vh == null) {
            vh = new ViewModelHolder();
            fm.beginTransaction()
                .add(vh, VIEW_HOLDER)
                .commitAllowingStateLoss();
        }

        return vh;
    }

    private PostViewModel getViewModel() {
        ViewModelHolder vh = getViewModelHolder();
        PostViewModel vm = (PostViewModel) vh.getViewModel(this.getClass());
        if (vm == null) {
            vm = new PostViewModel();
            vh.attach(this.getClass(), vm);
        }

        return vm;
    }
}
