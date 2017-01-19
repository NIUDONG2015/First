package musictheory.xinweitech.cn.musictheory.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.adapter.groupadapter.HotelEntityAdapter;
import musictheory.xinweitech.cn.musictheory.base.BaseActivity;
import musictheory.xinweitech.cn.musictheory.entity.CheckBoxBean;
import musictheory.xinweitech.cn.musictheory.entity.HotelEntity;
import musictheory.xinweitech.cn.musictheory.utils.JsonUtils;
import musictheory.xinweitech.cn.musictheory.utils.StatusBarCompat;

public class HomeActivity extends BaseActivity {
    private Context mContext;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.recyle_view_list)
    RecyclerView recyleViewList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private String emailName;
    private HotelEntityAdapter mAdapter;
    private LinearLayout home_view;
    private boolean checkStatus = false;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;

        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        emailName = intent.getStringExtra("emailName");
        home_view =(LinearLayout) findViewById(R.id.home_view);


        swipeLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.GRAY);


        StatusBarCompat.compat(this,  getResources().getColor(R.color.statusbar_blue));
        mAdapter = new HotelEntityAdapter(this);

        HotelEntity entity = JsonUtils.analysisJsonFile(this, "json");
        mAdapter.setData(entity.allTagsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyleViewList.setLayoutManager(linearLayoutManager);
        recyleViewList.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(CheckBoxBean bean) {

        checkStatus = bean.checkStatus;
        Log.e("", "Message from SecondActivity:" + bean.getCheckStatus());
    }

    @Override
    protected void initListener() {
//上来就开始显示 勾选中状态
        llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoActivity.actionStart(mContext, emailName);
            }
        });
    }


    @Override
    protected void initData() {

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //tongzhi 刷新
                        mAdapter.notifyDataSetChanged();
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });


    }


    public static void actionStart(Context context, String emailName) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("emailName", emailName);
        context.startActivity(intent);

    }

}
