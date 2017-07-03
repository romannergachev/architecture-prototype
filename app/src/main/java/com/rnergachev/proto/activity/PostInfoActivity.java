package com.rnergachev.proto.activity;

import com.rnergachev.proto.R;
import com.rnergachev.proto.base.BaseActivity;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.viewmodel.PostInfoViewModel;

/**
 * Created by rnergachev on 29/06/2017.
 */

public class PostInfoActivity extends BaseActivity<PostInfoViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.post_info_activity;
    }

    @Override
    protected PostInfoViewModel createViewModel() {
        DetailedPost post = getIntent().getParcelableExtra(getString(R.string.DETAILED_POST));
        return new PostInfoViewModel(post);
    }
}
