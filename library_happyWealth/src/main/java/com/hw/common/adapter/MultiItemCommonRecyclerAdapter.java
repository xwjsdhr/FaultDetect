package com.hw.common.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.hw.common.utils.viewUtils.ViewHolder;

import java.util.List;

public abstract class MultiItemCommonRecyclerAdapter<T> extends CommonRecyclerAdapter<T>
{

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonRecyclerAdapter(Context context, List<T> datas,
                                          MultiItemTypeSupport<T> multiItemTypeSupport)
    {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;

        if (mMultiItemTypeSupport == null)
            throw new IllegalArgumentException("the mMultiItemTypeSupport can not be null.");
    }

    @Override
    public int getItemViewType(int position)
    {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (mMultiItemTypeSupport == null) return super.onCreateViewHolder(parent, viewType);

        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext, null, parent, layoutId, -1);
        setListener(parent, holder, viewType);
        return holder;
    }

}
