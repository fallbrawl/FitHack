package com.example.android.fithack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.fithack.Fragments.ExerciseFragment;
import com.example.android.fithack.Fragments.HealthFragment;
import com.example.android.fithack.Fragments.MotivationFragment;
import com.example.android.fithack.Fragments.NewsFragment;

/**
 * Created by nexus on 27.10.2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "News", "Health", "Motivation", "Exercise" };

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewsFragment();
        } else if (position == 1) {
            return new HealthFragment();
        }
        else if (position == 2){
            return new MotivationFragment();
        } else {
            return new ExerciseFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
