package com.example.moviemanager.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviemanager.Adapters.MovieAdapter;
import com.example.moviemanager.Classes.Movies;
import com.example.moviemanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllMovies extends Fragment {


    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private MovieAdapter movieAdapter;
    private List<Movies> movie_list;
    private int page = 1;

    public AllMovies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_all_movies, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_movie_list);
        movie_list = new ArrayList<>();
        load_data_from_server(page);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter(getActivity(), movie_list);
        recyclerView.setAdapter(movieAdapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == movie_list.size() - 1) {
                    page++;
                    load_data_from_server(page);
                }
            }
        });
        return rootView;
    }

    private void load_data_from_server(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://api.themoviedb.org/4/list/1?api_key=5211640eb3a9ddf747fd1085148dd018&page=" + id).build();

                try {
                    Response response = client.newCall(request).execute();
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray array = object.getJSONArray("results");


                    for (int i = 0; i < array.length(); i++) {
                        JSONObject results = array.getJSONObject(i);
                        Movies myMovie = new Movies(results.getString("title"), results.getString("poster_path"), results.getString("release_date"), results.getString("overview"), results.getInt("id"), results.getInt("vote_count"));
                        movie_list.add(myMovie);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                movieAdapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }

}
