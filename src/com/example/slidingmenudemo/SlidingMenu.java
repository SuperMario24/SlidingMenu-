package com.example.slidingmenudemo;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView{

	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;

	private int mMenuWidth;

	//只调用一次onMeasure();
	private boolean once;

	public boolean isOpen = false;

	
	/**
	 * 当使用了自定义属性时，调用此构造方法
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}



	/**
	 * 未使用自定义属性时调用
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//获取屏幕的宽
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		
		//获取菜单的宽度，将200dp转换为px
		mMenuWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, context.getResources().getDisplayMetrics());
	}

	/**
	 * 设置子View的宽和高，设置自己的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!once){
			//获取HorizontalScrollView的第一个子View
			mWapper = (LinearLayout) getChildAt(0);
			//获取菜单
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			//获取内容
			mContent = (ViewGroup) mWapper.getChildAt(1);

			//设置内容的宽度，就是屏幕的宽度
			mContent.getLayoutParams().width = mScreenWidth;

			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 通过设置偏移量，将Menu隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		//会多次调用，当布局发生变化时调用
		if(changed){
			//滚动条为正值时，内容区域向左移动
			this.scrollTo(mMenuWidth, 0);
		}
	}

	/**
	 * 禁用掉onTouchEvent方法
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}

	/**
	 * 打开菜单
	 */
	public void onShow() {
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}
	/**
	 * 关闭菜单
	 */
	public void onHidden() {
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}
	
	
	public void toggle(){
		if(isOpen){
			onHidden();
		}else {
			onShow();
		}
	}
	
	/**
	 * 滚动发生时
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l*1.0f/mMenuWidth;//1~0
		//调用属性动画，设置TranslationX
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		
	}
}
