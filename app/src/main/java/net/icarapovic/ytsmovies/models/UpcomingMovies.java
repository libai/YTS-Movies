package net.icarapovic.ytsmovies.models;

import net.icarapovic.ytsmovies.responses.UpcomingMoviesResponse;

public class UpcomingMovies {

    private String status;
    private String status_message;
    private UpcomingMoviesResponse data;

    public UpcomingMoviesResponse getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return status_message;
    }
}
