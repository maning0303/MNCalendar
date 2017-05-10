package com.maning.calendarlibrary.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maning.calendarlibrary.R;
import com.maning.calendarlibrary.listeners.OnCalendarItemClickListener;
import com.maning.calendarlibrary.model.MNCalendarConfig;
import com.maning.calendarlibrary.utils.LunarCalendarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public class MNCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Date> mDatas;

    private LayoutInflater layoutInflater;

    private OnCalendarItemClickListener onCalendarItemClickListener;

    private Calendar currentCalendar;

    private MNCalendarConfig mnCalendarConfig;

    private Context context;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
    }

    public MNCalendarAdapter(Context context, ArrayList<Date> mDatas, Calendar currentCalendar, MNCalendarConfig mnCalendarConfig) {
        this.context = context;
        this.mDatas = mDatas;
        this.currentCalendar = currentCalendar;
        this.mnCalendarConfig = mnCalendarConfig;
        layoutInflater = LayoutInflater.from(this.context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.mn_item_calendar, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            Date datePosition = mDatas.get(position);

            myViewHolder.iv_today_bg.setVisibility(View.GONE);
            myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
            myViewHolder.tvDay.setText(String.valueOf(datePosition.getDate()));
            myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorSolar());
            myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorLunar());

            //不是本月的颜色变灰
            Date currentDate = currentCalendar.getTime();
            if (datePosition.getMonth() != currentDate.getMonth()) {
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorOtherMonth());
            }

            //判断是不是当天
            Date nowDate = new Date();
            String now_yyy_MM_dd = sdf.format(nowDate);
            String position_yyy_MM_dd = sdf.format(datePosition);
            if (now_yyy_MM_dd.equals(position_yyy_MM_dd)) {
                myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorTodayText());
                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorTodayText());

                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                myGrad.setColor(mnCalendarConfig.getMnCalendar_colorTodayBg());
            }

            //阴历的显示
            if (mnCalendarConfig.isMnCalendar_showLunar()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(datePosition);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                LunarCalendarUtils.Lunar solarToLunar = LunarCalendarUtils.solarToLunar(new LunarCalendarUtils.Solar(year, month, day));
                String lunarDayString = LunarCalendarUtils.getLunarDayString(solarToLunar.lunarDay);
                myViewHolder.tvDay_lunar.setText(lunarDayString);
            } else {
                myViewHolder.tvDay_lunar.setVisibility(View.GONE);
            }

            if (this.onCalendarItemClickListener != null) {
                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Date datePosition = mDatas.get(position);
                        onCalendarItemClickListener.onClick(datePosition);
                    }
                });

                myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Date datePosition = mDatas.get(position);
                        onCalendarItemClickListener.onLongClick(datePosition);
                        return true;
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tvDay_lunar;
        private ImageView iv_today_bg;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tvDay_lunar = (TextView) itemView.findViewById(R.id.tvDay_lunar);
            iv_today_bg = (ImageView) itemView.findViewById(R.id.iv_today_bg);
        }
    }

}
