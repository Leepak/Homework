package com.example.kapil.todos.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kapil.todos.R;
import com.example.kapil.todos.databinding.ActivityAddToDoBinding;
import com.example.kapil.todos.model.Todo;
import com.example.kapil.todos.view_model.TodoViewModel;

import dagger.android.AndroidInjection;

public class AddToDoActivity extends AppCompatActivity {

    TodoViewModel viewModel;

    ActivityAddToDoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_do);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Add Todo");

        binding.saveTodo.setOnClickListener(v -> {
            saveTodo();
        });


    }

    private void saveTodo() {
        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextDescription.getText().toString();



        if (!title.trim().isEmpty() || !description.trim().isEmpty()) {
            viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

            Todo todo  = new Todo(title,description);
            viewModel.insert(todo);
            Toast.makeText(this, "Todo Added", Toast.LENGTH_SHORT).show();
            Intent gotoMaintIntent = new Intent(AddToDoActivity.this, MainActivity.class);
            startActivity(gotoMaintIntent);
            finish();
        }else{
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
        }

    }
}
