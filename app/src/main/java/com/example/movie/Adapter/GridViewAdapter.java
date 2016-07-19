package com.example.movie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.movie.R;
import com.example.movie.Utils.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ImageUtil imageUtil;
    private ArrayList<String> list;

    public GridViewAdapter(Context paramContext) {
        this.context = paramContext;
        this.imageUtil = ImageUtil.getInstance();
        this.imageUtil.Imagecache();
    }

    public void Update(ArrayList<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_home, null);
            viewholder = new ViewHolder();
            viewholder.imageview = (ImageView) convertView.findViewById(R.id.imageView_shouye);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position), viewholder.imageview, imageUtil.getOptions());
        return convertView;
    }

    class ViewHolder {
        ImageView imageview;
    }

}
