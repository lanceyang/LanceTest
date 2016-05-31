package com.lance.lancetest.demon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lance.lancetest.R;
import com.lance.lancetest.common.BasicAdapter;
import com.lance.lancetest.common.BasicApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 16/5/20.
 */
public class DemonAdapter extends BasicAdapter{

    private List<String> dataList = new ArrayList<>();

    public DemonAdapter(List<String> dataList) {
        this.dataList.addAll(dataList);
    }

    public void setDataList(List<String> dataList){
        this.dataList=dataList;
    };

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {

        return dataList.size();

    }


    public void addAllData(List<String> dataList){
        this.dataList.clear();
        this.dataList.addAll(dataList);

    }
    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public String getItem(int position) {
        return dataList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            convertView =LayoutInflater.from(BasicApplication.getBasicApp()).inflate(R.layout.cell_layout,null,false);
        }

        ((TextView)ViewHolder.getView(R.id.tv,convertView)).setText(dataList.get(position));

        return convertView;
    }


}
