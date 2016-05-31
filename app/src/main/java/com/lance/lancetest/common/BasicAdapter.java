package com.lance.lancetest.common;

import android.util.SparseArray;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * Created by lance on 16/5/20.
 */
public abstract class BasicAdapter extends BaseAdapter{

    protected static class ViewHolder{

        private static SparseArray<View> views;

        public static <T extends View> T getView(int resId, View view){

            views = (SparseArray<View>)view.getTag();

            if (views == null){
                views = new SparseArray<>();
                views.put(resId,view.findViewById(resId));
                view.setTag(views);
            }else{
                views= (SparseArray<View>)view.getTag();
            }
            return (T)views.get(resId);
        }

    }
}
