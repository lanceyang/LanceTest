package com.lance.lancetest.demon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lance.lancetest.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 16/5/31.
 */
public class DemonRecyclerAdapter extends RecyclerView.Adapter<DemonRecyclerAdapter.ViewHolder>{


    private List<DemonHotVideo> dataList = new ArrayList<>();

    public DemonRecyclerAdapter() {
    }

    public void setDataList(List<DemonHotVideo> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (parent!=null&&parent.getTag()!=null){
            return (ViewHolder) parent.getTag();
        }

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_recycler_demon,parent,false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv.setText(dataList.get(position).getTitle());
        ImageLoader.getInstance().displayImage(dataList.get(position).getThumb(),holder.im);

    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        ImageView im;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.test_tv);
            im = (ImageView)itemView.findViewById(R.id.test_im);
            itemView.setTag(this);
        }
    }
}
