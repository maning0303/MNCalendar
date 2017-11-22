package com.maning.calendarlibrary.listeners;

import java.util.Calendar;

/**
 * Created by maning on 2017/5/10.
 */

public interface OnCalendarSelectedChangeListener {

    /**
     * 选中的时间的改变
     *
     * @param selectedCalendar
     */
    void onSelectedChange(Calendar selectedCalendar);

}
