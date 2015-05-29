package net.icarapovic.ytsmovies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.icarapovic.ytsmovies.fragments.MovieInfoFragment;

public class DetailsPagerAdapter extends FragmentPagerAdapter {

    private int ITEM_COUNT = 1;
    int id;

    public DetailsPagerAdapter(FragmentManager fm, int id){
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return MovieInfoFragment.newInstance(id);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return MovieInfoFragment.getTitle();
            default:
                return "Unknown";
        }
    }
}
