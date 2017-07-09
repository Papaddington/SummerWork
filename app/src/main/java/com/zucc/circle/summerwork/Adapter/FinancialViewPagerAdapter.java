package com.zucc.circle.summerwork.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 圆圈 on 2017-07-07.
 */

public class FinancialViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragemntList;
    private List<String> names;
    public FinancialViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> name) {
        super(fm);
        fragemntList = fragments;
        names = name;
    }
    @Override
    public Fragment getItem(int position) {
        return fragemntList.get(position);
    }

    @Override
    public int getCount() {
        return fragemntList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
