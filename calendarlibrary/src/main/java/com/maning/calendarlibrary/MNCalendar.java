package com.maning.calendarlibrary;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maning.calendarlibrary.adapter.MNCalendarAdapter;
import com.maning.calendarlibrary.listeners.OnCalendarChangeListener;
import com.maning.calendarlibrary.listeners.OnCalendarItemClickListener;
import com.maning.calendarlibrary.model.MNCalendarConfig;
import com.maning.calendarlibrary.view.MNGestureView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public class MNCalendar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "MNCalendar";

    private static SimpleDateFormat sdf_yyyy_MM = new SimpleDateFormat("yyyy-MM");

    private Context context;

    private RecyclerView recyclerViewCalendar;

    private MNGestureView mnGestureView;

    //日期
    private LinearLayout ll_week;
    private TextView tv_week_01;
    private TextView tv_week_02;
    private TextView tv_week_03;
    private TextView tv_week_04;
    private TextView tv_week_05;
    private TextView tv_week_06;
    private TextView tv_week_07;

    //标题
    private RelativeLayout rl_title_view;
    private ImageView btn_left;
    private ImageView btn_right;
    private TextView tv_calendar_title;
    //root
    private LinearLayout ll_root;


    private OnCalendarItemClickListener onCalendarItemClickListener;

    private OnCalendarChangeListener onCalendarChangeListener;

    //配置信息
    private MNCalendarConfig mnCalendarConfig = new MNCalendarConfig.Builder().build();

    //当前的日期
    private Calendar currentCalendar = Calendar.getInstance();
    private MNCalendarAdapter mnCalendarAdapter;

    public MNCalendar(Context context) {
        this(context, null);
    }

    public MNCalendar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MNCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        //初始化相关
        init();
    }

    private void init() {

        //初始化
        initViews();

        //绘制日历
        drawCalendar();

    }

    private void initViews() {
        //绑定View
        View.inflate(context, R.layout.mn_layout_calendar, this);
        recyclerViewCalendar = (RecyclerView) findViewById(R.id.recyclerViewCalendar);
        mnGestureView = (MNGestureView) findViewById(R.id.mnGestureView);
        ll_week = (LinearLayout) findViewById(R.id.ll_week);
        tv_week_01 = (TextView) findViewById(R.id.tv_week_01);
        tv_week_02 = (TextView) findViewById(R.id.tv_week_02);
        tv_week_03 = (TextView) findViewById(R.id.tv_week_03);
        tv_week_04 = (TextView) findViewById(R.id.tv_week_04);
        tv_week_05 = (TextView) findViewById(R.id.tv_week_05);
        tv_week_06 = (TextView) findViewById(R.id.tv_week_06);
        tv_week_07 = (TextView) findViewById(R.id.tv_week_07);
        rl_title_view = (RelativeLayout) findViewById(R.id.rl_title_view);
        btn_left = (ImageView) findViewById(R.id.btn_left);
        btn_right = (ImageView) findViewById(R.id.btn_right);
        tv_calendar_title = (TextView) findViewById(R.id.tv_calendar_title);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);

        //初始化RecycleerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        recyclerViewCalendar.setLayoutManager(gridLayoutManager);

        //手势监听
        mnGestureView.setOnSwipeListener(new MNGestureView.OnSwipeListener() {
            @Override
            public void rightSwipe() {
                setLastMonth();
            }

            @Override
            public void leftSwipe() {
                setNextMonth();
            }
        });

        //点击事件
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

    }

    private void drawCalendar() {

        //标题
        if (!mnCalendarConfig.isMnCalendar_showTitle()) {
            rl_title_view.setVisibility(View.GONE);
        } else {
            rl_title_view.setVisibility(View.VISIBLE);
            //标题
            tv_calendar_title.setText(sdf_yyyy_MM.format(getCurrentDate()));

            //标题颜色值
            int mnCalendar_colorTitle = mnCalendarConfig.getMnCalendar_colorTitle();
            tv_calendar_title.setTextColor(mnCalendar_colorTitle);
            btn_left.setColorFilter(mnCalendar_colorTitle);
            btn_right.setColorFilter(mnCalendar_colorTitle);
        }


        //判断是不是隐藏Week
        if (!mnCalendarConfig.isMnCalendar_showWeek()) {
            ll_week.setVisibility(View.GONE);
        } else {
            ll_week.setVisibility(View.VISIBLE);
            //week的颜色值
            int mnCalendar_colorWeek = mnCalendarConfig.getMnCalendar_colorWeek();
            tv_week_01.setTextColor(mnCalendar_colorWeek);
            tv_week_02.setTextColor(mnCalendar_colorWeek);
            tv_week_03.setTextColor(mnCalendar_colorWeek);
            tv_week_04.setTextColor(mnCalendar_colorWeek);
            tv_week_05.setTextColor(mnCalendar_colorWeek);
            tv_week_06.setTextColor(mnCalendar_colorWeek);
            tv_week_07.setTextColor(mnCalendar_colorWeek);
        }


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
        mnCalendarAdapter = new MNCalendarAdapter(context, mDatas, currentCalendar, mnCalendarConfig);
        recyclerViewCalendar.setAdapter(mnCalendarAdapter);

        //设置Item点击事件
        mnCalendarAdapter.setOnCalendarItemClickListener(onCalendarItemClickListener);

    }

    /**
     * 设置点击事件
     *
     * @param onCalendarItemClickListener
     */
    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
        //设置Item点击事件
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.setOnCalendarItemClickListener(onCalendarItemClickListener);
        }
    }

    /**
     * 设置改变的监听
     *
     * @param onCalendarChangeListener
     */
    public void setOnCalendarChangeListener(OnCalendarChangeListener onCalendarChangeListener) {
        this.onCalendarChangeListener = onCalendarChangeListener;
    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    public Date getCurrentDate() {
        return currentCalendar.getTime();
    }

    /**
     * 跳转到设置的月份
     *
     * @param date
     */
    public void setCurrentDate(Date date) {
        if (date != null) {
            currentCalendar.setTime(date);
            drawCalendar();
        }
    }

    /**
     * 切换到下个月
     */
    public void setNextMonth() {
        currentCalendar.add(Calendar.MONTH, 1);
        drawCalendar();
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.nextMonth();
        }
    }

    /**
     * 切换到上个月
     */
    public void setLastMonth() {
        currentCalendar.add(Calendar.MONTH, -1);
        drawCalendar();
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.lastMonth();
        }
    }

    /**
     * 回到今天
     */
    public void set2Today() {
        currentCalendar = Calendar.getInstance();
        drawCalendar();
    }

    /**
     * 配置参数
     *
     * @param config
     */
    public void setConfig(MNCalendarConfig config) {
        this.mnCalendarConfig = config;
        drawCalendar();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_left) {
            setLastMonth();
        } else if (view.getId() == R.id.btn_right) {
            setNextMonth();
        }
    }
}
