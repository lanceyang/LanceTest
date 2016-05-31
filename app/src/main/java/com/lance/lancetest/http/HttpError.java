package com.lance.lancetest.http;

/**
 * Created by lance on 16/5/19.
 */
public class HttpError {

    public final static int JSON_PRASE_ERROR = 0x20000;

    public final static int CONNECTION_ERROR = 0x20001;

    private int errorCode;

    private String errorMsg;

    public HttpError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
