package musictheory.xinweitech.cn.musictheory.adapter.groupadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import musictheory.xinweitech.cn.musictheory.R;


/**
 * Created by lyd10892 on 2016/8/23.
 */

public class DescHolder extends RecyclerView.ViewHolder {
    public TextView descView;
    public ImageView iv_status;

    public DescHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
        initView();

    }

    private void initView() {
        descView = (TextView) itemView.findViewById(R.id.tv_home_title);//tian jia wen zi
        iv_status = (ImageView) itemView.findViewById(R.id.ib_status);

    }



}
