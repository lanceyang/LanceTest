package com.lance.lancetest.http;

import android.util.Log;
import android.widget.Toast;

import com.lance.lancetest.R;
import com.lance.lancetest.common.BasicApplication;
import com.lance.lancetest.listener.HttpRequestCallback;
import com.lance.lancetest.utils.StreamTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lance on 16/5/19.
 */
class HttpUtils {

    public final static String TAG="HttpUtils";
    public static final String SERVLET_POST = "POST" ;
    public static final String SERVLET_GET = "GET" ;
    public static final String SERVLET_PUT = "PUT" ;

    private static HttpURLConnection connection;

    private static int state = BasicHttpConstant.ON_READY;

    public static void doPost( HttpTask task, HttpRequestCallback callback){

        state = BasicHttpConstant.ON_RUNING;

        try {
            URL url = new URL(task.getUrl());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            connection = conn;
            conn.setRequestMethod(SERVLET_POST);
            conn.setConnectTimeout(BasicHttpConstant.CONNECT_TIME_OUT);
            conn.setReadTimeout(BasicHttpConstant.READ_TIME_OUT);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            conn.connect();

            if (200 == conn.getResponseCode()){
                OutputStream os = conn.getOutputStream();
                os.write(task.getJsonStrParam().getBytes("utf-8"));
                os.flush();
                os.close();
                dealResponse(conn.getInputStream(),task);
            }else{
                task.getCallBack().sendOnFailed(HttpError.CONNECTION_ERROR, BasicApplication
                        .getBasicApp().getResources().getString(R.string.connect_error_hint));
            }

        }catch (Exception e){
            Log.e("HttpUtils_doPost",e.getMessage());
            task.getCallBack().sendOnFailed(HttpError.CONNECTION_ERROR, e.getMessage());
        }finally {
            connection.disconnect();
            state = BasicHttpConstant.ON_IDLE;
            callback.finish();

        }
    }

    public static void  doGet(HttpTask task,HttpRequestCallback callback){

        state = BasicHttpConstant.ON_RUNING;

        try {
            URL url = new URL(task.getUrl());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            connection = conn;

            conn.setRequestMethod(SERVLET_GET);
            conn.setConnectTimeout(BasicHttpConstant.CONNECT_TIME_OUT);
            conn.setReadTimeout(BasicHttpConstant.READ_TIME_OUT);
            conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
            conn.connect();
            if (200 == conn.getResponseCode()) {
                dealResponse(conn.getInputStream(),task);
            }
            else{
                task.getCallBack().sendOnFailed(HttpError.CONNECTION_ERROR, BasicApplication
                        .getBasicApp().getResources().getString(R.string.connect_error_hint));
            }
        }catch (Exception e){
            Log.e("HttpUtils_doGet",e.toString());
            task.getCallBack().sendOnFailed(HttpError.CONNECTION_ERROR, e.getMessage());
        }finally {
            state = BasicHttpConstant.ON_IDLE;
            callback.finish();
        }
    }

    public static void doPut(HttpTask task,HttpRequestCallback callback){

        state = BasicHttpConstant.ON_RUNING;

        try {
            URL url = new URL(task.getUrl());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            connection = conn;
            conn.setRequestMethod(SERVLET_PUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            OutputStream os = conn.getOutputStream();
            os.write(task.getJsonStrParam().getBytes("utf-8"));
            os.close();
            if (200 == conn.getResponseCode()) {
                dealResponse(conn.getInputStream(),task);
            }
            else{
                task.getCallBack().sendOnFailed(HttpError.CONNECTION_ERROR, BasicApplication
                        .getBasicApp().getResources().getString(R.string.connect_error_hint));
            }
        }catch (Exception e){
            Log.e("HttpUtils_doPut",e.getMessage());
            task.getCallBack().sendOnFailed(HttpError.CONNECTION_ERROR, e.getMessage());
        }finally {
            connection.disconnect();
            state = BasicHttpConstant.ON_IDLE;
            callback.finish();
        }

    }

    private static void dealResponse(InputStream in,HttpTask task){
        JSONObject object=null;
        try {
            object = parseJSON(in);
        }catch (Exception e){
//            task.getCallBack().sendOnFailed(HttpError.JSON_PRASE_ERROR,e.getMessage());
            Log.e("HttpUtils_dealResponse",e.toString());
        }

        task.getCallBack().sendOnSuccess(object);
    }

    private static JSONObject parseJSON(InputStream jsonStream){
        byte[] data = new byte[0];
        data = StreamTool.read(jsonStream);
        String json = new String(data);
        JSONObject jsonObject = null;
        try {

            if (json==null){
                throw new JSONException("this is a null");
            }else if (json.substring(0,1)=="{"){
                jsonObject = new JSONObject(json);
            }else{
                JSONArray array = new JSONArray(json);
                jsonObject = new JSONObject();
                jsonObject.put("array",array);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void disConnect(){
        connection.disconnect();
    }

    public static int getState() {
        return state;
    }
}
