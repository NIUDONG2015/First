package musictheory.xinweitech.cn.musictheory.adapter.caiadapter;

import android.content.Context;

import java.util.List;

/**
 * Created by niudong on 2017/1/11.
 */
public abstract class SimpleAdapter<T> extends BaseAdapter<T,BaseViewHolder> {

    public SimpleAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public SimpleAdapter(Context context, int layoutResId, List<T> datas) {
        super(context, layoutResId, datas);
    }


}
