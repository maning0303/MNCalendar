package com.maning.calendarlibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.maning.calendarlibrary.R;
import com.maning.calendarlibrary.adapter.MNCalendarAdapter;
import com.maning.calendarlibrary.listeners.OnCalendarItemClickListener;
import com.maning.calendarlibrary.listeners.OnCalendarSelectedChangeListener;
import com.maning.calendarlibrary.model.Lunar;
import com.maning.calendarlibrary.model.MNCalendarConfig;
import com.maning.calendarlibrary.model.MNCalendarEventModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maning on 2017/11/20.
 */

public class MNCalendarMonthPagerView extends FrameLayout {

    private Context mContext;
    private RecyclerView recyclerViewCalendarMonth;
    private Calendar currentCalendar;
    //配置信息
    private MNCalendarConfig mnCalendarConfig = new MNCalendarConfig.Builder().build();
    //Item点击监听
    private OnCalendarItemClickListener onCalendarItemClickListener;
    private OnCalendarSelectedChangeListener onCalendarSelectedChangeListener;

    private MNCalendarAdapter mnCalendarAdapter;

    private Calendar mSelectedCalendar;

    //事件集合
    private ArrayList<MNCalendarEventModel> mEventDatas;

    public MNCalendarMonthPagerView(@NonNull Context context) {
        this(context, null);
    }

    public MNCalendarMonthPagerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MNCalendarMonthPagerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.mn_layout_calendar_month_pager, this);
        recyclerViewCalendarMonth = (RecyclerView) findViewById(R.id.recyclerViewCalendarMonth);

        //初始化RecycleerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 7);
        recyclerViewCalendarMonth.setLayoutManager(gridLayoutManager);
    }

    public void setSelectedCalendar(Calendar mSelectedCalendar) {
        this.mSelectedCalendar = mSelectedCalendar;
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.setSelectedCalendar(this.mSelectedCalendar);
        }
    }

    public void updateSelectedCalendar(Calendar mSelectedCalendar) {
        this.mSelectedCalendar = mSelectedCalendar;
        if (mnCalendarAdapter != null && mSelectedCalendar != null) {
            mnCalendarAdapter.updateSelectedCalendar(this.mSelectedCalendar);
        }
    }

    public void setCurrentCalendar(Calendar calendar, MNCalendarConfig mnCalendarConfig,ArrayList<MNCalendarEventModel> mEventDatas) {
        this.currentCalendar = calendar;
        this.mEventDatas = mEventDatas;
        this.mnCalendarConfig = mnCalendarConfig;
        initAdapter();
    }

    public void updateEventDatas(ArrayList<MNCalendarEventModel> mEventDatas) {
        this.mEventDatas = mEventDatas;
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.updateEventDatas(this.mEventDatas);
        }
    }

    public void updateConfig(MNCalendarConfig mnCalendarConfig) {
        this.mnCalendarConfig = mnCalendarConfig;
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.updateConfig(this.mnCalendarConfig);
        }
    }

    private void initAdapter() {
        //计算日期
        ArrayList<Date> mDatas = new ArrayList<>();
        Calendar calendar = (Calendar) currentCalendar.clone();

        //至于当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //获取当月第一天是星期几
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //移动到需要绘制的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -day_of_week);

        //6*7
        while (mDatas.size() < 6 * 7) {
            mDatas.add(calendar.getTime());
            //向前移动一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //设置Adapter
        mnCalendarAdapter = new MNCalendarAdapter(mContext, mDatas, mEventDatas,currentCalendar, mSelectedCalendar, mnCalendarConfig);
        recyclerViewCalendarMonth.setAdapter(mnCalendarAdapter);
        mnCalendarAdapter.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {
            @Override
            public void onClick(Date date) {
                if (onCalendarItemClickListener != null) {
                    onCalendarItemClickListener.onClick(date);
                }
            }

            @Override
            public void onLongClick(Date date) {
                if (onCalendarItemClickListener != null) {
                    onCalendarItemClickListener.onLongClick(date);
                }
            }
        });
        mnCalendarAdapter.setOnCalendarSelectedChangeListener(new OnCalendarSelectedChangeListener() {
            @Override
            public void onSelectedChange(Calendar selectedCalendar) {
                if (onCalendarSelectedChangeListener != null) {
                    onCalendarSelectedChangeListener.onSelectedChange(selectedCalendar);
                }
            }
        });
    }

    /**
     * 设置点击事件
     *
     * @param onCalendarItemClickListener
     */
    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
    }

    public void setOnCalendarSelectedChangeListener(OnCalendarSelectedChangeListener onCalendarSelectedChangeListener) {
        this.onCalendarSelectedChangeListener = onCalendarSelectedChangeListener;
    }

}
