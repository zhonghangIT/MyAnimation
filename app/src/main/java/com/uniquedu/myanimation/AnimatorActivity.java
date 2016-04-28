package com.uniquedu.myanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AnimatorActivity extends AppCompatActivity {

    @InjectView(R.id.imageview_center)
    ImageView imageviewCenter;
    @InjectView(R.id.button_start)
    Button buttonStart;
    private static final String TAG = "ANIMATOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button_start)
    public void onClick() {
//        startObjectAnimator();
//        startPropertyAnimator();
//        startSetAnimator();
        startAnimatorFromXML();
    }

    private void startAnimatorFromXML() {
        Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.animator_set);
        animator.setTarget(imageviewCenter);
        animator.start();
    }

    private void startSetAnimator() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageviewCenter, "scaleX", 1.0f, 0f, 1.0f).setDuration(2000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageviewCenter, "scaleY", 1.0f, 0f, 1.0f).setDuration(2000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageviewCenter, "rotationX", 0, 360).setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);//这两个一块进行
        animatorSet.play(animator3).after(animator2);//animator2播放完毕进行animator3
        animatorSet.start();
    }

    private void startPropertyAnimator() {
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f, 1.0f);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f, 1.0f);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotationX", 0, 360);
        ObjectAnimator animator = new ObjectAnimator().ofPropertyValuesHolder(imageviewCenter, holderX, holderY, rotate).setDuration(2000);
        animator.start();
    }

    /**
     * 只改变一个属性
     */
    private void startObjectAnimator() {
        //默认属性动画时间是300毫秒，切记将setDuration设置在offloat之后，此处的scaleX是imageviewCenter的一个属性
        //属性动画就是将该对象(imageviewCenter)的属性(scaleX)在特定的时间(duration)内变化(1.0---0---1.0)
        //旋转放大缩小有一个中心点，默认是图片的正中心和View的左上角不同。 更改可以设置pivotX
        ObjectAnimator animator = new ObjectAnimator().ofFloat(imageviewCenter, "scaleX", 1.0f, 0, 1.0f).setDuration(2000);
        //添加监听器，AnimatorListenerAdapter是AnimatorListener的子类
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                Log.d(TAG, "动画结束");
            }
        });
        animator.start();
    }
}
