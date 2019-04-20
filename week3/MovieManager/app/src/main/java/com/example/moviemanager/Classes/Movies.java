package com.example.moviemanager.Classes;

import java.io.Serializable;

public class Movies implements Serializable {
    private String name, image_link, release_date, overview;
    int id, votes;

    public Movies(String name, String image_link, String release_date, String overview, int id, int votes) {
        this.name = name;
        this.image_link = image_link;
        this.release_date = release_date;
        this.overview = overview;
        this.id = id;
        this.votes = votes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getName() {
        return name;
    }

    public String getImage_link(String size) {

        return  "http://image.tmdb.org/t/p/w"+size+ this.image_link;
//        return image_link;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
