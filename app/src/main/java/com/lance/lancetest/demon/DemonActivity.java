package com.lance.lancetest.demon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lance.lancetest.R;
import com.lance.lancetest.http.BasicHttpHandler;
import com.lance.lancetest.http.HttpHelper;
import com.lance.lancetest.http.RequestParam;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DemonActivity extends Activity {

    private WebView webView;


    private String[] paths;
    private static final String APP_ID = "";

    private ListView listView;
    private List<String> dataList = new ArrayList<>();
    private DemonAdapter adapter;


    int fileSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.requestFocus();
//                Toast.makeText(DemonActivity.this,"click",Toast.LENGTH_SHORT).show();
//                v.invalidate(v.getLeft(),v.getTop()-2,v.getRight(),v.getBottom()-2);
            }
        });


        webView = (WebView) findViewById(R.id.wv);
        webView.loadUrl("file:///android_asset/test.html");

        try {
            paths = getAssets().getLocales();
        }catch (Exception e){
            e.printStackTrace();
        }



        listView = (ListView)findViewById(R.id.list_view);
        adapter = new DemonAdapter(dataList);
        listView.setAdapter(adapter);



        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });


    }



    private void test(){

        webView.invalidate(0,0,0+webView.getWidth(),0+webView.getHeight());

        RequestParam param = new RequestParam();
        param.setRequestType(RequestParam.REQUEST_TYPE_GET);
        HttpHelper.sendRequest("http://www.weather.com.cn/data/cityinfo/101010100.html", param, new BasicHttpHandler(getMainLooper()) {
            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(JSONObject jsonObject) {

                dataList.add(jsonObject.toString());
                adapter.addAllData(dataList);
                adapter.notifyDataSetInvalidated();

            }

            @Override
            protected void onFailed(int errorCode, String msg) {

            }
        });
    }
}