package com.rnergachev.proto.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rnergachev.proto.BR;
import com.rnergachev.proto.R;
import com.rnergachev.proto.data.model.DetailedPost;
import com.rnergachev.proto.handler.PostListAdapterHandler;
import com.rnergachev.proto.viewmodel.PostListViewModel;

/**
 * Recycler view adapter for posts list
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {

    private final PostListViewModel viewModel;
    private PostListAdapterHandler handler;

    class PostListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        PostListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            handler.onClick(viewModel.postsList.get(getAdapterPosition()));
        }
    }

    public PostListAdapter(PostListViewModel viewModel, PostListAdapterHandler handler) {
        this.viewModel = viewModel;
        this.handler = handler;
        this.viewModel.postsList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<DetailedPost>>() {
            @Override
            public void onChanged(ObservableList<DetailedPost> detailedPosts) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<DetailedPost> detailedPosts, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<DetailedPost> detailedPosts, int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<DetailedPost> detailedPosts, int fromPosition, int toPosition, int itemCount) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList<DetailedPost> detailedPosts, int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }

    @Override
    public PostListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PostListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PostListViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setVariable(BR.model, viewModel);
        binding.setVariable(BR.position, position);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return viewModel.postsList.size();
    }
}
