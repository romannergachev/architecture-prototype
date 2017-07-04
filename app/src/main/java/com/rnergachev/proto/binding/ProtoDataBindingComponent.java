package com.rnergachev.proto.binding;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingComponent;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Binding component for Picasso
 *
 * Created by rnergachev on 30/06/2017.
 */

public class ProtoDataBindingComponent implements DataBindingComponent {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
