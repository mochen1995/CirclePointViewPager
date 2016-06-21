package com.example.mmcc.circlepointviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by mmcc on 2016/6/21.
 */
public class CirclePointViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener{
    private RelativeLayout rl;
    private ImageView BluePoint;
    private int left;
    private ViewPager viewPager;
    private Drawable pointSrc;
    private Drawable pointHintSrc;

    public CirclePointViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CirclePointViewPager);
        pointSrc = a.getDrawable(R.styleable.CirclePointViewPager_pointSrc);
        pointHintSrc = a.getDrawable(R.styleable.CirclePointViewPager_pointHintSrc);
        if(pointSrc==null)
        {
            pointSrc = getResources().getDrawable(R.drawable.blue_point);
        }
        if(pointHintSrc==null)
        {
            pointHintSrc = getResources().getDrawable(R.drawable.blue_hint_point);
        }
        a.recycle();
        initViewpager();
    }

    private void initViewpager() {
        viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        viewPager.setOnPageChangeListener(this);
        addView(viewPager);
    }

    public void setAdapter(PagerAdapter adapter){
        viewPager.setAdapter(adapter);
        if(viewPager.getAdapter()!=null)
        {
            initCirclePoint(getContext());
        }

    }
    private void initCirclePoint(Context context) {
        rl = new RelativeLayout(context);
        rl.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.bottomMargin = 30;
        addView(rl,lp);
        int count = viewPager.getAdapter().getCount();
        for(int i=0;i<count;i++)
        {
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(pointHintSrc);
            RelativeLayout.LayoutParams imglp = new RelativeLayout.LayoutParams(30,30);
            if(i>0)
            {
                imglp.leftMargin =50 * i;
            }

            rl.addView(imageView,imglp);
        }
        BluePoint = new ImageView(context);
        BluePoint.setImageDrawable(pointSrc);
        RelativeLayout.LayoutParams imglp = new RelativeLayout.LayoutParams(30,30);
        rl.addView(BluePoint,imglp);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(left==0)
            left = rl.getChildAt(1).getLeft() - rl.getChildAt(0).getLeft();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(left!=0)
        {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) BluePoint.getLayoutParams();
            lp.leftMargin = (int) (left * positionOffset + left * position);
            rl.requestLayout();
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
