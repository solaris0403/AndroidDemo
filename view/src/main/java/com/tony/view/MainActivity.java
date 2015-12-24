package com.tony.view;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        final View view = findViewById(R.id.view_pass);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator.ofFloat(view, "translationX", 0, 100).setDuration(2000).start();
                pass(v);
            }
        });
    }

    final int startX = 0;
    final int deltaX = 100;

    private void pass(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float faction = animation.getAnimatedFraction();
                view.scrollTo(-(startX + (int)(deltaX*faction)), 0);
            }
        });
        animator.start();
    }
}
