package com.tony.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ObjectAnimationActivity extends AppCompatActivity {

    @Bind(R.id.btn_view)
    Button mBtnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        ButterKnife.bind(this);
        mBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewWrapper viewWrapper = new ViewWrapper(v);
                ObjectAnimator.ofInt(viewWrapper, "width", 500).setDuration(2000).start();
            }
        });
    }

    private class ViewWrapper{
        private View mTarget;

        public ViewWrapper(View mTarget) {
            this.mTarget = mTarget;
        }

        public void setWidth(int width){
            this.mTarget.getLayoutParams().width = width;
            this.mTarget.requestLayout();
        }

        public int getWidth(){
            return this.mTarget.getLayoutParams().width;
        }
    }
}
