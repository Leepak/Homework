package com.example.kapil.todos.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kapil.todos.R;
import com.example.kapil.todos.databinding.ActivityEditTodoBinding;
import com.example.kapil.todos.model.Todo;
import com.example.kapil.todos.ui.MainActivity;
import com.example.kapil.todos.view_model.TodoViewModel;

import dagger.android.AndroidInjection;

public class EditTodoActivity extends AppCompatActivity {

    ActivityEditTodoBinding binding;
    Todo todo;

    TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_todo);



        getSupportActionBar().setTitle("Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        todo = getIntent().getParcelableExtra("todo");


        populateFields(todo);

        saveTodo();

    }

    private void populateFields(Todo todo){
        binding.editTextTitle.setText(todo.getTitle());
        binding.editTextDescription.setText(todo.getDescription());
    }

    private void saveTodo(){
        binding.saveTodo.setOnClickListener(v -> {
            String title = binding.editTextTitle.getText().toString();
            String description = binding.editTextDescription.getText().toString();
            int id = todo.getId();

            if (!title.trim().isEmpty() || !description.trim().isEmpty()) {
                viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

                Todo todo = new Todo(id,title,description);
                viewModel.update(todo);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                Intent gotoMaintIntent = new Intent(EditTodoActivity.this, MainActivity.class);
                startActivity(gotoMaintIntent);
                finish();
            }else{
                Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
