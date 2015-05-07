package net.icarapovic.ytsmovies.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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
    public static final int DEFAULT_PAGE = 1; // # of movies / limit
    public static final int DEFAULT_MINIMAL_RATING = 0; // 1-9
    public static final boolean DEFAULT_RT_RATING = false;
    // movie qualities
    public static final String QUALITY_HD = "720p";
    public static final String QUALITY_FULL_HD = "1080p";
    public static final String QUALITY_3D = "3D";
    // movie genres
    public static final String ACTION = "action";
    public static final String ADVENTURE = "adventure";
    public static final String ANIMATION = "animation";
    public static final String BIOGRAPHY = "biography";
    public static final String COMEDY = "comedy";
    public static final String CRIME = "crime";
    public static final String DOCUMENTARY = "documentary";
    public static final String DRAMA = "drama";
    public static final String FAMILY = "family";
    public static final String FANTASY = "family";
    public static final String FILM_NOIR = "film-noir";
    public static final String HISTORY = "history";
    public static final String HORROR = "horror";
    public static final String MUSIC = "music";
    public static final String MUSICAL = "musical";
    public static final String MYSTERY = "mystery";
    public static final String ROMANCE = "romance";
    public static final String SCI_FI = "sci-fi";
    public static final String SPORT = "sport";
    public static final String THRILLER = "thriller";
    public static final String WAR = "war";
    public static final String WESTERN = "western";
    // sort
    public static final String TITLE = "title";
    public static final String YEAR = "year";
    public static final String RATING = "rating";
    public static final String PEERS = "peers";
    public static final String SEEDS = "seeds";
    public static final String DOWNLOADS = "download_count";
    public static final String LIKES = "like_count";
    public static final String DATE = "date_added";
    // order
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    ListMoviesResponse listMoviesResponse;
    Movie[] movies;

    final RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.NONE) // change to FULL for debug
            .build();

    ApiRequest service = adapter.create(ApiRequest.class);

    public void getMovieList(final Context c, final RecyclerView recyclerView){

        service.listMovies(null, DEFAULT_LIMIT, DEFAULT_PAGE, null, DEFAULT_MINIMAL_RATING, null, null, null, DEFAULT_RT_RATING, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, retrofit.client.Response response) {
                listMoviesResponse = listMovies.getListMoviesResponse();
                movies = listMoviesResponse.getMovies();
                recyclerView.setAdapter(new MovieListAdapter(c, movies));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(c, "Error getting data", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void search(final Context c, String query, final RecyclerView recyclerView){

        service.search(query, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, Response response) {
                listMoviesResponse = listMovies.getListMoviesResponse();
                movies = listMoviesResponse.getMovies();
                if(movies.length == 0){
                    Toast.makeText(c, "Query returned 0 results",Toast.LENGTH_LONG).show();
                }
                recyclerView.setAdapter(new MovieListAdapter(c, movies));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(c, "Error getting data", Toast.LENGTH_LONG).show();
            }
        });
    }

}

