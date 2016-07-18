package com.hw.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hw.common.utils.viewUtils.CommonAdapter;
import com.hw.common.utils.viewUtils.ViewHolder;

public abstract class MultiItemListCommonAdapter<T> extends CommonAdapter<T>
{

    protected MultiListItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemListCommonAdapter(Context context, MultiListItemTypeSupport<T> multiItemTypeSupport)
    {
        super(context, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
        if (mMultiItemTypeSupport == null)
            throw new IllegalArgumentException("the mMultiItemTypeSupport can not be null.");
    }

    public int getViewTypeCount()
    {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getViewTypeCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position,
                    mDatas.get(position));
        return super.getItemViewType(position);

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (mMultiItemTypeSupport == null)
            return super.getView(position, convertView, parent);

        int layoutId = mMultiItemTypeSupport.getLayoutId(position,
                getItem(position));
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        getView(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

}
