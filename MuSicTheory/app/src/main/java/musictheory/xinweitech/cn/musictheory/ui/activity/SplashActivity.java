package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.os.Handler;

import butterknife.ButterKnife;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;

/**
 * Created by niudong on 2017/1/12.
 */


public class SplashActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class, null);
                finish();
            }
        }, 2000);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

}
