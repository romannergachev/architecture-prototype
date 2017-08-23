package com.rnergachev.proto.base;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rnergachev.proto.BR;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Base Fragment class
 */
public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    @Inject
    public Provider<VM> viewModelProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(getLayoutId(), container, false);

        VM viewModel = getViewModel();
        ViewDataBinding binding = DataBindingUtil.getBinding(root);
        binding.setVariable(BR.model, viewModel);

        return root;
    }

    protected VM getViewModel() {
        ViewModelHolder vh = ((BaseActivity) getActivity()).getViewModelHolder();
        VM vm = (VM) vh.getViewModel(this.getClass().getCanonicalName());
        if (vm == null) {
            vm = createViewModel();
            vh.attach(this.getClass().getCanonicalName(), vm);
        }

        return vm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ViewModelHolder vh = ((BaseActivity) getActivity()).getViewModelHolder();
        vh.detach(this.getClass().getCanonicalName());
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
