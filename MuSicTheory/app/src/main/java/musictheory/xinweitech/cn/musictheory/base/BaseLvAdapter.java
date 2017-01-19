package musictheory.xinweitech.cn.musictheory.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import musictheory.xinweitech.cn.musictheory.ui.view.GifView;

/**
 * winsion
 * Created by yalong on 2016/6/6.
 */
public abstract class BaseLvAdapter<T> extends BaseAdapter {

    private List<T> mData;
    private Context mContext;
    private int mLayoutId;

    /**
     * @param context
     * @param data     填充的数据集合
     * @param layoutId 条目布局ID
     */
    public BaseLvAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = ViewHolder.get(mContext, view, viewGroup, mLayoutId, i);
        convert(viewHolder, mData.get(i), i);
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder viewHolder, T t, int position);

    /**
     * ViewHolder
     */
    public static class ViewHolder {
        private SparseArray<View> mViews;
        private View mConvertView;

        private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
            this.mViews = new SparseArray<>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            mConvertView.setTag(this);
        }

        public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            }
            return (ViewHolder) convertView.getTag();
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public View getConvertView() {
            return mConvertView;
        }

        public void setText(int viewId, String s) {
            TextView textView = getView(viewId);
            textView.setText(s);
        }

        public void setText(int viewId, SpannableStringBuilder builder) {
            TextView textView = getView(viewId);
            textView.setText(builder);
        }

        public void setButtonText(int viewId, String s) {
            Button button = getView(viewId);
            button.setText(s);
        }

        public void setImage(int viewId, int resId) {
            ImageView imageView = getView(viewId);
            imageView.setImageResource(resId);
        }

        public void setImage(int viewId, String path) {
            ImageView imageView = getView(viewId);
            Glide.with(imageView.getContext()).load(new File(path)).crossFade().into(imageView);
            imageView.setOnClickListener((v) -> {
                Intent it = new Intent(Intent.ACTION_VIEW);
                it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
                imageView.getContext().startActivity(it);
            });
        }

        public void setImageFromVideo(int viewId, Bitmap bitmap, String path) {
            ImageView imageView = getView(viewId);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            imageView.setBackground(bitmapDrawable);
            imageView.setOnClickListener((v) -> {
                Intent it = new Intent(Intent.ACTION_VIEW);
                it.setDataAndType(Uri.fromFile(new File(path)), "video/*");
                imageView.getContext().startActivity(it);
            });
        }

        public void setImageButtonOnClickListener(int viewId, View.OnClickListener listener) {
            ImageButton imageButton = getView(viewId);
            imageButton.setOnClickListener(listener);
        }

        public void setTextColor(int viewId, String color) {
            TextView textView = getView(viewId);
            textView.setTextColor(Color.parseColor(color));
        }

        public void setTextColor(int viewId, int color) {
            TextView textView = getView(viewId);
            textView.setTextColor(color);
        }

        public void setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
        }

        public void setVisibility(int viewId, boolean isShow) {
            View view = getView(viewId);
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }

        public void setBgColor(int viewId, int resId) {
            View view = getView(viewId);
            view.setBackgroundResource(resId);
        }

        public void setGif(int viewId, int resId) {
            GifView view = getView(viewId);
            view.setMovieResource(resId);
        }

        public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
        }

        public void setEnable(int viewId, boolean isEnable) {
            View view = getView(viewId);
            view.setEnabled(isEnable);
        }
    }
}
