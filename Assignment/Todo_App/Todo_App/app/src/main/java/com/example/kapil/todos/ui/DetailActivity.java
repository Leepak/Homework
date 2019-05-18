package com.example.kapil.todos.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kapil.todos.R;
import com.example.kapil.todos.databinding.ActivityDetailBinding;
import com.example.kapil.todos.model.Todo;
import com.example.kapil.todos.view_model.TodoViewModel;

import dagger.android.AndroidInjection;

public class DetailActivity extends AppCompatActivity {

    private static final String TODO = "todo";
    ActivityDetailBinding binding;
    Todo todo;
    TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        todo = getIntent().getParcelableExtra(TODO);

        updateView(todo);

    }

    private void updateView(Todo todo){
        binding.titleText.setText(todo.getTitle());
        binding.detailText.setText(todo.getDescription());


        binding.deleteTodo.setOnClickListener(v -> {
            showDeleteDialog(todo);
        });
        binding.editTodo.setOnClickListener( v -> {
            Intent editIntent = new Intent(this, EditTodoActivity.class);
            editIntent.putExtra("todo",todo);
            startActivity(editIntent);
        });
    }

    private void showDeleteDialog(Todo todo) {



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure, you want to delete this?").
                setPositiveButton("Yes", (dialog, which) -> {
                    viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
                    viewModel.delete(todo);
                    dialog.dismiss();
                    Intent mainIntent = new Intent(this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    Toast.makeText(this, "Todo deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Delete Todo");
        alertDialog.show();

    }


}
