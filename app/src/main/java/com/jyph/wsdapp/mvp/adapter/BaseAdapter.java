package com.jyph.wsdapp.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by ying on 2016/9/6.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder,T> extends RecyclerView.Adapter<VH> {
    private List<T> mData;
    private Context context;

    public BaseAdapter(List<T> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    public T getItem(int position){
        return mData.get(position);
    }
    public void addData(List<T> list){
        mData.addAll(list);
        notifyDataSetChanged();
    }
    public void setData(List<T> list){
        mData.clear();
        addData(list);
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Context getContext() {
        return context;
    }
}
