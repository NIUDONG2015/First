package musictheory.xinweitech.cn.musictheory.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 自定义loading
 * Created by niudong on 2016/6/30.
 */
public class InterceptEventLinearLayout extends LinearLayout {

    public InterceptEventLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
