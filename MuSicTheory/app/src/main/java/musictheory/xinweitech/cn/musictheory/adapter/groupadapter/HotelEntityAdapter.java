package musictheory.xinweitech.cn.musictheory.adapter.groupadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import musictheory.xinweitech.cn.musictheory.MusicApplication;
import musictheory.xinweitech.cn.musictheory.R;
import musictheory.xinweitech.cn.musictheory.entity.CheckBoxBean;
import musictheory.xinweitech.cn.musictheory.entity.HotelEntity;
import musictheory.xinweitech.cn.musictheory.ui.activity.HomeDetailActivity;
import musictheory.xinweitech.cn.musictheory.utils.HotelUtils;

import static android.content.ContentValues.TAG;


/**
 * Created by lyd10892 on 2016/8/23.
 */

public class HotelEntityAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {


    public ArrayList<HotelEntity.TagsEntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;

    private SparseBooleanArray mBooleanMap;
    private boolean checkStatus;
    private String beanName;

    public HotelEntityAdapter(Context context) {
        mContext = context;


        Log.d(TAG, "constructor");
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    //zhuce
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        EventBus.getDefault().register(this);
    }

    //zhuxiao
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    public void setData(ArrayList<HotelEntity.TagsEntity> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return HotelUtils.isEmpty(allTagList) ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).getTagInfoList().size();
        if (count >= 8 && !mBooleanMap.get(section)) {
            count = 8;
        }

        return HotelUtils.isEmpty(allTagList.get(section).getTagInfoList()) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.home_head_title, parent, false));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {


        return new DescHolder(mInflater.inflate(R.layout.home_desc_item, parent, false));

    }

    //true
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(CheckBoxBean bean) {

        checkStatus = bean.getCheckStatus();
        beanName = bean.getName();

        Log.e("", "Message from SecondActivity:" + bean.getCheckStatus() + bean.getName());

//在这里show 图片
    }


    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {

        final String name = allTagList.get(section).tagsName;
        holder.titleView.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MusicApplication.mContext, "" + name + section, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(DescHolder holder, int section, final int position) {


        final String tagName = allTagList.get(section).getTagInfoList().get(position).tagName;
        holder.descView.setText(tagName);
        //解决办法  就不进行复用
        holder.setIsRecyclable(false);
        //状态和id进行判断   相等的话就选中
        if (checkStatus) {
            if (beanName.equals(tagName))
//                holder.iv_status.setTag(new Integer(position));//设置tag 否则划回来时选中消失
                holder.iv_status.setVisibility(View.VISIBLE);
        } else {

            holder.iv_status.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeDetailActivity.actionStart(mContext, tagName);
            }
        });


    }

    public ItemOnClickListener mItemOnClickListener;

    public void setmItemOnClickListener(ItemOnClickListener listener) {
        Log.d(TAG, "setmItemOnClickListener...");
        this.mItemOnClickListener = listener;
    }

    public interface ItemOnClickListener {
        /**
         * 传递点击的view
         *
         * @param view
         */
        void itemOnClickListener(View view);
    }

}
