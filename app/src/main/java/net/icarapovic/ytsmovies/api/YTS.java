package net.icarapovic.ytsmovies.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.MovieListAdapter;
import net.icarapovic.ytsmovies.models.ListMovies;
import net.icarapovic.ytsmovies.models.Movie;
import net.icarapovic.ytsmovies.responses.ListMoviesResponse;
import net.steamcrafted.loadtoast.LoadToast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class YTS {

    // base API URL
    public static final String BASE_URL = "http://yts.to/api/v2";
    // default values for empty query
    public static final int DEFAULT_LIMIT = 20; // 1-50

    ListMoviesResponse listMoviesResponse;
    Movie[] movies;

    final RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.NONE) // change to FULL for debug
            .build();

    ApiRequest service = adapter.create(ApiRequest.class);

    public void getRecentMovies(final Context c, final RecyclerView recyclerView, int page){

        service.listMovies(null, DEFAULT_LIMIT, page, null, null, null, null, null, false, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, retrofit.client.Response response) {
                listMoviesResponse = listMovies.getListMoviesResponse();
                movies = listMoviesResponse.getMovies();
                recyclerView.setAdapter(new MovieListAdapter(c, movies));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(c, R.string.error, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void searchByQuery(final Context c, String query, final RecyclerView recyclerView){

        service.search(query, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, Response response) {
                listMoviesResponse = listMovies.getListMoviesResponse();
                movies = listMoviesResponse.getMovies();
                if(movies.length == 0){
                    Toast.makeText(c, R.string.no_results,Toast.LENGTH_LONG).show();
                }
                recyclerView.setAdapter(new MovieListAdapter(c, movies));
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(c, R.string.error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchByFilter(final Context c, String query, String quality, String genre, String sort, String order, String rating, int page, final RecyclerView recyclerView){

        service.listMovies(query, DEFAULT_LIMIT, page, quality, rating, genre, sort, order, false, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, Response response) {
                listMoviesResponse = listMovies.getListMoviesResponse();
                movies = listMoviesResponse.getMovies();
                if(movies.length == 0)
                    Toast.makeText(c, R.string.no_results, Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new MovieListAdapter(c, movies));
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(c, R.string.error, Toast.LENGTH_LONG).show();
            }
        });
    }

}

