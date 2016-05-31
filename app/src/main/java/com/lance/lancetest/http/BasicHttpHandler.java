package com.lance.lancetest.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONObject;

/**
 * Created by lance on 16/5/19.
 */
public abstract class BasicHttpHandler extends Handler{

    public final static int MSG_WHAT_START = 0x100010;

    public final static int MSG_WHAT_SUCCESS = 0x100011;

    public final static int MSG_WHAT_FAILED = 0x100012;

    /**
     * Default constructor associates this handler with the {@link Looper} for the
     * current thread.
     * <p/>
     * If this thread does not have a looper, this handler won't be able to receive messages
     * so an exception is thrown.
     */
    public BasicHttpHandler(Looper looper) {
        super(Looper.getMainLooper());
    }

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what){
            case MSG_WHAT_START:
                onStart();
                break;
            case MSG_WHAT_SUCCESS:
                onSuccess((JSONObject)msg.obj);
                break;
            case MSG_WHAT_FAILED:
                HttpError error = (HttpError)msg.obj;
                onFailed(error.getErrorCode(),error.getErrorMsg());
                break;
        }
    }

    protected abstract void onStart();

    protected abstract void onSuccess(JSONObject jsonObject);

    protected abstract void onFailed(int errorCode,String msg);

    public void sendOnStart(){
        sendEmptyMessage(MSG_WHAT_START);
    }

    public void sendOnSuccess(JSONObject jsonObject){
        Message msg = obtainMessage(MSG_WHAT_SUCCESS);
        msg.obj=jsonObject;
        sendMessage(msg);
    }

    public void sendOnFailed(int errorCode,String errorMsg){
        Message msg = obtainMessage(MSG_WHAT_FAILED);
        msg.obj = new HttpError(errorCode,errorMsg);
        sendMessage(msg);
    }

}
