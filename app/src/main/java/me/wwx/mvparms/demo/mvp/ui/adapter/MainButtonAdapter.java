package me.wwx.mvparms.demo.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.wwx.mvparms.demo.mvp.ui.fragment.MineFragment;
import me.wwx.mvparms.demo.mvp.ui.fragment.PhotoFragment;

/**
 * 作者：wwx on 2017/5/22 0022 16:53
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class MainButtonAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"我的", "日记", "视频","图片"};

    public MainButtonAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) return MineFragment.newInstance();
        if(position == 3) return PhotoFragment.newInstance();
        return new Fragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
