package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;

/**
 * Created by niudong on 2017/1/12.
 */


public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.user_view)
    LinearLayout userView;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_email_name)
    TextView tvEmailName;
    @BindView(R.id.rl_verify)
    RelativeLayout rlVerify;
    @BindView(R.id.rl_users)
    RelativeLayout cHangePwd;
    @BindView(R.id.changge_pwd)
    RelativeLayout rlUsers;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.rl_love_go)
    RelativeLayout rlLoveGo;
    @BindView(R.id.bt_logout)
    Button btLogout;
    private String username;
    private TextView tv_verify;
    private LinearLayout ll_verify;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        ll_verify = (LinearLayout) findViewById(R.id.ll_verify);
        Intent intent = getIntent();

        username = intent.getStringExtra("news_title_f");

        StatusBarCompat.compat(this, getResources().getColor(R.color.statusbar_blue));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvEmailName.setText(username);
    }

    public static void actionStart(Context context, String emailName) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra("news_title_f", emailName);
        context.startActivity(intent);

    }

    @OnClick({R.id.ll_back, R.id.changge_pwd, R.id.rl_verify, R.id.rl_users, R.id.rl_update, R.id.rl_love_go, R.id.bt_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                UserInfoActivity.this.finish();
                break;

            case R.id.changge_pwd:
                showSnackbar(userView, getResources().getString(R.string.change_pwd));
                startActivity(ChangePassWordActivity.class, null);

                break;
            case R.id.rl_verify:
                showSnackbar(userView, getResources().getString(R.string.go_verify));
                break;
            case R.id.rl_users:
                showSnackbar(userView, getResources().getString(R.string.stay_tuned_for_our));
                break;
            case R.id.rl_update:
                showSnackbar(userView, getResources().getString(R.string.check_update));
                break;
            case R.id.rl_love_go:
                showSnackbar(userView, getResources().getString(R.string.stay_tuned_for_our));
                break;
            case R.id.bt_logout:
                Intent intent = new Intent(getResources().getString(R.string.offline));
                sendBroadcast(intent);
                break;
        }
    }
}

