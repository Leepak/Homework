package com.example.kapil.todos.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.kapil.todos.R;
import com.example.kapil.todos.adapter.TodoAdapter;
import com.example.kapil.todos.clickcallback.TodoClickCallBack;
import com.example.kapil.todos.databinding.ActivityMainBinding;
import com.example.kapil.todos.view_model.TodoViewModel;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    private TodoViewModel todoViewModel;
    ActivityMainBinding binding;
    TodoAdapter adapter;
    private static final String TODO = "todo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.noteRecyclerView.setHasFixedSize(true);
        binding.noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        setUpViewModel();
        setUpAdapter();
        swipeDelete();



        binding.addTodoFab.setOnClickListener(v -> {
            Intent addIntent = new Intent(MainActivity.this,AddToDoActivity.class);
            startActivity(addIntent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                todoViewModel.deleteAll();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void setUpAdapter(){
        TodoClickCallBack todoClickCallBack = todo -> {
          Intent intent = new Intent(MainActivity.this, DetailActivity.class);
          intent.putExtra(TODO, todo);
          startActivity(intent);
        };
        adapter = new TodoAdapter(todoClickCallBack, this);
        binding.noteRecyclerView.setAdapter(adapter);
    }

    private void setUpViewModel(){
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        todoViewModel.getTodoList().observe(this,todos -> {
            adapter.submitList(todos);
        });
    }
    private void swipeDelete(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                showDeleteDialog(viewHolder);
            }
        }).attachToRecyclerView(binding.noteRecyclerView);
    }


    private void showDeleteDialog(RecyclerView.ViewHolder viewHolder) {



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure, you want to delete this?").
                setPositiveButton("Yes", (dialog, which) -> {
                    todoViewModel.delete(adapter.getTodoAt(viewHolder.getAdapterPosition()));
                    dialog.dismiss();

                    Toast.makeText(MainActivity.this, "Todo Deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Delete Todo");
        alertDialog.show();

    }


}
