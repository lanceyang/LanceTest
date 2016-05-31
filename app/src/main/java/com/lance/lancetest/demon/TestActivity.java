package com.lance.lancetest.demon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lance.lancetest.R;
import com.lance.lancetest.http.BasicHttpHandler;
import com.lance.lancetest.http.HttpHelper;
import com.lance.lancetest.http.RequestParam;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 16/5/31.
 */
public class TestActivity extends AppCompatActivity{

    private List<DemonHotVideo> list = new ArrayList<>();

    RecyclerView recyclerView;

    DemonRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        setTitle("Test");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new DemonRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        test();

    }

    private void test(){

        RequestParam param = new RequestParam();
        param.setRequestType(RequestParam.REQUEST_TYPE_GET);
        HttpHelper.sendRequest("http://videofile.enorth.com.cn/appnews/tvnews/rdsp/recommend.json", param, new BasicHttpHandler(getMainLooper()) {
            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(JSONObject jsonObject) {
                try {
                    JSONArray array = jsonObject.getJSONArray("array");
                    for (int i = 0 ; i<array.length();i++){
                        list.add(new DemonHotVideo(array.getJSONObject(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                adapter.setDataList(list);
                adapter.notifyDataSetChanged();


            }

            @Override
            protected void onFailed(int errorCode, String msg) {

            }
        });
    }
}
