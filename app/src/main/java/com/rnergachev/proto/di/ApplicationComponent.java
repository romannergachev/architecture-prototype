package com.rnergachev.proto.di;

import com.rnergachev.proto.activity.PostsListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application component for dagger
 *
 * Created by rnergachev on 30/06/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(PostsListActivity activity);
}
