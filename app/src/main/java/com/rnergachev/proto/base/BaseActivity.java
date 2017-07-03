package com.rnergachev.proto.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;
import com.rnergachev.proto.ProtoDataBindingComponent;

/**
 * Created by rnergachev on 29/06/2017.
 */

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity implements BaseView {
    private static final String VIEW_HOLDER = "VIEW_HOLDER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VM viewModel = getViewModel();
        ViewDataBinding binding = DataBindingUtil.setContentView(this, getLayoutId(), new ProtoDataBindingComponent());
        binding.setVariable(BR.model, viewModel);
    }

    public ViewModelHolder getViewModelHolder() {
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

    protected VM getViewModel() {
        ViewModelHolder vh = getViewModelHolder();
        VM vm = (VM) vh.getViewModel(this.getClass().getCanonicalName());
        if (vm == null) {
            vm = createViewModel();
            vh.attach(this.getClass().getCanonicalName(), vm);
        }

        return vm;
    }

    abstract protected int getLayoutId();

    abstract protected VM createViewModel();
}
