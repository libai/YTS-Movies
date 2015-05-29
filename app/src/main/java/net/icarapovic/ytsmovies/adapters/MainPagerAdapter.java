package net.icarapovic.ytsmovies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.icarapovic.ytsmovies.fragments.NewestFragment;
import net.icarapovic.ytsmovies.fragments.UpcomingFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

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
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return NewestFragment.TITLE;
            case 1:
                return UpcomingFragment.TITLE;
            default:
                return "Unknown" + position;
        }
    }
}