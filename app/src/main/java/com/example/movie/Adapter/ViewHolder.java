package com.example.movie.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;


    private ViewHolder(Context context,ViewGroup parent,int layoutId,int postion){
        this.mPosition = postion;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     *
     */

    public static ViewHolder getInstance(Context context,View convertView,ViewGroup parent,int layoutId,int postion){
        if (convertView == null){
            return new ViewHolder(context, parent, layoutId, postion);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     *
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     *
     */

    public ViewHolder setText(int viewId,String text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     *
     */
    //public ViewHolder setImageBitmap(int viewId,Bitmap bm){
    public ViewHolder setImageBitmap(int viewId,int bm){
        ImageView view = getView(viewId);
        view.setImageResource(bm);
        return this;
    }

    public int getPosition(){
        return mPosition;
    }

    public View getConvertView(){
        return mConvertView;
    }

}
