package com.tony.bitmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.img_pic)
    ImageView mImgPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mImgPic.setImageBitmap(ImageResizer.decodeSampledBitmapFromResource(getResources(), R.mipmap.bg_desktop, 100, 150));
        mImgPic.setBackgroundResource(R.mipmap.bg_desktop);
    }
}
