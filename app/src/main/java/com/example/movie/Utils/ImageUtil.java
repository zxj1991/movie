package com.example.movie.Utils;

import android.graphics.Bitmap;

import com.example.movie.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class ImageUtil {
    private static ImageUtil imageutil;
    private DisplayImageOptions options;

    public static ImageUtil getInstance() {
        if (imageutil == null)
            imageutil = new ImageUtil();
        return imageutil;
    }

    public void Imagecache() {
        this.options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.pic)
                .showImageForEmptyUri(R.drawable.pic)
                .showImageOnFail(R.drawable.pic)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .delayBeforeLoading(100)//载入图片前稍做延时可以提高整体滑动的流畅度
                .build();
    }

    public DisplayImageOptions getOptions() {
        return this.options;
    }
}
