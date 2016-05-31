package com.lance.lancetest.http;

/**
 * Created by lance on 16/5/18.
 */
public class HttpTask{

    private RequestParam param;

    private BasicHttpHandler handler;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BasicHttpHandler getCallBack() {
        return handler;
    }

    public String getJsonStrParam() {
        return null;
    }

    public void setParam(RequestParam param) {
        this.param = param;
    }

    public void setHandler(BasicHttpHandler handler) {
        this.handler = handler;
    }

    public int getRequestType(){
        return param.getRequestType();
    }
}
