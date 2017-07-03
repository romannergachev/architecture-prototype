package com.rnergachev.proto;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingComponent;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by rnergachev on 30/06/2017.
 */

public class ProtoDataBindingComponent implements DataBindingComponent {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
