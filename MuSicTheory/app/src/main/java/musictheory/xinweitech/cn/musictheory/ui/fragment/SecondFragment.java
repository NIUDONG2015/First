package musictheory.xinweitech.cn.musictheory.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.base.BaseFragment;


/**
 * Created by niudong on 2017/1/12.
 */

public class SecondFragment extends BaseFragment {

    @BindView(R.id.recyle_view)
    RecyclerView recyleView;

    @Override
    protected View setContentView() {
        return mLayoutInflater.inflate(R.layout.fragment_second, null);
    }

    @Override
    protected void init() {

    }


    /*private void initRefreshLayout() {

        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshData();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

           *//*     if (currPage <= totalPage)
                    loadMoreData();
                else {
//                    Toast.makeText()
                    mRefreshLaout.finishRefreshLoadMore();
                }*//*
            }
        });
    }*/


    private void refreshData() {


    }

    private void loadMoreData() {

    }


}
