package com.rnergachev.proto;

import android.databinding.ObservableField;

/**
 * Created by rnergachev on 28/06/2017.
 */

public class PostViewModel {
    public ObservableField<String> sampleString;

    public PostViewModel() {
        sampleString = new ObservableField<>("");
    }
}
