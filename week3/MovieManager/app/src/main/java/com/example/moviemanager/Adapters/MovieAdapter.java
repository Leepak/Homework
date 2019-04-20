package com.example.moviemanager.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviemanager.Classes.Movies;
import com.example.moviemanager.MovieActivity;
import com.example.moviemanager.MovieActivity2;
import com.example.moviemanager.R;

import java.io.Serializable;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private final List<Movies> my_movies;

    public MovieAdapter(Context context, List<Movies> my_movies) {
        this.context = context;
        this.my_movies = my_movies;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card, viewGroup, false);

        return new MovieViewHolder(itemView, this);
//        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder viewHolder, int i) {
        viewHolder.title.setText(my_movies.get(i).getName());
        Glide.with(context).load(my_movies.get(i).getImage_link("185")).into(viewHolder.imageView);
        viewHolder.date.setText("Release Date : "+ my_movies.get(i).getRelease_date());
    }

    @Override
    public int getItemCount() {
        return my_movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title, date;
        public ImageView imageView;


        public MovieViewHolder(@NonNull View itemView, MovieAdapter adapter) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.movie_list_name);
            imageView = (ImageView) itemView.findViewById(R.id.movie_list_image);
            date = (TextView) itemView.findViewById(R.id.movie_list_date);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();

            Movies movies = my_movies.get(position);
            Intent i = new Intent(context, MovieActivity2.class);
            i.putExtra("movie", (Serializable) movies);
            context.startActivity(i);
        }
    }
}
