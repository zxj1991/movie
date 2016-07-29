package com.example.movie.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movie.Bean.FenleiInfo;
import com.example.movie.R;
import com.example.movie.Utils.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ImageUtil imageUtil;
    private List<FenleiInfo> list;

    public GridViewAdapter(Context paramContext) {
        this.context = paramContext;
        this.imageUtil = ImageUtil.getInstance();
        this.imageUtil.Imagecache();
    }

    public void Update(List<FenleiInfo> list) {
        if (list != null)
            this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.list == null)
            return 0;
        return list.size();
    }

    public Object getItem(int position) {
        if (this.list == null)
            return position;
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_fenlei, null);
            viewholder = new ViewHolder();
            viewholder.imageview = (ImageView) convertView.findViewById(R.id.image_fenlei);
            viewholder.textview= (TextView) convertView.findViewById(R.id.textView_fenlei);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.textview.setText(list.get(position).getD_name());
        ImageLoader.getInstance().displayImage(list.get(position).getD_pic(), viewholder.imageview, imageUtil.getOptions());
        return convertView;
    }

    class ViewHolder {
        ImageView imageview;
        TextView textview;
    }

}
