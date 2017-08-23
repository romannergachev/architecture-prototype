package com.rnergachev.proto.base.adapter;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

/**
 * Base class for the adapter handle observable operations
 */
public abstract class ObservableListAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected <T> void observeList(ObservableList<T> list) {

        list.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<T>>() {
            @Override
            public void onChanged(ObservableList<T> list) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<T> list, int start, int count) {
                notifyItemRangeChanged(start, count);
            }

            @Override
            public void onItemRangeInserted(ObservableList<T> list, int start, int count) {
                notifyItemRangeInserted(start, count);
            }

            @Override
            public void onItemRangeMoved(ObservableList<T> list, int from, int to, int count) {
                for (int i = 0; i < count; i++) {
                    notifyItemMoved(from + i, to + i);
                }
            }

            @Override
            public void onItemRangeRemoved(ObservableList<T> list, int start, int count) {
                notifyItemRangeRemoved(start, count);
            }
        });
    }
}
