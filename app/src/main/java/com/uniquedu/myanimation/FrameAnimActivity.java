package com.uniquedu.myanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FrameAnimActivity extends AppCompatActivity {

    @InjectView(R.id.imageview_anim)
    ImageView imageviewAnim;
    @InjectView(R.id.button_start)
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button_start)
    public void onClick() {
        imageviewAnim.setBackgroundResource(R.drawable.anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageviewAnim.getBackground();
        animationDrawable.start();
    }
}
