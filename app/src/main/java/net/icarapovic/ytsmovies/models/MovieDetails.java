package net.icarapovic.ytsmovies.models;

import net.icarapovic.ytsmovies.responses.MovieDetailsResponse;

public class MovieDetails {

    private String status;
    private String status_message;
    private MovieDetailsResponse data;

    MovieDetails(){}

    public String getStatus() {
        return status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public MovieDetailsResponse getData() {
        return data;
    }
}
