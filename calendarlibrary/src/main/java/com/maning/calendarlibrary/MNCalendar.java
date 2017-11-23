package com.maning.calendarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maning.calendarlibrary.constant.MNConst;
import com.maning.calendarlibrary.listeners.OnCalendarChangeListener;
import com.maning.calendarlibrary.listeners.OnCalendarItemClickListener;
import com.maning.calendarlibrary.listeners.OnCalendarSelectedChangeListener;
import com.maning.calendarlibrary.model.Lunar;
import com.maning.calendarlibrary.model.MNCalendarConfig;
import com.maning.calendarlibrary.model.MNCalendarEventModel;
import com.maning.calendarlibrary.view.MNCalendarMonthPagerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public class MNCalendar extends LinearLayout implements View.OnClickListener {


    private static final String TAG = "MNCalendar";
    private Handler handler = new Handler();

    private Context context;

    private ViewPager viewPagerCalendar;

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

    /**
     * 保存选中的日期
     */
    private Calendar mSelectedCalendar;
    //最小年
    private int minYear = 1900;
    //最大支持的年份
    private int maxYear = 2100;

    private OnCalendarItemClickListener onCalendarItemClickListener;

    private OnCalendarChangeListener onCalendarChangeListener;

    //配置信息
    private MNCalendarConfig mnCalendarConfig = new MNCalendarConfig.Builder().build();

    //当前的日期
    private Calendar currentCalendar = Calendar.getInstance();

    //事件集合
    private ArrayList<MNCalendarEventModel> mEventDatas = new ArrayList<>();

    public MNCalendar(Context context) {
        this(context, null);
    }

    public MNCalendar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MNCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        //自定义属性获取
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MNCalendar);
        maxYear = ta.getInteger(R.styleable.MNCalendar_mnCalendar_maxYear, 2100);
        minYear = ta.getInteger(R.styleable.MNCalendar_mnCalendar_minYear, 1900);
        ta.recycle();

        Log.e(TAG, "maxYear : " + maxYear + " , minYear : " + minYear);

        //初始化相关
        init();
    }

    private void init() {

        //初始化
        initViews();

        //绘制View
        drawTitleViews();

        //初始化ViewPager
        initViewPagerAdapter();

        //绘制日期
        setCurrentPager();

        try {
            setEventDatas(this.mEventDatas);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setEventDatas(ArrayList<MNCalendarEventModel> mEventDatas) throws ParseException {
        this.mEventDatas = mEventDatas;
        if (this.mEventDatas == null) {
            this.mEventDatas = new ArrayList<>();
        }

        //刷新ViewPager页面
        for (int i = 0; i < viewPagerCalendar.getChildCount(); i++) {
            MNCalendarMonthPagerView view = (MNCalendarMonthPagerView) viewPagerCalendar.getChildAt(i);
            if (view != null) {
                view.updateEventDatas(mEventDatas);
            }
        }
    }

    private void initViews() {
        //绑定View
        View.inflate(context, R.layout.mn_layout_calendar, this);
        viewPagerCalendar = (ViewPager) findViewById(R.id.viewPagerCalendar);
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

        //点击事件
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

    }

    private void drawTitleViews() {
        //标题
        if (!mnCalendarConfig.isMnCalendar_showTitle()) {
            rl_title_view.setVisibility(View.GONE);
        } else {
            rl_title_view.setVisibility(View.VISIBLE);
            //标题默认样式
            try {
                String mnCalendar_titleDateFormat = mnCalendarConfig.getMnCalendar_titleDateFormat();
                SimpleDateFormat sdf_yyyy_MM = new SimpleDateFormat(mnCalendar_titleDateFormat);
                tv_calendar_title.setText(sdf_yyyy_MM.format(getCurrentDate()));
            } catch (Exception e) {
                tv_calendar_title.setText(MNConst.sdf_yyyy_MM.format(getCurrentDate()));
            }
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
    }

    private void setCurrentPager() {
        Calendar instance = (Calendar) currentCalendar.clone();
        int yearSize = instance.get(Calendar.YEAR) - minYear;
        int monthSize = instance.get(Calendar.MONTH) + 1;
        int position = 12 * yearSize + monthSize - 1;
        viewPagerCalendar.setCurrentItem(position);
    }

    private void initViewPagerAdapter() {
        viewPagerCalendar.setAdapter(new MNViewPagerAdapter());
        viewPagerCalendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int year = position / 12 + minYear;
                int month = position % 12 + 1;
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                Date date = null;
                try {
                    date = formatter.parse(year + "-" + month);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.setTime(date);
                currentCalendar = calendar;
                drawTitleViews();
                updatePageChangeListener();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private class MNViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return (maxYear - minYear) * 12;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int year = position / 12 + minYear;
            int month = position % 12 + 1;
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            Date date = null;
            try {
                date = formatter.parse(year + "-" + month);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
            //绘制月份显示
            MNCalendarMonthPagerView calendarMonthPagerView = new MNCalendarMonthPagerView(context);
            calendarMonthPagerView.setCurrentCalendar(calendar, mnCalendarConfig, mEventDatas);
            calendarMonthPagerView.setTag(position);
            calendarMonthPagerView.setSelectedCalendar(mSelectedCalendar);
            calendarMonthPagerView.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {
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
            calendarMonthPagerView.setOnCalendarSelectedChangeListener(new OnCalendarSelectedChangeListener() {
                @Override
                public void onSelectedChange(Calendar selectedCalendar) {
                    if (selectedCalendar == null) {
                        return;
                    }
                    //改变选中的
                    mSelectedCalendar = selectedCalendar;

                    //刷新ViewPager页面
                    for (int i = 0; i < viewPagerCalendar.getChildCount(); i++) {
                        MNCalendarMonthPagerView view = (MNCalendarMonthPagerView) viewPagerCalendar.getChildAt(i);
                        if (view != null) {
                            view.setSelectedCalendar(mSelectedCalendar);
                        }
                    }

                    //判断是不是本月
                    Date date = mSelectedCalendar.getTime();
                    Date currentDate = currentCalendar.getTime();
                    if (date.getMonth() != currentDate.getMonth()) {
                        if (date.getMonth() < currentDate.getMonth() && date.getYear() <= currentDate.getYear()) {
                            MNCalendarMonthPagerView view = (MNCalendarMonthPagerView) viewPagerCalendar.findViewWithTag(viewPagerCalendar.getCurrentItem() - 1);
                            if (view != null) {
                                view.updateSelectedCalendar(mSelectedCalendar);
                            }
                            //上个月
                            setLastMonth();
                        } else {
                            MNCalendarMonthPagerView view = (MNCalendarMonthPagerView) viewPagerCalendar.findViewWithTag(viewPagerCalendar.getCurrentItem() + 1);
                            if (view != null) {
                                view.updateSelectedCalendar(mSelectedCalendar);
                            }
                            //跳转下一个月
                            setNextMonth();
                        }
                    }

                    //延时刷新
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //刷新ViewPager页面
                            for (int i = 0; i < viewPagerCalendar.getChildCount(); i++) {
                                MNCalendarMonthPagerView view = (MNCalendarMonthPagerView) viewPagerCalendar.getChildAt(i);
                                if (view != null) {
                                    view.updateSelectedCalendar(mSelectedCalendar);
                                }
                            }
                        }
                    }, 200);


                }
            });
            container.addView(calendarMonthPagerView);
            return calendarMonthPagerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    /**
     * 设置点击事件
     *
     * @param onCalendarItemClickListener
     */
    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
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
     * 回到今天
     */
    public void set2Today() {
        currentCalendar = Calendar.getInstance();
        setCurrentPager();
        updatePageChangeListener();
    }

    /**
     * 跳转到设置的月份
     *
     * @param date
     */
    public void setCurrentDate(Date date) {
        if (date != null) {
            currentCalendar.setTime(date);
            setCurrentPager();
            updatePageChangeListener();
        }
    }

    /**
     * 切换到下个月
     */
    public void setNextMonth() {
        viewPagerCalendar.setCurrentItem(viewPagerCalendar.getCurrentItem() + 1);
    }

    /**
     * 切换到上个月
     */
    public void setLastMonth() {
        viewPagerCalendar.setCurrentItem(viewPagerCalendar.getCurrentItem() - 1);
    }

    private void updatePageChangeListener() {
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.onPageChange(getCurrentDate());
        }
    }

    /**
     * 配置参数
     *
     * @param config
     */
    public void setConfig(MNCalendarConfig config) {
        this.mnCalendarConfig = config;
        drawTitleViews();
        //刷新ViewPager页面
        for (int i = 0; i < viewPagerCalendar.getChildCount(); i++) {
            MNCalendarMonthPagerView view = (MNCalendarMonthPagerView) viewPagerCalendar.getChildAt(i);
            if (view != null) {
                view.updateConfig(mnCalendarConfig);
            }
        }
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
