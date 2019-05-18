package com.example.kapil.todos.di.modules;


import com.example.kapil.todos.ui.AddToDoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;



@Module
public abstract class AddTodoModule {
    @ContributesAndroidInjector()
    abstract AddToDoActivity contributeAddToDoActivity();
}