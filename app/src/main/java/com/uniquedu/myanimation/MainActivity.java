package com.uniquedu.myanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.button_zhuzhen)
    Button buttonZhuzhen;
    @InjectView(R.id.button_set)
    Button buttonSet;
    @InjectView(R.id.button_property)
    Button buttonProperty;
    @InjectView(R.id.button_layout)
    Button buttonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.button_zhuzhen, R.id.button_set, R.id.button_property, R.id.button_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_zhuzhen:
                startActivity(new Intent(getApplicationContext(), FrameAnimActivity.class));
                break;
            case R.id.button_set:
                startActivity(new Intent(getApplicationContext(), TweenedActivity.class));
                break;
            case R.id.button_property:
                startActivity(new Intent(getApplicationContext(), AnimatorActivity.class));
                break;
            case R.id.button_layout:
                startActivity(new Intent(getApplicationContext(), LayoutAnimationActivity.class));
                break;
        }
    }


}
