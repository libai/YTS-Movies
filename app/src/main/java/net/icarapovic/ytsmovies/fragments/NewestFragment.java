package net.icarapovic.ytsmovies.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.MovieListAdapter;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.models.ListMovies;
import net.steamcrafted.loadtoast.LoadToast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewestFragment extends Fragment{

    LoadToast lt;

    public static NewestFragment newInstance(){
        NewestFragment mf = new NewestFragment();
        return mf;
    }

    public static String getTitle(){
        return "Newest";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lt = new LoadToast(getActivity());
        lt.setText("Fetching data...").setProgressColor(Color.BLUE).setTranslationY(300).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newest, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        final RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        new Server().getRecentMovies(1, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, Response response) {
                recyclerView.setAdapter(new MovieListAdapter(getActivity(), listMovies.getListMoviesResponse().getMovies()));
                lt.success();
            }

            @Override
            public void failure(RetrofitError error) {
                lt.error();
            }
        });
        return v;
    }
}
