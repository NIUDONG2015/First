package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;


/**
 * Created by niudong on 2017/1/11.
 */
public class RegisterActivity extends BaseActivity implements TextWatcher {

    private static final String mPhoneNumPattern = "^[1][0-9]{10}$";
    private static final String mEmailPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    private static final String mPasswordPattern = "[a-zA-Z0-9]{6,18}";
    @BindView(R.id.reg_view)
    LinearLayout regView;
    private String email;
    private String pwd;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.regist)

    Button regist;
    private SharedPreferences userInfo;//用户信息缓存
    private SharedPreferences.Editor edit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this, getResources().getColor(R.color.statusbar_white));
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        edit = userInfo.edit();
    }

    @Override
    protected void initListener() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //核实信息
                verfiyInfo();
            }
        });
    }


    @Override
    protected void initData() {

    }



    public void verfiyInfo() {

        email = etEmail.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();

        //校验邮箱名称
        if (checkUserName(email)) {
            verfiyPwd();
        } else {
            showSnackbar(regView, getResources().getString(R.string.email_verfiy_erro));
            return;
        }
    }

    private void verfiyPwd() {

        if (checkPassword(pwd)) {
            gotoLogin();
        } else {
            showSnackbar(regView, getResources().getString(R.string.password_hint));
            return;
        }
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

    //  验证完成后去 注册  registerOK再登录
    //  checkVcodel 验证码
    //跳转登陆
    private void gotoLogin() {
        edit.putString("reg_email", email);
        edit.putString("reg_pwd", pwd);
        edit.commit();
        showSnackbar(regView, getResources().getString(R.string.reg_ok_go_login));
        startActivity(LoginActivity.class, null);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    //这个方法做校验操作
    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }




 /*
    public void sendCode() {
        if (!requestPermission()) {
            return;
        }
        if (tilNumber.getEditText().getText().toString().length() != 11) {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (LocalAccountManager.getInstance(this).exist(tilNumber.getEditText().getText().toString())) {
            Toast.makeText(this, "手机号已经注册", Toast.LENGTH_SHORT).show();
            return;
        }
        SMSManager.getInstance().sendMessage(this, "86", tilNumber.getEditText().getText().toString());
    }

    public void register() {
        if (TextUtils.isEmpty(tilName.getEditText().getText().toString())) {
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tilPassword.getEditText().getText().toString().length() < 6 || tilPassword.getEditText().getText().toString().length() > 12) {
            Toast.makeText(this, "请输入6-12位密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tilCode.getEditText().getText().toString())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        SMSManager.getInstance().verifyCode(this, "86", tilNumber.getEditText().getText().toString(), tilCode.getEditText().getText().toString(), new Callback() {
            @Override
            public void success() {
                if (LocalAccountManager.getInstance(RegisterActivity.this).create(RegisterActivity.this,
                        new Account(
                                tilNumber.getEditText().getText().toString(),
                                tilPassword.getEditText().getText().toString(),
                                tilName.getEditText().getText().toString()))) {
                    Intent i = new Intent();
                    i.putExtra("number", tilNumber.getEditText().getText().toString());
                    i.putExtra("password", tilPassword.getEditText().getText().toString());
                    setResult(RESULT_OK, i);
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void error(Throwable error) {
                Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSManager.getInstance().unRegisterTimeListener(this);
    }

    @Override
    public void onLastTimeNotify(int lastSecond) {
        if (lastSecond > 0)
            btnCode.setText(lastSecond + "秒后重新发送");
        else
            btnCode.setText("发送验证码");
    }

    @Override
    public void onAbleNotify(boolean valuable) {
        btnCode.setEnabled(valuable);
    }

    public boolean requestPermission() {
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "please give me the permission", Toast.LENGTH_SHORT).show();
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE},
                        EXTERNAL_STORAGE_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQ_CODE: {
                // 如果请求被拒绝，那么通常grantResults数组为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //申请成功，进行相应操作
                    sendCode();
                } else {
                    //申请失败，可以继续向用户解释。
                }
                return;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

}
