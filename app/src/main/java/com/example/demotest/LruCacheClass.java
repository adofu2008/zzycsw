package com.example.demotest;

import android.graphics.Bitmap;
import android.util.LruCache;

public class LruCacheClass
{
    private LruCache<String,Bitmap> lruCache;
    public LruCacheClass()
    {
        int maxCache = (int)Runtime.getRuntime().maxMemory();
        lruCache = new LruCache<String,Bitmap>(maxCache/4){
            @Override
            protected int sizeOf(String key, Bitmap value)
            {
                return value.getByteCount();
            }
        };
    }
    public void addBitmapToCache(String url, Bitmap bitmap)
    {
        if(lruCache.get(url) == null)
        {
            lruCache.put(url,bitmap);
        }
    }
    public Bitmap getBitmapFromCache(String url)
    {
        return lruCache.get(url);
    }
}
