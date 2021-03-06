package com.tony.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by user on 12/28/15.
 */
public class Rotate3dAnimation extends Animation{
    private  float mFromDegree;
    private float mToDegree;
    private float mCenterX;
    private float mCenterY;
    private float mDepthZ;
    private boolean mReverse;
    private Camera mCamera;

    public Rotate3dAnimation(float mFromDegree, float mToDegree, float mCenterX, float mCenterY, float mDepthZ, boolean mReverse) {
        this.mFromDegree = mFromDegree;
        this.mToDegree = mToDegree;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthZ = mDepthZ;
        this.mReverse = mReverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final float fromDegrees = mFromDegree;
        float degrees = fromDegrees + ((mToDegree - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();
        camera.save();
        if (mReverse){
            camera.translate(0.0f, 0.0f, mDepthZ*interpolatedTime);
        }else{
            camera.translate(0.0f, 0.0f, mDepthZ*(1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}
