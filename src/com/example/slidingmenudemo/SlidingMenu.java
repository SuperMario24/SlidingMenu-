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

	//ֻ����һ��onMeasure();
	private boolean once;

	public boolean isOpen = false;

	
	/**
	 * ��ʹ�����Զ�������ʱ�����ô˹��췽��
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}



	/**
	 * δʹ���Զ�������ʱ����
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//��ȡ��Ļ�Ŀ�
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		
		//��ȡ�˵��Ŀ�ȣ���200dpת��Ϊpx
		mMenuWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, context.getResources().getDisplayMetrics());
	}

	/**
	 * ������View�Ŀ�͸ߣ������Լ��Ŀ�͸�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!once){
			//��ȡHorizontalScrollView�ĵ�һ����View
			mWapper = (LinearLayout) getChildAt(0);
			//��ȡ�˵�
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			//��ȡ����
			mContent = (ViewGroup) mWapper.getChildAt(1);

			//�������ݵĿ�ȣ�������Ļ�Ŀ��
			mContent.getLayoutParams().width = mScreenWidth;

			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * ͨ������ƫ��������Menu����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		//���ε��ã������ַ����仯ʱ����
		if(changed){
			//������Ϊ��ֵʱ���������������ƶ�
			this.scrollTo(mMenuWidth, 0);
		}
	}

	/**
	 * ���õ�onTouchEvent����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}

	/**
	 * �򿪲˵�
	 */
	public void onShow() {
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}
	/**
	 * �رղ˵�
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
	 * ��������ʱ
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l*1.0f/mMenuWidth;//1~0
		//�������Զ���������TranslationX
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		
	}
}
