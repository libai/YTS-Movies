package net.icarapovic.ytsmovies.api;

import net.icarapovic.ytsmovies.models.ListMovies;
import net.icarapovic.ytsmovies.models.MovieDetails;
import net.icarapovic.ytsmovies.models.UpcomingMovies;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiRequest {
    @GET("/list_movies.json")
    void listMovies(
            @Query("query_term") String query,
            @Query("limit") int limit,
            @Query("page") int page,
            @Query("quality") String quality,
            @Query("minimum_rating") String rating,
            @Query("genre") String genre,
            @Query("sort_by") String sort,
            @Query("order_by") String order,
            @Query("with_rt_rating") boolean rt_rating,
            Callback<ListMovies> response
    );

    @GET("/movie_details.json")
    void getMovieDetails(
            @Query("movie_id") int id,
            @Query("with_images") boolean extraImages,
            @Query("with_cast") boolean withCast,
            Callback<MovieDetails> response
    );

    @GET("/list_movies.json")
    void search(
            @Query("query_term") String query,
            Callback<ListMovies> response
    );

    @GET("/list_upcoming.json")
    void getUpcomingMovies(
            Callback<UpcomingMovies> response
    );
}
