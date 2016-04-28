package com.uniquedu.myanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LayoutAnimationActivity extends AppCompatActivity {


    @InjectView(R.id.button_add)
    Button buttonAdd;
    @InjectView(R.id.checkbox_appear)
    CheckBox checkboxAppear;
    @InjectView(R.id.checkbox_change_appear)
    CheckBox checkboxChangeAppear;
    @InjectView(R.id.checkbox_disappear)
    CheckBox checkboxDisappear;
    @InjectView(R.id.checkbox_change_disappear)
    CheckBox checkboxChangeDisappear;
    @InjectView(R.id.gridlayout)
    GridLayout gridlayout;
    @InjectView(R.id.button_layout)
    Button buttonLayout;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        ButterKnife.inject(this);
    }

    public void addViewToGrid() {
        index++;
        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        button.setText("测试数据" + index);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridlayout.setLayoutTransition(getLayoutTransition());
                gridlayout.removeView(v);
            }
        });
        gridlayout.setLayoutTransition(getLayoutTransition());
        gridlayout.addView(button, gridlayout.getChildCount() > 0 ? 1 : 0);
    }

    private LayoutTransition getLayoutTransition() {
        LayoutTransition transition = new LayoutTransition();
        ObjectAnimator appear = ObjectAnimator.ofFloat(this, "scaleX", 0, 1).setDuration(1000);
        ObjectAnimator disappear = ObjectAnimator.ofFloat(this, "scaleX", 1, 0).setDuration(1000);
        if (checkboxAppear.isChecked()) {
            transition.setAnimator(LayoutTransition.APPEARING, appear);
        }
        if (checkboxChangeAppear.isChecked()) {
            transition.setStagger(LayoutTransition.CHANGE_APPEARING, 30);//设置产生动画的多个View的间隔
            PropertyValuesHolder pvLeft = PropertyValuesHolder.ofInt("left", 0, 1);
            PropertyValuesHolder pvTop = PropertyValuesHolder.ofInt("top", 0, 1);
            PropertyValuesHolder pvRight = PropertyValuesHolder.ofInt("right", 0, 1);
            PropertyValuesHolder pvBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
            PropertyValuesHolder pvScaleX = PropertyValuesHolder.ofFloat("scaleX", 1, 0, 1);
            PropertyValuesHolder pvScaleY = PropertyValuesHolder.ofFloat("scaleY", 1, 0, 1);
            ObjectAnimator change_appear = ObjectAnimator.ofPropertyValuesHolder(this, pvLeft, pvRight, pvTop, pvBottom, pvScaleX, pvScaleY);
            change_appear.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    View view = (View) ((ObjectAnimator) animation).getTarget();
                    //动画结束时记得将view的属性更改成原标准
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                }
            });
            transition.setAnimator(LayoutTransition.CHANGE_APPEARING, change_appear);
            transition.setDuration(1000);
        }
        if (checkboxDisappear.isChecked()) {
            transition.setAnimator(LayoutTransition.DISAPPEARING, disappear);
        }
        if (checkboxChangeDisappear.isChecked()) {
            transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);//设置产生动画的多个View的间隔
            //left top right bottom必须设置，这里的动画不会被覆盖
            PropertyValuesHolder pvLeft = PropertyValuesHolder.ofInt("left", 0, 1);
            PropertyValuesHolder pvTop = PropertyValuesHolder.ofInt("top", 0, 1);
            PropertyValuesHolder pvRight = PropertyValuesHolder.ofInt("right", 0, 1);
            PropertyValuesHolder pvBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
            //后边这个是设置动画
            PropertyValuesHolder pvScaleX = PropertyValuesHolder.ofFloat("scaleX", 1, 0, 1);
            PropertyValuesHolder pvScaleY = PropertyValuesHolder.ofFloat("scaleY", 1, 0, 1);
            ObjectAnimator change_disappear = ObjectAnimator.ofPropertyValuesHolder(this, pvLeft, pvRight, pvTop, pvBottom, pvScaleX, pvScaleY);
            change_disappear.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    View view = (View) ((ObjectAnimator) animation).getTarget();
                    //动画结束时记得将view的属性更改成原标准
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                }
            });
            transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, change_disappear);
            //设置动画的时长
            transition.setDuration(1000);
        }
        return transition;
    }

    @OnClick({R.id.button_add, R.id.button_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                addViewToGrid();
                break;
            case R.id.button_layout:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
                LayoutAnimationController lac = new LayoutAnimationController(animation);
                lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
                lac.setDelay(0.7f);
                gridlayout.setLayoutAnimation(lac);
                break;
        }
    }
}
