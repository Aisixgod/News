package com.sdau.news.adapters;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sdau.news.fragments.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "PagerAdapter";

    private NewsFragment[] fragments;
    private ArrayList<String> types;
    private ArrayList<String> typesCN;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public PagerAdapter(FragmentManager fm, NewsFragment[] fragments, ArrayList<String> types, ArrayList<String> typesCN) {
        super(fm);
        this.fragments = fragments;
        this.types = types;
        this.typesCN = typesCN;
    }


    @Override
    public Fragment getItem(int position) {
      //  Log.d(TAG,"getItem" + position);
        return fragments[position];
    }
    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typesCN.get(position);
    }
}
