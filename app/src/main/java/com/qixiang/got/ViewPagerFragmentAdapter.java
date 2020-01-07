package com.qixiang.got;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList<Fragment>();
    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> list)
    {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList != null? mList.size():0  ;
    }
}