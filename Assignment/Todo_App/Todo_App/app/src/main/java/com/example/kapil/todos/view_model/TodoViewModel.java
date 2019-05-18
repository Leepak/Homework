package com.example.kapil.todos.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import com.example.kapil.todos.model.Todo;
import com.example.kapil.todos.repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository repository;
    private LiveData<List<Todo>> todoList;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        todoList = repository.getAllTodos();
    }
    public void insert(Todo todo){
        repository.insert(todo);
    }
    public void update(Todo todo){
        repository.update(todo);
    }
    public void delete(Todo todo){
        repository.delete(todo);
    }
    public void deleteAll(){
        repository.deleteAllTodos();
    }

    public LiveData<List<Todo>> getTodoList() {
        return todoList;
    }
}
