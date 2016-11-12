package com.example.slidingmenudemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Fragment2 extends Fragment{

	/**
	 * 第二个fragment，里面嵌套了一个SlidingMenu布局，里面嵌套了菜单和内容两个布局
	 */
	private ImageView ivInfo;
	private SlidingMenu slidingMenu;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_2, null);
		
		ivInfo = (ImageView) view.findViewById(R.id.iv_my_info);
		slidingMenu = (SlidingMenu) view.findViewById(R.id.SlidingMenu);
		
		//打开或关闭左侧菜单
		ivInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
		
		
		return view;
	}
}
