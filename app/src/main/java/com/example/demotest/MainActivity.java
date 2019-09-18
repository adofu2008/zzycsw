package com.example.demotest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    String bastURL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private ListView lv_list = null;
    MyBaseAdapter myBaseAdapter = null;
    private int listStart,listStop;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        lv_list = (ListView)findViewById(R.id.lv_list);
        lv_list.setOnScrollListener(new listOnScrollChangeListener());
        getInfoFromNet();
    }
    private class listOnScrollChangeListener implements AbsListView.OnScrollListener
    {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState)
        {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
        {
            listStart = firstVisibleItem;
            listStop = firstVisibleItem+visibleItemCount;
        }
    }
    private void getInfoFromNet()
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
            .url(bastURL)
            .get()
            .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {
                Log.d("MainActivity","onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                Log.d("MainActivity","onResponse");
                String responseJson = response.body().string();
                Gson gson = new Gson();
                InfoBean infoBean = gson.fromJson(responseJson,InfoBean.class);
                Log.d("MainActivity","InfoBean"+infoBean.toString());
                myBaseAdapter = new MyBaseAdapter(MainActivity.this,infoBean.getData());
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        lv_list.setAdapter(myBaseAdapter);
                    }
                });

            }
        });
    }
}
