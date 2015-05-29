package net.icarapovic.ytsmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.models.Movie;

public class CastFragment extends Fragment{

    public static CastFragment newInstance(Bundle args){
        CastFragment cf = new CastFragment();
        cf.setArguments(args);
        return cf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cast, container, false);

        ImageView dirImg, actImg1, actImg2, actImg3, actImg4;
        TextView dirName, actName1, actName2, actName3, actName4;

        dirImg = (ImageView) v.findViewById(R.id.director_img);
        dirName = (TextView) v.findViewById(R.id.director);
        actImg1 = (ImageView) v.findViewById(R.id.actorImg1);
        actImg2 = (ImageView) v.findViewById(R.id.actorImg2);
        actImg3 = (ImageView) v.findViewById(R.id.actorImg3);
        actImg4 = (ImageView) v.findViewById(R.id.actorImg4);
        actName1 = (TextView) v.findViewById(R.id.actorName1);
        actName2 = (TextView) v.findViewById(R.id.actorName2);
        actName3 = (TextView) v.findViewById(R.id.actorName3);
        actName4 = (TextView) v.findViewById(R.id.actorName4);




        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
