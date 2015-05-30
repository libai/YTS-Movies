package net.icarapovic.ytsmovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.models.Actor;
import net.icarapovic.ytsmovies.models.Director;
import net.icarapovic.ytsmovies.models.Movie;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastFragment extends Fragment{

    Bundle args;
    public static final String NAME = "Cast";

    public static CastFragment newInstance(Bundle args){
        CastFragment cf = new CastFragment();
        cf.setArguments(args);
        return cf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cast, container, false);
        Context c = getActivity().getApplicationContext();

        ImageView actImg1, actImg2, actImg3, actImg4;
        TextView dirName, actName1, actName2, actName3, actName4, char1, char2, char3, char4;
        CircleImageView dirImg;

        dirImg = (CircleImageView) v.findViewById(R.id.director_img);
        dirName = (TextView) v.findViewById(R.id.director);
        actImg1 = (ImageView) v.findViewById(R.id.actImg1);
        actImg2 = (ImageView) v.findViewById(R.id.actImg2);
        actImg3 = (ImageView) v.findViewById(R.id.actImg3);
        actImg4 = (ImageView) v.findViewById(R.id.actImg4);
        actName1 = (TextView) v.findViewById(R.id.actName1);
        actName2 = (TextView) v.findViewById(R.id.actName2);
        actName3 = (TextView) v.findViewById(R.id.actName3);
        actName4 = (TextView) v.findViewById(R.id.actName4);
        char1 = (TextView) v.findViewById(R.id.char1);
        char2 = (TextView) v.findViewById(R.id.char2);
        char3 = (TextView) v.findViewById(R.id.char3);
        char4 = (TextView) v.findViewById(R.id.char4);

        Picasso.with(c).load(args.getString(Director.MEDIUM_IMAGE)).into(dirImg);
        Picasso.with(c).load(args.getStringArray(Actor.MEDIUM_IMG)[0]).into(actImg1);
        Picasso.with(c).load(args.getStringArray(Actor.MEDIUM_IMG)[1]).into(actImg2);
        Picasso.with(c).load(args.getStringArray(Actor.MEDIUM_IMG)[2]).into(actImg3);
        Picasso.with(c).load(args.getStringArray(Actor.MEDIUM_IMG)[3]).into(actImg4);

        dirName.setText(args.getString(Director.NAME));
        actName1.setText(args.getStringArray(Actor.NAME)[0] + " as");
        actName2.setText(args.getStringArray(Actor.NAME)[1] + " as");
        actName3.setText(args.getStringArray(Actor.NAME)[2] + " as");
        actName4.setText(args.getStringArray(Actor.NAME)[3] + " as");
        char1.setText(args.getStringArray(Movie.CHARACTERS)[0]);
        char2.setText(args.getStringArray(Movie.CHARACTERS)[1]);
        char3.setText(args.getStringArray(Movie.CHARACTERS)[2]);
        char4.setText(args.getStringArray(Movie.CHARACTERS)[3]);

        return v;
    }
}