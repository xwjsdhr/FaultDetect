package com.hw.common.adapter;

public interface MultiListItemTypeSupport<T>
{
	int getLayoutId(int position, T t);

	int getViewTypeCount();

	int getItemViewType(int position, T t);
}