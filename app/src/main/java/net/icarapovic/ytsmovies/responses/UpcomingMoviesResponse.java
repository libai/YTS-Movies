package net.icarapovic.ytsmovies.responses;

import net.icarapovic.ytsmovies.models.Movie;

public class UpcomingMoviesResponse {

    private int upcoming_movies_count;
    private Movie[] upcoming_movies;

    public int getUpcoming_movies_count() {
        return upcoming_movies_count;
    }

    public Movie[] getMovies() {
        return upcoming_movies;
    }
}
