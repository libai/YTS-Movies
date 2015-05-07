package net.icarapovic.ytsmovies.models;

import net.icarapovic.ytsmovies.responses.ListMoviesResponse;

public class ListMovies {

    private String status;
    private String status_message;
    private ListMoviesResponse data;

    public ListMovies(){}

    public String getStatus() {
        return status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public ListMoviesResponse getListMoviesResponse() {
        return data;
    }
}
