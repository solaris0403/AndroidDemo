package com.tony.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.lst_anim)
    ListView mLstAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
//        mBtnAnim.startAnimation(animation);
//        Animation animation = new Rotate3dAnimation(0, 360, 200, 200, 20, true);
//        animation.setDuration(2000);
//        mBtnAnim.startAnimation(animation);
        ObjectAnimator.ofFloat(mLstAnim, "translationX", 0, 100);
        update();
    }
    private void update(){
        String[] strings = new String[30];
        for (int i = 0; i<strings.length; i++){
            strings[i] = "-------"+i;
        }
        LayoutAnimationController controller = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.anim_item));
        controller.setDelay(0.5F);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, strings);
        mLstAnim.setLayoutAnimation(controller);
        mLstAnim.setAdapter(arrayAdapter);
    }
}
