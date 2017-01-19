package musictheory.xinweitech.cn.musictheory.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yalong on 2016/6/13.
 */
public abstract class BaseFragment extends Fragment {

    protected LayoutInflater mLayoutInflater;
    private View mContentView;
    protected Context mContext;
    protected Handler mHandler;
    //    public ApiService apiService;
    public Vibrator vibrator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        mContext = getContext();
        mHandler = new Handler();
//        apiService = RetrofitManager.getInstance().getService();
        //振动器
//        vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        // 缓存Fragment，防止切换造成UI的重绘
        if (mContentView == null) {
            mContentView = setContentView();
            ButterKnife.bind(this, mContentView);
            init();
        }
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    /**
     * 设置Fragment的内容View
     */
    protected abstract View setContentView();

    /**
     * 初始化
     */
    protected abstract void init();
}
