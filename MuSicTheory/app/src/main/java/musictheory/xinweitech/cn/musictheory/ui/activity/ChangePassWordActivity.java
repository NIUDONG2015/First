package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;


/**
 * Created by niudong on 2017/1/11.
 */
public class ChangePassWordActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    LinearLayout ivBack;
    @BindView(R.id.ll_change_view)
    LinearLayout changeView;
    @BindView(R.id.et_pre_pwd)
    EditText etPrePwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.commit)
    Button commit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this, getResources().getColor(R.color.font_text_blue));
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.et_pre_pwd, R.id.et_new_pwd, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_pre_pwd:
                break;
            case R.id.et_new_pwd:
                break;
            case R.id.commit:
                //跳转 个人中心或者重新登录
                showSnackbar(changeView, getResources().getString(R.string.commit));
                break;
        }
    }
}
