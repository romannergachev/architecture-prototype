package com.rnergachev.proto.di;

import android.content.Context;

import com.rnergachev.proto.R;
import com.rnergachev.proto.data.network.JsonPlaceholderApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Application module for dagger
 * <p>
 * Created by rnergachev on 30/06/2017.
 */
@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public JsonPlaceholderApi provideJsonPlaceholderApi() {
        return new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceholderApi.class);
    }
}