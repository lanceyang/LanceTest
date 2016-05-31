package com.lance.lancetest.http;

import java.util.HashMap;

/**
 * Created by lance on 16/5/19.
 */
public class RequestParam extends HashMap {


    public static final int REQUEST_TYPE_POST=0;

    public static final int REQUEST_TYPE_GET=1;

    public static final int REQUEST_TYPE_PUT=2;

    private int RequestType = REQUEST_TYPE_POST;

    public void setRequestType(int requestType) {
        RequestType = requestType;
    }

    public int getRequestType() {
        return RequestType;
    }

    public void put(String key, Object value){

        super.put(key,value);

    }

}
