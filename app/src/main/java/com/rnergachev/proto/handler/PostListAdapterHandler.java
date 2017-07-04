package com.rnergachev.proto.handler;

import com.rnergachev.proto.data.model.DetailedPost;

/**
 * List on click handler
 *
 * Created by rnergachev on 29/06/2017.
 */

public interface PostListAdapterHandler {
    /**
     * Performs the post selection
     *
     * @param post that has been selected
     */
    void onClick(DetailedPost post);
}
