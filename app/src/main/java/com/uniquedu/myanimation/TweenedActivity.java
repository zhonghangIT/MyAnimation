package com.uniquedu.myanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TweenedActivity extends AppCompatActivity {

    @InjectView(R.id.button_translate)
    Button buttonTranslate;
    @InjectView(R.id.button_rotate)
    Button buttonRotate;
    @InjectView(R.id.button_sclae)
    Button buttonSclae;
    @InjectView(R.id.button_alpha)
    Button buttonAlpha;
    @InjectView(R.id.button_set)
    Button buttonSet;
    @InjectView(R.id.imageview_center)
    ImageView imageviewCenter;
    private static final String TAG = "TWEENED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.button_translate, R.id.button_rotate, R.id.button_sclae, R.id.button_alpha, R.id.button_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_translate:
                TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            Log.d(TAG, "动画开始");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Log.d(TAG, "动画结束");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            Log.d(TAG, "动画重复");
                        }
                    });
                translateAnimation.setInterpolator(getApplicationContext(),android.R.anim.decelerate_interpolator);
                imageviewCenter.startAnimation(translateAnimation);
                break;
            case R.id.button_rotate:
                RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_rotate);
                imageviewCenter.startAnimation(rotateAnimation);
                break;
            case R.id.button_sclae:
                ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scale);
                imageviewCenter.startAnimation(scaleAnimation);
                break;
            case R.id.button_alpha:
                AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha);
                imageviewCenter.startAnimation(alphaAnimation);
                break;
            case R.id.button_set:
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_set);
                imageviewCenter.startAnimation(animation);
                break;
        }
    }
}
