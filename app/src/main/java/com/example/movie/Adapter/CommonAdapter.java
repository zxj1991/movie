package com.example.movie.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mData;

    private int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mData2, int itemLayoutId) {
        this.mContext = context;
        this.mData = mData2;
        this.mItemLayoutId = itemLayoutId;
    }

    public int getCount() {
        return mData.size();
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder viewHolder, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.getInstance(mContext, convertView, parent, mItemLayoutId, position);
    }
}
