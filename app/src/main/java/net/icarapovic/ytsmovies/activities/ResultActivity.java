package net.icarapovic.ytsmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.MovieListAdapter;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.models.ListMovies;
import net.icarapovic.ytsmovies.models.Movie;
import net.steamcrafted.loadtoast.LoadToast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResultActivity extends AppCompatActivity {

    private String query, quality, genre, sort, order, rating;
    private RecyclerView recyclerView;
    private LoadToast lt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);

        // initialize objects and reference views
        init();

        // fetch data from server
        displayData();
    }

    private void init(){
        Intent data = getIntent();
        lt = new LoadToast(this);
        lt.setText("Fetching results...").setTranslationY(300).show();

        query = data.getStringExtra("query");
        quality = data.getStringExtra("quality");
        genre = data.getStringExtra("genre");
        sort = data.getStringExtra("sort");
        order = data.getStringExtra("order");
        rating = data.getStringExtra("rating");

        LinearLayoutManager llm = new LinearLayoutManager(this);


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void displayData(){
        Server server = new Server();
        int page = 1;
        server.searchByFilter(query, quality, genre, sort, order, rating, page, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, Response response) {
                Movie[] movies = listMovies.getListMoviesResponse().getMovies();
                if(movies.length == 0)
                    Toast.makeText(getApplicationContext(), R.string.no_results, Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new MovieListAdapter(ResultActivity.this, movies));
                recyclerView.getAdapter().notifyDataSetChanged();
                lt.success();
            }

            @Override
            public void failure(RetrofitError error) {
                lt.error();
            }
        });
    }






}
