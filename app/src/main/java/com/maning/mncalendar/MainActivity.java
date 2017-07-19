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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private MNCalendar mnCalendar;

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
            public void onClick(Date date, Lunar lunar) {
                //阳历转换阴历
//                Lunar solarToLunar = LunarCalendarUtils.solarToLunar(date);

                //Toast日期
                String launarString = lunar.lunarYear + "-" + lunar.lunarMonth + "-" + lunar.lunarDay;
                Toast.makeText(context, "单击:\n阳历:" + sdf2.format(date) + "\n阴历:" + launarString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(Date date) {
                Toast.makeText(context, "长按:" + sdf2.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 日历改变监听
         */
        mnCalendar.setOnCalendarChangeListener(new OnCalendarChangeListener() {
            @Override
            public void lastMonth() {
                Toast.makeText(context, sdf.format(mnCalendar.getCurrentDate()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void nextMonth() {
                Toast.makeText(context, sdf.format(mnCalendar.getCurrentDate()), Toast.LENGTH_SHORT).show();
            }
        });

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
                        .setMnCalendar_colorWeek("#00ff00")
                        .setMnCalendar_colorLunar("#FF0000")
                        .setMnCalendar_colorSolar("#9BCCAF")
                        .setMnCalendar_colorTodayBg("#00FFFF")
                        .setMnCalendar_colorTodayText("#000000")
                        .setMnCalendar_colorOtherMonth("#F1EDBD")
                        .setMnCalendar_colorTitle("#FF0000")
                        .setMnCalendar_showLunar(true)
                        .setMnCalendar_showWeek(true)
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
