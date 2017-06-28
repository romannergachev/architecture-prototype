package com.rnergachev.proto;

import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class PostViewModel implements BaseViewModel {
    public ObservableField<String> sampleString;

    public PostViewModel() {
        sampleString = new ObservableField<>("");

        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
            @Override
            public void run() {
                sampleString.set("sample3");
            }
        }, 3000);
    }

    @Override
    public void clear() { }
}
