package musictheory.xinweitech.cn.musictheory.utils;

import android.view.animation.Interpolator;

public class SmoothInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float t) {
    	//传说中MIUI使用的就是这个插值公式
    	return (float) Math.sqrt(1 - (1 - t) * (1 - t));
    }
}
