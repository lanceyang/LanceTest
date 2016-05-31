package com.lance.lancetest.demon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by lance on 16/5/31.
 */
public class DemonHotVideo implements Serializable{


    public DemonHotVideo() {
    }


    public DemonHotVideo(JSONObject jsonObject) {
        try {
            setThumb(jsonObject.getString("thumb"));
            setIntro(jsonObject.getString("intro"));
            setTitle(jsonObject.getString("title"));
            setUrl(jsonObject.getString("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String thumb;

    private String title;

    private String intro;

    private String url;

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public String getUrl() {
        return url;
    }
}
