package com.example.movie.Utils;

import android.graphics.Bitmap;

import com.example.movie.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

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
                .build();
    }

    public DisplayImageOptions getOptions() {
        return this.options;
    }
}
