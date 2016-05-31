package com.lance.lancetest.http;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by lance on 16/5/18.
 */
public class BasicHttpTaskManager {

    private static Queue<HttpTask> requestQueue = new ArrayDeque<>();
    /**
     * 添加到队尾
     * @param task
     * @return
     */
    public boolean addTask(HttpTask task){
        return requestQueue.offer(task);
    }

    /**
     * 移除指定task
     * @param task
     */
    private void removeTask(HttpTask task){
        requestQueue.remove(task);
    }

    /**
     * 返回队头元素并移除
     */
    public HttpTask pollTask(){
        return requestQueue.poll();
    }

    /**
     * 返回队头并不移除
     * @return
     */
    private HttpTask peekTask(){
        return requestQueue.peek();
    }

    /**
     * 取得Task的参数
     * @return
     */
    public String getParams(){
        return null;
    }

    public HttpTask next(){
        return peekTask();
    }
}
