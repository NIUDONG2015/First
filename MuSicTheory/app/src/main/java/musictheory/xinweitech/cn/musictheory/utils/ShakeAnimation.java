package musictheory.xinweitech.cn.musictheory.utils;

import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

public class ShakeAnimation extends RotateAnimation {
	private float mFromDegrees;
	private float mToDegrees;
	 
	private int mPivotXType = ABSOLUTE;
	private int mPivotYType = ABSOLUTE;
	private float mPivotXValue = 0.0f;
	private float mPivotYValue = 0.0f;
	
	private float mPivotX;
	private float mPivotY;
	
	public ShakeAnimation(float fromDegrees, float toDegrees, int pivotXType,
			float pivotXValue, int pivotYType, float pivotYValue) {
		super(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		
		mPivotXValue = pivotXValue;
		mPivotXType = pivotXType;
		mPivotYValue = pivotYValue;
		mPivotYType = pivotYType;
		initializePivotPoint();
	}

	private void initializePivotPoint() {
		if (mPivotXType == ABSOLUTE) {
		    mPivotX = mPivotXValue;
		}
		if (mPivotYType == ABSOLUTE) {
		    mPivotY = mPivotYValue;
		}
	}
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		float degrees = 0;
		if(interpolatedTime <= 0.25f){
			interpolatedTime *= 4;
			degrees = mFromDegrees + ((mToDegrees - mFromDegrees) * interpolatedTime);
		} else if(interpolatedTime <= 0.5f){
			interpolatedTime = (interpolatedTime - 0.25f) * 4;
			degrees = mToDegrees + ((mFromDegrees - mToDegrees) * interpolatedTime);
		} else if(interpolatedTime <= 0.75f){
			interpolatedTime = (interpolatedTime - 0.5f) * 4;
			degrees = mFromDegrees + ((mToDegrees - mFromDegrees) * interpolatedTime);
		} else {
			interpolatedTime = (interpolatedTime - 0.75f) * 4;
			degrees = mToDegrees + ((mFromDegrees - mToDegrees) * interpolatedTime);
		}
		
		float scale = getScaleFactor();
	       
		if (mPivotX == 0.0f && mPivotY == 0.0f) {
	        t.getMatrix().setRotate(degrees);
	    } else {
	        t.getMatrix().setRotate(degrees, mPivotX * scale, mPivotY * scale);
	    }
	}
	
	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
	    mPivotX = resolveSize(mPivotXType, mPivotXValue, width, parentWidth);
	    mPivotY = resolveSize(mPivotYType, mPivotYValue, height, parentHeight);
	}
}
