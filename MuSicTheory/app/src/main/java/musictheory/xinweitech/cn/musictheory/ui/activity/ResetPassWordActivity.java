package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.ui.view.CustomDialog;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;

/**
 * Created by niudong on 2017/1/18.
 */


public class ResetPassWordActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.et_reg_email)
    EditText etRegEmail;
    @BindView(R.id.bt_next_go)
    Button btNextGo;
    @BindView(R.id.reset_view)
    LinearLayout regSetView;
    private CustomDialog customDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reset_pwd);
        StatusBarCompat.compat(this, getResources().getColor(R.color.statusbar_white));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.ll_back, R.id.bt_next_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bt_next_go:

                //pang duan EditText shu ru nei rong
                showDiaLog();
                break;
        }
    }

    public void showDiaLog() {
        customDialog = new CustomDialog(this);
        customDialog.setTitle(getResources().getString(R.string.send_success));

        customDialog.setMessage(getResources().getString(R.string.reset_password_send_your_email));


        customDialog.setYesOnclickListener(getResources().getString(R.string.confirm2), new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                showSnackbar(regSetView, getResources().getString(R.string.confirm2));
                customDialog.dismiss();
            }
        });
        customDialog.show();

     /*
       argb
       Window window = customDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.5f;
        window.setAttributes(lp);*/
    }

}