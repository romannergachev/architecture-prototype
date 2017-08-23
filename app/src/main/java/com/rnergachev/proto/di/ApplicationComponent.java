package com.rnergachev.proto.di;

import com.rnergachev.proto.activity.PostInfoActivity;
import com.rnergachev.proto.activity.PostsListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application component for dagger
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(PostsListActivity activity);
    void inject(PostInfoActivity activity);
}
