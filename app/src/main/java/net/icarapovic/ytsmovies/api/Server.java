package net.icarapovic.ytsmovies.api;

import net.icarapovic.ytsmovies.models.ListMovies;
import net.icarapovic.ytsmovies.models.MovieDetails;
import net.icarapovic.ytsmovies.models.UpcomingMovies;

import retrofit.Callback;
import retrofit.RestAdapter;

public class Server {

    // base API URL
    public static final String BASE_URL = "http://yts.to/api/v2";
    public static final String FETCHING_DATA = "Fetching data...";

    final RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.NONE) // change to FULL for debug
            .build();

    ApiRequest service = adapter.create(ApiRequest.class);

    public void getRecentMovies(int page, Callback<ListMovies> callback){
        service.listMovies(null, 30, page, null, null, null, null, null, false, callback);
    }

    public void searchByFilter(String query, String quality, String genre, String sort, String order, String rating, int page, Callback<ListMovies> callback){
        service.listMovies(query, 20, page, quality, rating, genre, sort, order, false, callback);
    }

    public void getMovieDetails(int id, Callback<MovieDetails> callback){
        service.getMovieDetails(id, true, true, callback);
    }

    public void getUpcomingMovies(Callback<UpcomingMovies> callback){
        service.getUpcomingMovies(callback);
    }

}

