package com.wty.app.smartconfig.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：通用 adapter
 * @author wty
 */
public abstract class BaseViewCommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected int mLayoutResId;//布局文件id
    protected List<T> mData;

    public BaseViewCommonAdapter(Context context, List<T> data) {
        this(context,0, data);
    }

    public BaseViewCommonAdapter(Context context, int layoutResId, List<T> data) {
        this.mContext = context;
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BaseViewHolder viewHolder = onCreateViewHolder(convertView,
                parent,position);
        convert(viewHolder, getItem(position),position);
        return viewHolder.getConvertView();
    }

    public BaseViewHolder onCreateViewHolder(View convertView, ViewGroup parent, int position) {
        return createBaseViewHolder(convertView, parent, mLayoutResId);
    }

    protected BaseViewHolder createBaseViewHolder(View convertView, ViewGroup parent, int layoutResId) {
        return BaseViewHolder.get(mContext,convertView,parent,layoutResId);
    }

    public void remove(int position) {
        mData.remove(position);
        notifyDataSetChanged();

    }

    public void addOne(int position, T item) {
        mData.add(position, item);
        notifyDataSetChanged();
    }

    public void add(T item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addList(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void refreshList(List<T> data){
        this.mData.clear();
        addList(data);
    }

    public void clear(){
        this.mData.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 功能描述：绑定数据
     **/
    protected abstract void convert(BaseViewHolder holder, T item,int position);
}
