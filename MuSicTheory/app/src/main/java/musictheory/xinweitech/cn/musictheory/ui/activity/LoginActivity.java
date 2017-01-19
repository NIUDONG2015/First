package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.presenter.LoginPresenter;
import musictheory.xinweitech.cn.musictheory.receiver.MyReceiver;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;


/**
 * Created by niudong on 2017/1/11.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private SharedPreferences userInfo;//用户信息缓存
    private SharedPreferences.Editor edit;
    private static final String mPhoneNumPattern = "^[1][0-9]{10}$";
    private static final String mEmailPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    private static final String mPasswordPattern = "[a-zA-Z0-9]{6,18}";
    @BindView(R.id.login_view)
    LinearLayout loginView;
    private LoginPresenter presenter;
    private IntentFilter intentFilter;
    private String username;
    private String pwd;
    private ProgressDialog dialog;
    @BindView(R.id.ll_reg_to)
    LinearLayout lv_reg_to;
    @BindView(R.id.login_email)
    EditText login_email;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.ll_forget_pwd)
    LinearLayout ll_forget_pwd;

    //deng lu zhang hao
    private ArrayList<String> numbers = new ArrayList<>();
    //mi ma
    private ArrayList<String> passWordList = new ArrayList<>();
    private String usernameLast;
    private String passWordLast;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //广播过滤
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //注册广播
        registerReceiver(MyReceiver.getInstance(), intentFilter);
        StatusBarCompat.compat(this, getResources().getColor(R.color.statusbar_white));

        // 将上一次登录信息回显
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        usernameLast = userInfo.getString("reg_email", "");
        passWordLast = userInfo.getString("reg_pwd", "");
        edit = userInfo.edit();

        if (!TextUtils.isEmpty(usernameLast) && (!TextUtils.isEmpty(passWordLast))) {
            login_email.setText(usernameLast);
            etPwd.setText(passWordLast);
   /*         login.setBackgroundColor(getResources().getColor(R.color.longin_bgclik));
            login.setTextColor(getResources().getColor(R.color.colorwhite));*/
        } else {
            showSnackbar(loginView, getResources().getString(R.string.first_login_go_reg));
        }

    }


    @Override
    protected void initListener() {
        lv_reg_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RegisterActivity.class, null);
            }
        });

        ll_forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast(getResources().getString(R.string.take_you_retrieve_password));
                startActivity(ResetPassWordActivity.class, null);
            }
        });
/**
 * 此方法在输入密码 点击键盘离开的时候触发
 */

        etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {

                    Log.i(TAG, "chu fa la");
                    checkInfo();
                    return true;
                }
                return false;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInfo();
            }
        });
    }

    @Override
    protected void initData() {
  /*      username = login_email.getText().toString();
        pwd = etPwd.getText().toString();
        presenter = new LoginPresenter(LoginActivity.this);*/
/*
        boolean checkUserInfo = checkUserInfo(username, pwd);

        if (checkUserInfo) {
            dialog.show();
            presenter.longin(username, pwd);
        } else {//选择不全哦！
            Toast.makeText(LoginActivity.this, "输入用户名和密码", Toast.LENGTH_SHORT).show();
        }*/


    }



    /*
    deng lu success
     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
     startActivity(intent);
     finish();*/


    /**
     * 检查信息
     *
     * @return
     */

    private void checkInfo() {
        // 如果账号是admin且密码是123456，就认为登录成功
        username = login_email.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();


        if (checkUserName(username) && (checkPassword(pwd))) {

            if (username.equals(usernameLast) && pwd.equals(passWordLast)) {

                ShowToast(getResources().getString(R.string.login_success));
                LoginSuccess();
                HomeActivity.actionStart(this, username);
                finish();
            } else {
                showSnackbar(loginView, getResources().getString(R.string.check_the_account_information_valid));
                return;
            }
        } else {


            showSnackbar(loginView, getResources().getString(R.string.email_or_pwd_erro));
            return;
        }
    }



    /**
     * 登录成功保存登录状态
     */
    private void LoginSuccess() {
        // 将账号密码以及userid保存起来

        edit.putString("lastLoginPwd", pwd);
        edit.putString("lastLoginUser", username);
        edit.commit();
    }
    /**
     * 检验用户输入——界面相关逻辑处理
     *
     * @param username
     * @param password
     * @return
     */
/*    private boolean checkUserInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        } else {
            return true;
        }
    }*/


    /**
     * 登陆成功
     */
/*    public void success() {//子线程
        Log.i("xiancheng  1no", Thread.currentThread().getName());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 登陆成功
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Log.i("xiancheng  2zhu", Thread.currentThread().getName());
            }
        });
    }*/

    /**
     * 登陆失败
     */

/*    public void failed() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
                Log.e("xiancheng  3zhu", Thread.currentThread().getName());
            }
        });

    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(MyReceiver.getInstance());
        Log.i(TAG, "onDestroy:unregisterReceiver ");
    }


    private boolean checkUserName(String userName) {
        if (TextUtils.isEmpty(userName)) {
            return false;
        }
        if (!Pattern.matches(mEmailPattern, userName) && !Pattern.matches(mPhoneNumPattern, userName)) {
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        if (!Pattern.matches(mPasswordPattern, password)) {
            return false;
        }
        return true;
    }
}
