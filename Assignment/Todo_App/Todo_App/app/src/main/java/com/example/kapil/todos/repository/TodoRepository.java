package com.example.kapil.todos.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.os.AsyncTask;


import com.example.kapil.todos.db.AppDatabase;
import com.example.kapil.todos.db.TodoDao;
import com.example.kapil.todos.model.Todo;

import java.util.List;

public class TodoRepository {
    private TodoDao todoDao;
    private LiveData<List<Todo>> allTodos;

    public TodoRepository(Application application){
        AppDatabase database = AppDatabase.getAppDatabase(application);
        todoDao = database.todoDao();
        allTodos = todoDao.getAllTodos();
    }
    public void insert(Todo todo){
        new InsertTodo(todoDao).execute(todo);
    }
    public void update(Todo todo){
        new UpdateTodo(todoDao).execute(todo);
    }

    public void delete(Todo todo){
        new DeleteTodo(todoDao).execute(todo);
    }
    public void deleteAllTodos(){
        new DeleteAllTodos(todoDao).execute();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }


    private static class InsertTodo extends AsyncTask<Todo, Void, Void>{
        private TodoDao todoDao;

        public InsertTodo(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }
    private static class UpdateTodo extends AsyncTask<Todo, Void, Void>{
        private TodoDao todoDao;

        public UpdateTodo(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.update(todos[0]);
            return null;
        }
    }

    private static class DeleteTodo extends AsyncTask<Todo, Void, Void>{
        private TodoDao todoDao;

        public DeleteTodo(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }
    private static class DeleteAllTodos extends AsyncTask<Void, Void, Void>{
        private TodoDao todoDao;

        public DeleteAllTodos(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAllTodos();
            return null;
        }
    }
}
