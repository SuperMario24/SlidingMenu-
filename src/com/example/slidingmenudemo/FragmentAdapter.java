package com.example.slidingmenudemo;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{

	private List<Fragment> fragments;
	private Context context;

	
	public FragmentAdapter(FragmentManager fm, List<Fragment> fragments,
			Context context) {
		super(fm);
		this.fragments = fragments;
		this.context = context;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
