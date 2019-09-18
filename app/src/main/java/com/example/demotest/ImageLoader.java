package com.example.demotest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageLoader
{
    private ImageView imageView;
    private String url;
    private LruCacheClass lruCacheClass;
    public ImageLoader(LruCacheClass lruCacheClass,String url,ImageView imageView)
    {
        this.lruCacheClass = lruCacheClass;
        this.url = url;
        this.imageView = imageView;
    }

    public void loadImageByAsyncTask()
    {
        new AsyncTask<String, Void, Bitmap>()
        {
            @Override
            protected Bitmap doInBackground(String... strings)
            {
                InputStream inputStream = null;
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                    .url(strings[0])
                    .get()
                    .build();
                Call call = okHttpClient.newCall(request);
                try
                {
                    Response response = call.execute();
                    inputStream = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return  bitmap;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }finally
                {
                    try
                    {
                        inputStream.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap)
            {
                super.onPostExecute(bitmap);
                if(ImageLoader.this.imageView.getTag().equals(ImageLoader.this.url))
                {
                    if(ImageLoader.this.lruCacheClass.getBitmapFromCache(ImageLoader.this.url) == null)
                    {
                        ImageLoader.this.lruCacheClass.addBitmapToCache(ImageLoader.this.url,bitmap);
                    }
                    ImageLoader.this.imageView.setImageBitmap(bitmap);
                }
            }
        }.execute(url);
    }
}
