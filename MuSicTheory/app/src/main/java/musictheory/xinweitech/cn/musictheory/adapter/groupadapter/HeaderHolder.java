package musictheory.xinweitech.cn.musictheory.adapter.groupadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import musictheory.xinweitech.cn.musictheory.R;


/**
 * Created by lyd10892 on 2016/8/23.
 */

public class HeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;

    public HeaderHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
        initView();
    }

    private void initView() {
        titleView = (TextView) itemView.findViewById(R.id.header);

    }
}
