package com.example.lluismasdeu.pprog2_p_final.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by eloy on 09-05-2017.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    public static class TabEntry {
        private Fragment fragment;
        private String name;

        public TabEntry(Fragment fragment, String name) {
            this.fragment = fragment;
            this.name = name;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public String getName() {
            return name;
        }
    }

    private List<TabEntry> entries;


    public TabsAdapter(FragmentManager fm, List<TabEntry> entries) {
        super(fm);
        this.entries = entries;
    }

    @Override
    public Fragment getItem(int position) {
        return entries.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return entries.get(position).getName();
    }
}
