package musictheory.xinweitech.cn.musictheory.base;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.ui.activity.LoginActivity;
import musictheory.xinweitech.cn.musictheory.utils.ActivityCollector;
import musictheory.xinweitech.cn.musictheory.utils.ToastUtil;


/**
 * Created by niudong on 2017/1/11.
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    private Context mContext;
    private Toast mToast;
    private LayoutInflater mInflater;
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //设定屏幕朝向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActivityCollector.addActivity(this);
        mInflater = LayoutInflater.from(mContext);

        initView();
        ButterKnife.bind(this);
        initData();
        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getResources().getString(R.string.offline));
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();


    /**
     * 跳转Activity
     *
     * @param cls
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 获取输入的文字
     *
     * @param textView
     * @return
     */
    public String getText(TextView textView) {
        return textView.getText().toString();
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    public void showToast(int resId) {
        ToastUtil.showToast(mContext, resId);
    }

    public boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public void showSnackbar(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }


    // 自定义Toast
    public void ShowToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                //对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);

        }

        return true;
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(getResources().getString(R.string.logout_current_account));
            builder.setCancelable(false);
            builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll(); // 销毁所有活动
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent); // 重新启动LoginActivity
                }
            });

            builder.setNegativeButton(getResources().getString(R.string.let_me_see), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.RemoveActivity(this);
    }
}
