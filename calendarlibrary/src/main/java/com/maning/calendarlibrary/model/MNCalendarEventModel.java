package com.maning.calendarlibrary.model;

import java.util.Date;

/**
 * Created by maning on 2017/11/22.
 * 当天事件的Model
 */

public class MNCalendarEventModel {

    //事件事件
    private Date eventDate;
    //事件背景颜色
    private String eventBgColor;
    //事件文字的颜色
    private String eventTextColor;
    //事件的文字
    private String eventInfo;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventBgColor() {
        return eventBgColor;
    }

    public void setEventBgColor(String eventBgColor) {
        this.eventBgColor = eventBgColor;
    }

    public String getEventTextColor() {
        return eventTextColor;
    }

    public void setEventTextColor(String eventTextColor) {
        this.eventTextColor = eventTextColor;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    @Override
    public String toString() {
        return "MNCalendarEventModel{" +
                "eventDate=" + eventDate +
                ", eventBgColor='" + eventBgColor + '\'' +
                ", eventTextColor='" + eventTextColor + '\'' +
                ", eventInfo='" + eventInfo + '\'' +
                '}';
    }
}
