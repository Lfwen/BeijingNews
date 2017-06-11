package com.newsapp.lfw.beijingnews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.newsapp.lfw.beijingnews.R;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;

    private ArrayList<ImageView> imageViews;
    /**
     * 两点的间距
     */
    private int leftDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids = new int[]
                {
                        R.drawable.guide_1,
                        R.drawable.guide_2,
                        R.drawable.guide_3
                };

        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++)
        {
            ImageView imageView = new ImageView(GuideActivity.this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            imageViews.add(imageView);
            //创建点
            ImageView point = new ImageView(GuideActivity.this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 单位是像素，需要做适配
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i!=0)
            {
                //不包括第0个，所有的点距左边有10个像素
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);
            //添加点到线性布局中
            ll_point_group.addView(point);
        }

        //设置ViewPager的适配器
        viewPager.setAdapter(new MyPagerAdapter());

        //根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽、边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //得到屏幕滑动的百分比
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());


    }

    class MyPagerAdapter extends PagerAdapter
    {
        //默认创建两个，最多创建三个

        /*
            返回数据的总个数
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         *  作用，类似getView
         * @param container ViewPager
         * @param position 要创建页面的位置
         * @return 返回和创建当前页面有关系的值，可以返回当前页面的视图、返回当前位置position
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
//            return position;
            return imageView;
//            return super.instantiateItem(container, position);
        }

        /**
         *
         * @param view 当前创建的视图
         * @param object instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
//            return view == imageViews.get(Integer.parseInt((String) object));
            return view == object;
        }

        /**
         * 销毁页面
         * @param container ViewPager
         * @param position 要销毁的页面的位置
         * @param object 要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener
    {

        @Override
        public void onGlobalLayout() {
            //执行不只一次，所以监听一次就移除监听
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            leftDes = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
            Toast.makeText(GuideActivity.this, ""+leftDes, Toast.LENGTH_SHORT).show();
            //        Log.d("leftDes", String.valueOf(leftDes));
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener
    {
        /**
         * 当页面滑动了会回调这个方法
         * @param position  当前滑动页面的位置
         * @param positionOffset    页面滑动的百分比
         * @param positionOffsetPixels  滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当页面被选中时回调这个方法
         * @param position 被选中页面对应的位置
         */
        @Override
        public void onPageSelected(int position) {

        }

        /**
         * 当ViewPager页面滑动状态发生变化的时候：拖拽，静止，惯性滚动
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
