package com.example.moviemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviemanager.Classes.Movies;

public class MovieActivity2 extends AppCompatActivity {
    TextView title, release_date, votes, description;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.txt_movie_name);
        release_date = (TextView) findViewById(R.id.txt_release_date);
        votes= (TextView) findViewById(R.id.txt_votes);
        description= (TextView) findViewById(R.id.txt_desc);

        imageView = (ImageView) findViewById(R.id.img_movie_image);
        Movies movies = (Movies) getIntent().getSerializableExtra("movie");
        Glide.with(this).load(movies.getImage_link("400")).into(imageView);

        this.setTitle(movies.getName());
        release_date.setText("Release Date : " + movies.getRelease_date());
        votes.setText("Total Votes : "+ movies.getVotes() );
        description.setText(movies.getOverview());
    }
}
