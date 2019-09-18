package com.example.demotest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyBaseAdapter extends BaseAdapter
{
    private List<Data> list;
    private LayoutInflater layoutInflater;
    private LruCacheClass lruCacheClass;
    private ImageLoader imageLoader;
    public MyBaseAdapter(Context context,List<Data> list)
    {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        lruCacheClass = new LruCacheClass();
    }
    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.item_list,null);
        }
            ImageView iv_pic = convertView.findViewById(R.id.iv_pic);
            TextView tv_title = convertView.findViewById(R.id.tv_title);
            TextView tv_content = convertView.findViewById(R.id.tv_content);
            iv_pic.setImageResource(R.mipmap.ic_launcher);
            String url = list.get(position).getPicSmall();
            iv_pic.setTag(url);
            if(lruCacheClass.getBitmapFromCache(url) != null)
            {
                iv_pic.setImageBitmap(lruCacheClass.getBitmapFromCache(url));
            }
            else
            {
                imageLoader = new ImageLoader(lruCacheClass,url,iv_pic);
                imageLoader.loadImageByAsyncTask();
            }
            tv_title.setText(list.get(position).getName());
            tv_content.setText(list.get(position).getDescription());

        return convertView;
    }
}
