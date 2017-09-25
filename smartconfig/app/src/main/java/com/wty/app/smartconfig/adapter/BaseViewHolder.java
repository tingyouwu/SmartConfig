package com.wty.app.smartconfig.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

/**
 * @Decription 通用 viewholder
 * @author wty
 */
public class BaseViewHolder {

    private final SparseArray<View> views;

    public View convertView;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.views = new SparseArray<>();
        this.convertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        this.convertView.setTag(this);
    }

    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId)
    {
        if(convertView == null){
            return new BaseViewHolder(context,parent,layoutId);
        }
        return (BaseViewHolder)convertView.getTag();
    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    @SuppressWarnings("unchecked")
    public void removeView(int viewId) {
        View view = views.get(viewId);
        if (view != null) {
            views.remove(viewId);
        }
    }
}
