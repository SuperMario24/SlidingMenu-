package com.example.slidingmenudemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity{

	private ViewPager vp;
	private List<Fragment> fragments;
	private FragmentAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vp = (ViewPager) findViewById(R.id.vp);
		
		fragments = new ArrayList<Fragment>();
		fragments.add(new Fragment1());
		fragments.add(new Fragment2());
		
		adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, this);
		vp.setAdapter(adapter);
	}

}
