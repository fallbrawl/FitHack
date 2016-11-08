package com.example.android.fithack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.fithack.Fragments.ExerciseFragment;
import com.example.android.fithack.Fragments.HealthFragment;
import com.example.android.fithack.Fragments.MotivationFragment;
import com.example.android.fithack.Fragments.NewsFragment;

import java.util.ArrayList;

/**
 * Created by nexus on 27.10.2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "News", "Health", "Motivation", "Exercise" };
    private ArrayList<Fragment> fragments;

    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
