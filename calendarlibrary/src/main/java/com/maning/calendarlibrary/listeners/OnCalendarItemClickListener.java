package com.maning.calendarlibrary.listeners;

import com.maning.calendarlibrary.model.Lunar;

import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public interface OnCalendarItemClickListener {

    void onClick(Date date, Lunar lunar);

    void onLongClick(Date date);

}
