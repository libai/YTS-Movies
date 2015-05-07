package net.icarapovic.ytsmovies.responses;

import net.icarapovic.ytsmovies.models.Movie;

public class ListMoviesResponse {

    private String limit;
    private Movie[] movies;
    private String page_number;
    private String movie_count;

    public String getLimit() {
        return limit;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public String getPage_number() {
        return page_number;
    }

    public String getMovie_count() {
        return movie_count;
    }
}
