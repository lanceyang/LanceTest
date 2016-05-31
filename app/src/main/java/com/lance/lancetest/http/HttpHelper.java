package com.lance.lancetest.http;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.lance.lancetest.listener.HttpRequestCallback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lance on 16/5/19.
 */
public class HttpHelper{


    private  static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

    private static BasicHttpTaskManager taskManager = new BasicHttpTaskManager();

    private static Handler requestHandler = new Handler();

    private static HttpRequestCallback callback = new HttpRequestCallback() {
        @Override
        public void finish() {
            taskManager.pollTask();
            notifyHttp(taskManager.next());
        }
    };

    public static void sendRequest(@NonNull final String url, @NonNull final RequestParam param,final BasicHttpHandler handler){


        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                HttpTask task = new HttpTask();
                task.setHandler(handler);
                task.setUrl(url);
                task.setParam(param);
                taskManager.addTask(task);
                handler.sendOnStart();
                synchronized (HttpUtils.class){
                    notifyHttp(task);
                }
            }
        });
    }

    private  static void notifyHttp(HttpTask task){
        if (HttpUtils.getState()!=BasicHttpConstant.ON_RUNING&&task!=null){
            switch (taskManager.next().getRequestType()){
                case RequestParam.REQUEST_TYPE_POST:
                    HttpUtils.doPost(task,callback);
                    break;
                case  RequestParam.REQUEST_TYPE_GET:
                    HttpUtils.doGet(task,callback);
                    break;

                case RequestParam.REQUEST_TYPE_PUT:
                    HttpUtils.doPut(task,callback);
                    break;
            }

        }
    }


    public static void disConnect(){
        HttpUtils.disConnect();
    }
}
