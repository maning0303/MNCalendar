package com.maning.calendarlibrary.model;

import java.util.Date;

/**
 * Created by maning on 2017/7/19.
 * Item 显示日期的Bean : 包含阳历和阴历
 */

public class MNCalendarItemModel {

    public MNCalendarItemModel() {
    }

    public MNCalendarItemModel(Date date, Lunar lunar) {
        mDate = date;
        mLunar = lunar;
    }

    private Date mDate;
    private Lunar mLunar;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Lunar getLunar() {
        return mLunar;
    }

    public void setLunar(Lunar lunar) {
        mLunar = lunar;
    }

}
