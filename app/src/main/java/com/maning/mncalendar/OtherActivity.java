package com.maning.mncalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.maning.calendarlibrary.MNCalendarVertical;
import com.maning.calendarlibrary.listeners.OnCalendarRangeChooseListener;
import com.maning.calendarlibrary.model.MNCalendarVerticalConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OtherActivity extends AppCompatActivity {

    private Context context;

    private MNCalendarVertical mnCalendarVertical;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        context = this;

        mnCalendarVertical = (MNCalendarVertical) findViewById(R.id.mnCalendarVertical);

        /**
         * 区间选取完成监听
         */
        mnCalendarVertical.setOnCalendarRangeChooseListener(new OnCalendarRangeChooseListener() {
            @Override
            public void onRangeDate(Date startDate, Date endDate) {
                String startTime = sdf.format(startDate);
                String endTime = sdf.format(endDate);
                Toast.makeText(context, "开始日期:" + startTime + ",结束日期:" + endTime, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_other, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_01:

                /**
                 *  自定义设置相关
                 */
                MNCalendarVerticalConfig mnCalendarVerticalConfig = new MNCalendarVerticalConfig.Builder()
                        .setMnCalendar_showWeek(true)                   //是否显示星期栏
                        .setMnCalendar_showLunar(true)                  //是否显示阴历
                        .setMnCalendar_colorWeek("#B07219")             //星期栏的颜色
                        .setMnCalendar_titleFormat("yyyy-MM")           //每个月的标题样式
                        .setMnCalendar_colorTitle("#FF0000")            //每个月标题的颜色
                        .setMnCalendar_colorSolar("#ff0fc7")            //阳历的颜色
                        .setMnCalendar_colorLunar("#00ff00")            //阴历的颜色
                        .setMnCalendar_colorBeforeToday("#F1EDBD")      //今天之前的日期的颜色
                        .setMnCalendar_colorRangeBg("#9930C553")        //区间中间的背景颜色
                        .setMnCalendar_colorRangeText("#000000")        //区间文字的颜色
                        .setMnCalendar_colorStartAndEndBg("#258C3E")    //开始结束的背景颜色
                        .setMnCalendar_countMonth(12)                    //显示多少月(默认6个月)
                        .build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig);
                break;
            case R.id.action_02:
                //隐藏星期
                MNCalendarVerticalConfig mnCalendarVerticalConfig2 = new MNCalendarVerticalConfig.Builder()
                        .setMnCalendar_showWeek(false)
                        .build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig2);
                break;
            case R.id.action_03:
                //隐藏阴历
                MNCalendarVerticalConfig mnCalendarVerticalConfig3 = new MNCalendarVerticalConfig.Builder()
                        .setMnCalendar_showLunar(false)
                        .build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig3);
                break;
            case R.id.action_04:
                //恢复默认
                MNCalendarVerticalConfig mnCalendarVerticalConfig4 = new MNCalendarVerticalConfig.Builder().build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
