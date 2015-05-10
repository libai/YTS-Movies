package net.icarapovic.ytsmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.api.YTS;

public class ResultActivity extends AppCompatActivity {

    private Intent data;
    private String query, quality, genre, sort, order, rating;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayoutManager llm;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);

        init();

        displayData();
    }

    private void init(){
        data = getIntent();

        query = data.getStringExtra("query");
        quality = data.getStringExtra("quality");
        genre = data.getStringExtra("genre");
        sort = data.getStringExtra("sort");
        order = data.getStringExtra("order");
        rating = data.getStringExtra("rating");

        llm = new LinearLayoutManager(this);


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void displayData(){
        new YTS().searchByFilter(getApplicationContext(), query, quality, genre, sort, order, rating, page, recyclerView);
    }



}
