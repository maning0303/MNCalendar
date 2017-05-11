package com.maning.calendarlibrary.listeners;

import java.util.Date;

/**
 * Created by maning on 2017/5/10.
 * 垂直方向的范围选取监听
 */

public interface OnCalendarRangeChooseListener {

    void onRangeDate(Date startDate, Date endDate);

}
