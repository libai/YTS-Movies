package net.icarapovic.ytsmovies.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.icarapovic.ytsmovies.fragments.CastFragment;
import net.icarapovic.ytsmovies.fragments.MovieInfoFragment;

public class DetailsPagerAdapter extends FragmentPagerAdapter {

    Bundle infoArgs, castArgs;

    public DetailsPagerAdapter(FragmentManager fm, Bundle infoArgs, Bundle castArgs){
        super(fm);
        this.castArgs = castArgs;
        this.infoArgs = infoArgs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return MovieInfoFragment.newInstance(infoArgs);
            case 1:
                return CastFragment.newInstance(castArgs);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return MovieInfoFragment.NAME;
            case 1:
                return CastFragment.NAME;
            default:
                return "Unknown";
        }
    }
}
