package net.icarapovic.ytsmovies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.icarapovic.ytsmovies.fragments.NewestFragment;
import net.icarapovic.ytsmovies.fragments.UpcomingFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static int ITEM_COUNT = 2;
    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return NewestFragment.newInstance();
            case 1:
                return UpcomingFragment.newInstance();
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
                return NewestFragment.getTitle();
            case 1:
                return UpcomingFragment.getTitle();
            default:
                return "Unknown" + position;
        }
    }
}