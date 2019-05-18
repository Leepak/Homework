package com.example.kapil.todos.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.kapil.todos.clickcallback.TodoClickCallBack;
import com.example.kapil.todos.databinding.TodoItemBinding;
import com.example.kapil.todos.model.Todo;
import com.example.kapil.todos.ui.EditTodoActivity;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends ListAdapter<Todo,TodoAdapter.TodoHolder> {


    TodoItemBinding binding;
    Context context;
    private TodoClickCallBack todoClickCallBack;

//    public TodoAdapter() {
//        super(DIFF_CALLBACK);
//    }

    public TodoAdapter(TodoClickCallBack todoClickCallBack, Context context) {
        super(DIFF_CALLBACK);
        this.todoClickCallBack = todoClickCallBack;
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Todo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(Todo oldItem, Todo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Todo oldItem, Todo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = TodoItemBinding.inflate(layoutInflater, parent, false);


        return new TodoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        Todo todo = getItem(position);
        holder.bind(todo, todoClickCallBack);


        binding.editTodo.setOnClickListener(v -> {
            Intent editIntent = new Intent(context, EditTodoActivity.class);
            editIntent.putExtra("todo",todo);
            context.startActivity(editIntent);
        });



    }




    public Todo getTodoAt(int position) {
        return getItem(position);
    }

    class TodoHolder extends RecyclerView.ViewHolder{


        public TodoHolder(TodoItemBinding todoItemBinding) {
            super(binding.getRoot());
            binding = todoItemBinding;
        }
        public void bind(Todo todo, TodoClickCallBack todoClickCallBack){
            itemView.setOnClickListener(v -> todoClickCallBack.showTodo(todo));
            binding.textViewTitle.setText(todo.getTitle());
            binding.textViewDescription.setText(todo.getDescription());
        }
    }



}
