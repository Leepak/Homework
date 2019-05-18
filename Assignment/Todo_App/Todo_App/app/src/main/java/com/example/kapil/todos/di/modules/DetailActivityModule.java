package com.example.kapil.todos.di.modules;

import com.example.kapil.todos.ui.DetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class DetailActivityModule {
    @ContributesAndroidInjector()
    abstract DetailActivity contributeDetailActivity();
}