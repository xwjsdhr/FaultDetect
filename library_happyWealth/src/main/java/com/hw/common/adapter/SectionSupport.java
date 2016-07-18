package com.hw.common.adapter;

/**
 * Created by nicai on 16/4/15.
 */
public interface SectionSupport<T>
{
    public int sectionHeaderLayoutId();

    public int sectionTitleTextViewId();

    public String getTitle(T t);
}
