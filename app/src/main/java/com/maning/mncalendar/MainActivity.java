package com.maning.mncalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.maning.calendarlibrary.MNCalendar;
import com.maning.calendarlibrary.listeners.OnCalendarChangeListener;
import com.maning.calendarlibrary.listeners.OnCalendarItemClickListener;
import com.maning.calendarlibrary.model.Lunar;
import com.maning.calendarlibrary.model.MNCalendarConfig;
import com.maning.calendarlibrary.model.MNCalendarEventModel;
import com.maning.calendarlibrary.utils.LunarCalendarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat sdf_yyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    private MNCalendar mnCalendar;

    //事件集合
    private ArrayList<MNCalendarEventModel> mEventDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        mnCalendar = (MNCalendar) findViewById(R.id.mnCalendar);

        /**
         * Item点击监听
         */
        mnCalendar.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {

            @Override
            public void onClick(Date date) {
                //Toast日期----阴历可以自己转:LunarCalendarUtils.solarToLunar(date);
                ToastUtil.showToast(context, "单击:" + sdf_yyy_MM_dd.format(date));

            }

            @Override
            public void onLongClick(Date date) {
                //Toast日期----阴历可以自己转:LunarCalendarUtils.solarToLunar(date);
                ToastUtil.showToast(context, "长按:" + sdf_yyy_MM_dd.format(date));
            }
        });

        /**
         * 日历改变监听
         */
        mnCalendar.setOnCalendarChangeListener(new OnCalendarChangeListener() {
            @Override
            public void onPageChange(Date date) {
                ToastUtil.showToast(context, sdf.format(date));
            }
        });

        try {
            MNCalendarEventModel mnCalendarEventModel = new MNCalendarEventModel();
            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2017-11-22"));
            mnCalendarEventModel.setEventInfo("班");
            mnCalendarEventModel.setEventBgColor("#FF00FF");
            mnCalendarEventModel.setEventTextColor("#FFFFFF");
            mEventDatas.add(mnCalendarEventModel);

            mnCalendarEventModel = new MNCalendarEventModel();
            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2017-11-08"));
            mnCalendarEventModel.setEventInfo("议");
            mnCalendarEventModel.setEventBgColor("#FF0000");
            mnCalendarEventModel.setEventTextColor("#FFFFFF");
            mEventDatas.add(mnCalendarEventModel);

            mnCalendarEventModel = new MNCalendarEventModel();
            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2017-10-29"));
            mnCalendarEventModel.setEventInfo("假");
            mnCalendarEventModel.setEventBgColor("#0000FF");
            mnCalendarEventModel.setEventTextColor("#FFFFFF");
            mEventDatas.add(mnCalendarEventModel);

            mnCalendarEventModel = new MNCalendarEventModel();
            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2018-01-25"));
            mnCalendarEventModel.setEventInfo("差");
            mnCalendarEventModel.setEventBgColor("#000000");
            mnCalendarEventModel.setEventTextColor("#FFFFFF");
            mEventDatas.add(mnCalendarEventModel);
            //设置事件
            mnCalendar.setEventDatas(mEventDatas);
        } catch (Exception e) {

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_10:
                //上个月
                mnCalendar.setLastMonth();
                break;
            case R.id.action_11:
                //下个月
                mnCalendar.setNextMonth();
                break;
            case R.id.action_01:
                //跳转到当前月份
                String newDateString = "2017-10";
                Date date = null;
                try {
                    date = sdf.parse(newDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mnCalendar.setCurrentDate(date);
                break;
            case R.id.action_02:
                //跳转到今天
                mnCalendar.set2Today();
                break;
            case R.id.action_03:
                //改变样式配置
                MNCalendarConfig build = new MNCalendarConfig.Builder()
                        //星期的文字颜色
                        .setMnCalendar_colorWeek("#00ff00")
                        //阴历的颜色
                        .setMnCalendar_colorLunar("#FF0000")
                        //阳历的颜色
                        .setMnCalendar_colorSolar("#9BCCAF")
                        //今天的背景色
                        .setMnCalendar_colorTodayBg("#00FFFF")
                        //今天的文字颜色
                        .setMnCalendar_colorTodayText("#000000")
                        //不是本月的文字颜色
                        .setMnCalendar_colorOtherMonth("#F1EDBD")
                        //标题的颜色
                        .setMnCalendar_colorTitle("#FF0000")
                        //选中日期的背景色
                        .setMnCalendar_colorSelected("#FFFF00")
                        //是否显示阴历
                        .setMnCalendar_showLunar(true)
                        //是否显示标题
                        .setMnCalendar_showWeek(true)
                        //显示标题的样式
                        .setMnCalendar_TitleDateFormat("yyyy年MM月")
                        .build();
                mnCalendar.setConfig(build);
                break;
            case R.id.action_04:
                //默认配置
                MNCalendarConfig buildDefault = new MNCalendarConfig.Builder().build();
                mnCalendar.setConfig(buildDefault);
                break;
            case R.id.action_05:
                MNCalendarConfig build05 = new MNCalendarConfig.Builder()
                        .setMnCalendar_showTitle(false)
                        .build();
                mnCalendar.setConfig(build05);
                break;
            case R.id.action_06:
                MNCalendarConfig build06 = new MNCalendarConfig.Builder()
                        .setMnCalendar_showWeek(false)
                        .build();
                mnCalendar.setConfig(build06);
                break;
            case R.id.action_07:
                MNCalendarConfig build07 = new MNCalendarConfig.Builder()
                        .setMnCalendar_showLunar(false)
                        .build();
                mnCalendar.setConfig(build07);
                break;
            case R.id.action_12:
                MNCalendarConfig build12 = new MNCalendarConfig.Builder()
                        .setMnCalendar_TitleDateFormat("MM月yyyy年")
                        .build();
                mnCalendar.setConfig(build12);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
