package com.example.movie;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration condig = new ImageLoaderConfiguration
                .Builder(this)//
                .memoryCacheExtraOptions(480, 800)//
                .threadPoolSize(3)//线程池数量
                .threadPriority(Thread.NORM_PRIORITY - 2)//
                .tasksProcessingOrder(QueueProcessingType.LIFO)//
                .denyCacheImageMultipleSizesInMemory()//
                .memoryCache(new UsingFreqLimitedMemoryCache(2*1024*1024))//
                .memoryCacheSize(2*1024*1024)//
                .diskCacheSize(50*1024*1024)//
                .diskCacheFileCount(100)    //缓存的文件数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//
                .imageDownloader(new BaseImageDownloader(this,5*1000,30*1000))//
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())//
                .writeDebugLogs()
                .build();


        ImageLoader.getInstance().init(condig);
    }

}
