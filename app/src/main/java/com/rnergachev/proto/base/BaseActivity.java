package com.rnergachev.proto.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Base Activity class
 *
 * Created by rnergachev on 29/06/2017.
 */

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity implements BaseView {
    private static final String VIEW_HOLDER = "VIEW_HOLDER";

    @Inject public Provider<VM> viewModelProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies();

        VM viewModel = getViewModel();
        ViewDataBinding binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setVariable(BR.model, viewModel);
    }

    /**
     * Provides view model holder
     * @return {@link ViewModelHolder}
     */
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

    protected VM createViewModel() {
        return viewModelProvider.get();
    }

    /**
     * Provides layout id
     * @return layout id
     */
    protected abstract int getLayoutId();

    /**
     * Injects dependencies
     */
    protected abstract void injectDependencies();
}
