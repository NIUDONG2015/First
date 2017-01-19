package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.entity.CheckBoxBean;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;

/**
 * Created by niudong on 2017/1/12.
 */


public class HomeDetailActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.home_detail_title)
    TextView homeDetailTitle;
    @BindView(R.id.cb_check)
    CheckBox cB_check;
    private String newsTitle;

    private LinearLayout home_detail_view;
    private SharedPreferences userInfo;//用户信息缓存
    private SharedPreferences.Editor edit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_home_detail);
        ButterKnife.bind(this);
        //setSupportActionBar(toolbar);
        home_detail_view = (LinearLayout) findViewById(R.id.home_detail_view);
        newsTitle = getIntent().getStringExtra("news_title_f");
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        edit = userInfo.edit();

        //回显选中状态

        Boolean checkBoxLast = userInfo.getBoolean("CheckBox", false);

        cB_check.setChecked(checkBoxLast);
        StatusBarCompat.compat(HomeDetailActivity.this, getResources().getColor(R.color.statusbar_blue));
    }

    @Override
    protected void initListener() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        cB_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

//                if (!compoundButton.isPressed()) return;
                if (b) {

                    showSnackbar(home_detail_view, getResources().getString(R.string.selected));
                } else {
                    showSnackbar(home_detail_view, getResources().getString(R.string.no_selected));
                }
                edit.putBoolean("CheckBox", b);
                edit.commit();
                EventBus.getDefault().post(new CheckBoxBean(b, newsTitle));
//传走
            }
        });


    }

    @Override
    protected void initData() {
        homeDetailTitle.setText(newsTitle);
    }

    public static void actionStart(Context context, String newsTitle) {
        Intent intent = new Intent(context, HomeDetailActivity.class);
        intent.putExtra("news_title_f", newsTitle);
        context.startActivity(intent);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }


}
