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
    public static final String TITLE = "Newest";

    public static NewestFragment newInstance(){
        return new NewestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lt = new LoadToast(getActivity());
        lt.setText(Server.FETCHING_DATA).setProgressColor(Color.BLUE).setTranslationY(300).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_newest, container, false);
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

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
