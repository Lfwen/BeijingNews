package com.newsapp.lfw.beijingnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.newsapp.lfw.beijingnews.activity.GuideActivity;
import com.newsapp.lfw.beijingnews.utils.CacheUtils;

public class LauncherActivity extends Activity {

    public static final String START_MAIN = "start_main";
    private RelativeLayout activity_launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        activity_launcher = (RelativeLayout) findViewById(R.id.activity_launcher);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setFillAfter(true);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);

        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);
        set.setDuration(2000);
        set.setAnimationListener(new MyAnimationListener());

        activity_launcher.startAnimation(set);


    }

    class MyAnimationListener implements Animation.AnimationListener
    {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(LauncherActivity.this, START_MAIN);
            if(isStartMain)
            {
                //直接进入主界面
            }
            else
            {
                //进入引导页面
                Intent intent = new Intent(LauncherActivity.this, GuideActivity.class);
                startActivity(intent);
            }
//            Toast.makeText(LauncherActivity.this, "动画播放结束", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
