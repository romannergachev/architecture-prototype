package com.rnergachev.proto.viewmodel;

import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Looper;

import com.rnergachev.proto.base.BaseViewModel;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class PostViewModel implements BaseViewModel {
    public final ObservableField<String> sampleString;

    public PostViewModel() {
        sampleString = new ObservableField<>("");

        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
            @Override
            public void run() {
                sampleString.set("sample13");
            }
        }, 3000);
    }

    @Override
    public void clear() { }
}
