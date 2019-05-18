package com.example.kapil.todos.di;


import android.app.Application;
import com.example.kapil.todos.di.modules.DetailActivityModule;
import com.example.kapil.todos.TodoApp;
import com.example.kapil.todos.di.modules.AddTodoModule;
import com.example.kapil.todos.di.modules.EditTodoModule;
import com.example.kapil.todos.di.modules.MainActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton

@Component(modules = {
        AndroidInjectionModule.class,
        MainActivityModule.class,
        DetailActivityModule.class,
        AddTodoModule.class,
        EditTodoModule.class

})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(TodoApp todoApp);
}
