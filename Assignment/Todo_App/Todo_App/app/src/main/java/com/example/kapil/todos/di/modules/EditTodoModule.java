package com.example.kapil.todos.di.modules;


import com.example.kapil.todos.ui.EditTodoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;



@Module
public abstract class EditTodoModule {
    @ContributesAndroidInjector()
    abstract EditTodoActivity contributeEditTodoActivity();
}