package com.maning.calendarlibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maning.calendarlibrary.R;
import com.maning.calendarlibrary.constant.MNConst;
import com.maning.calendarlibrary.listeners.OnCalendarItemClickListener;
import com.maning.calendarlibrary.listeners.OnCalendarSelectedChangeListener;
import com.maning.calendarlibrary.model.Lunar;
import com.maning.calendarlibrary.model.MNCalendarConfig;
import com.maning.calendarlibrary.model.MNCalendarEventModel;
import com.maning.calendarlibrary.model.Solar;
import com.maning.calendarlibrary.utils.LunarCalendarUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public class MNCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //日期集合
    private ArrayList<Date> mDatas;
    //事件集合
    private ArrayList<MNCalendarEventModel> mEventDatas;

    private LayoutInflater layoutInflater;

    private OnCalendarItemClickListener onCalendarItemClickListener;
    private OnCalendarSelectedChangeListener onCalendarSelectedChangeListener;

    //当前月份的日期
    private Calendar currentCalendar;
    //选中的日期
    private Calendar selectedCalendar;

    private MNCalendarConfig mnCalendarConfig;

    private Context context;

    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
    }

    public void setOnCalendarSelectedChangeListener(OnCalendarSelectedChangeListener onCalendarSelectedChangeListener) {
        this.onCalendarSelectedChangeListener = onCalendarSelectedChangeListener;
    }

    public MNCalendarAdapter(Context context, ArrayList<Date> mDatas, ArrayList<MNCalendarEventModel> mEventDatas, Calendar currentCalendar, Calendar selectedCalendar, MNCalendarConfig mnCalendarConfig) {
        this.context = context;
        this.mDatas = mDatas;
        this.mEventDatas = mEventDatas;
        this.currentCalendar = currentCalendar;
        this.selectedCalendar = selectedCalendar;
        this.mnCalendarConfig = mnCalendarConfig;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public void updateEventDatas(ArrayList<MNCalendarEventModel> mEventDatas) {
        this.mEventDatas = mEventDatas;
        notifyDataSetChanged();
    }

    public void updateConfig(MNCalendarConfig mnCalendarConfig) {
        this.mnCalendarConfig = mnCalendarConfig;
        notifyDataSetChanged();
    }

    public void setSelectedCalendar(Calendar selectedCalendar) {
        this.selectedCalendar = selectedCalendar;
    }

    public void updateSelectedCalendar(Calendar selectedCalendar) {
        this.selectedCalendar = selectedCalendar;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.mn_item_calendar, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder myViewHolder = (MyViewHolder) holder;

            final Date datePosition = mDatas.get(position);
            String position_yyy_MM_dd = MNConst.sdf_yyy_MM_dd.format(datePosition);

            myViewHolder.tv_event.setVisibility(View.GONE);
            myViewHolder.iv_item_bg.setVisibility(View.GONE);
            myViewHolder.iv_item_bg.setBackground(context.getResources().getDrawable(R.drawable.mn_item_bg));
            myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
            myViewHolder.tvDay.setText(String.valueOf(datePosition.getDate()));
            myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorSolar());
            myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorLunar());

            //TODO:判断事件的展示
            if (mEventDatas != null && mEventDatas.size() > 0) {
                for (int i = 0; i < mEventDatas.size(); i++) {
                    MNCalendarEventModel mnCalendarEventModel = mEventDatas.get(i);
                    Date eventDate = mnCalendarEventModel.getEventDate();
                    String event_yyy_MM_dd = MNConst.sdf_yyy_MM_dd.format(eventDate);
                    if (event_yyy_MM_dd.equals(position_yyy_MM_dd)) {
                        myViewHolder.tv_event.setVisibility(View.VISIBLE);
                        //动态修改颜色
                        GradientDrawable myGrad = (GradientDrawable) myViewHolder.tv_event.getBackground();
                        myGrad.setColor(Color.parseColor(mnCalendarEventModel.getEventBgColor()));
                        myViewHolder.tv_event.setTextColor(Color.parseColor(mnCalendarEventModel.getEventTextColor()));
                        myViewHolder.tv_event.setText(mnCalendarEventModel.getEventInfo());
                    }
                }
            }

            //不是本月的颜色变灰
            Date currentDate = currentCalendar.getTime();
            if (datePosition.getMonth() != currentDate.getMonth()) {
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorOtherMonth());
            }

            //判断是不是当天
            Date nowDate = new Date();
            String now_yyy_MM_dd = MNConst.sdf_yyy_MM_dd.format(nowDate);
            if (now_yyy_MM_dd.equals(position_yyy_MM_dd)) {
                myViewHolder.iv_item_bg.setVisibility(View.VISIBLE);
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorTodayText());
                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorTodayText());

                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_item_bg.getBackground();
                myGrad.setColor(mnCalendarConfig.getMnCalendar_colorTodayBg());
            }

            //判断是不是选中状态
            if (selectedCalendar != null) {
                Date selectedDate = selectedCalendar.getTime();
                String selected_yyy_MM_dd = MNConst.sdf_yyy_MM_dd.format(selectedDate);
                if (selected_yyy_MM_dd.equals(position_yyy_MM_dd)) {
                    myViewHolder.iv_item_bg.setVisibility(View.VISIBLE);
                    //动态修改颜色
                    GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_item_bg.getBackground();
                    myGrad.setColor(mnCalendarConfig.getMnCalendar_colorSelected());
                }
            }

            //阴历的显示
            if (mnCalendarConfig.isMnCalendar_showLunar()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(datePosition);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                Lunar solarToLunar = LunarCalendarUtils.solarToLunar(new Solar(year, month, day));
                String lunarDayString = LunarCalendarUtils.getLunarDayString(solarToLunar.lunarDay);
                myViewHolder.tvDay_lunar.setText(lunarDayString);
            } else {
                myViewHolder.tvDay_lunar.setVisibility(View.GONE);
            }


            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Date datePosition = mDatas.get(position);
                    if (onCalendarItemClickListener != null) {
                        onCalendarItemClickListener.onClick(datePosition);
                    }

                    if (selectedCalendar == null) {
                        selectedCalendar = Calendar.getInstance();
                    }
                    selectedCalendar.setTime(datePosition);
                    notifyItemChanged(position);
                    notifyDataSetChanged();

                    onCalendarSelectedChangeListener.onSelectedChange(selectedCalendar);

                }
            });

            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onCalendarItemClickListener != null) {
                        Date datePosition = mDatas.get(position);
                        Lunar lunar = LunarCalendarUtils.solarToLunar(datePosition);
                        onCalendarItemClickListener.onLongClick(datePosition);
                    }
                    return true;
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tvDay_lunar;
        private TextView tv_event;
        private ImageView iv_item_bg;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tvDay_lunar = (TextView) itemView.findViewById(R.id.tvDay_lunar);
            tv_event = (TextView) itemView.findViewById(R.id.tv_event);
            iv_item_bg = (ImageView) itemView.findViewById(R.id.iv_item_bg);
        }
    }

}
