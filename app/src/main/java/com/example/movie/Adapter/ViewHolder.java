package com.example.movie.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movie.Utils.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private ImageUtil imageUtil;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int postion) {
        this.mPosition = postion;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        imageUtil = ImageUtil.getInstance();
        imageUtil.Imagecache();

    }

    /**
     *
     */

    public static ViewHolder getInstance(Context context, View convertView, ViewGroup parent, int layoutId, int postion) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, postion);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置item的textview
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 设置网络下载图片
     *
     * @param viewId
     * @param list
     */
    public ViewHolder setdownloadImage(int viewId, List<?> list) {
        ImageView view = getView(viewId);
        String url = null;
        for (int i = 0; i < list.size(); i++) {
            url = (String) list.get(i);
        }
        ImageLoader.getInstance().displayImage(url, view, imageUtil.getOptions());

        return this;
    }


    /**
     * 设置图片
     */
    //public ViewHolder setImageBitmap(int viewId,Bitmap bm){
    public ViewHolder setImageBitmap(int viewId, Object bm) {
        ImageView view = getView(viewId);

        if (bm instanceof String) {
            //地址
            ImageLoader.getInstance().displayImage(String.valueOf(bm), view, imageUtil.getOptions());

        } else if (bm instanceof Integer) {
            //本地文件
            view.setImageResource((Integer) bm);
        } else if (bm instanceof Bitmap) {
            //bitmap
            view.setImageBitmap((Bitmap) bm);
        }
        return this;
    }


    public int getPosition() {
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }

}
