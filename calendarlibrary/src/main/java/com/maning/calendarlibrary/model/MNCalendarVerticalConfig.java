package com.maning.calendarlibrary.model;

import android.graphics.Color;

/**
 * Created by maning on 2017/5/10.
 * 垂直方向的配置信息
 */

public class MNCalendarVerticalConfig {

    //是否显示阴历
    private boolean mnCalendar_showLunar = true;
    //是否显示星期栏
    private boolean mnCalendar_showWeek = true;
    //每个月标题的样式
    private String mnCalendar_titleFormat = "yyyy年MM月";
    //每个月标题的颜色
    private int mnCalendar_colorTitle = Color.parseColor("#282828");
    //星期栏的颜色
    private int mnCalendar_colorWeek = Color.parseColor("#5E5E5E");
    //阳历的颜色
    private int mnCalendar_colorSolar = Color.parseColor("#282828");
    //阴历的颜色
    private int mnCalendar_colorLunar = Color.parseColor("#979797");
    //今天之前的日期的颜色
    private int mnCalendar_colorBeforeToday = Color.parseColor("#979797");
    //开始结束的背景颜色
    private int mnCalendar_colorStartAndEndBg = Color.parseColor("#df0e0e0e");
    //区间中间的背景颜色
    private int mnCalendar_colorRangeBg = Color.parseColor("#d0353535");
    //区间文字的颜色
    private int mnCalendar_colorRangeText = Color.parseColor("#FFFFFF");
    //显示多少月(默认6个月)
    private int mnCalendar_countMonth = 6;

    private MNCalendarVerticalConfig() {
    }

    public boolean isMnCalendar_showLunar() {
        return mnCalendar_showLunar;
    }

    private void setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
        this.mnCalendar_showLunar = mnCalendar_showLunar;
    }

    public boolean isMnCalendar_showWeek() {
        return mnCalendar_showWeek;
    }

    private void setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
        this.mnCalendar_showWeek = mnCalendar_showWeek;
    }

    public String getMnCalendar_titleFormat() {
        return mnCalendar_titleFormat;
    }

    private void setMnCalendar_titleFormat(String mnCalendar_titleFormat) {
        this.mnCalendar_titleFormat = mnCalendar_titleFormat;
    }

    public int getMnCalendar_colorWeek() {
        return mnCalendar_colorWeek;
    }

    private void setMnCalendar_colorWeek(int mnCalendar_colorWeek) {
        this.mnCalendar_colorWeek = mnCalendar_colorWeek;
    }

    public int getMnCalendar_colorSolar() {
        return mnCalendar_colorSolar;
    }

    private void setMnCalendar_colorSolar(int mnCalendar_colorSolar) {
        this.mnCalendar_colorSolar = mnCalendar_colorSolar;
    }

    public int getMnCalendar_colorLunar() {
        return mnCalendar_colorLunar;
    }

    private void setMnCalendar_colorLunar(int mnCalendar_colorLunar) {
        this.mnCalendar_colorLunar = mnCalendar_colorLunar;
    }

    public int getMnCalendar_colorBeforeToday() {
        return mnCalendar_colorBeforeToday;
    }

    private void setMnCalendar_colorBeforeToday(int mnCalendar_colorBeforeToday) {
        this.mnCalendar_colorBeforeToday = mnCalendar_colorBeforeToday;
    }

    public int getMnCalendar_colorStartAndEndBg() {
        return mnCalendar_colorStartAndEndBg;
    }

    private void setMnCalendar_colorStartAndEndBg(int mnCalendar_colorStartAndEndBg) {
        this.mnCalendar_colorStartAndEndBg = mnCalendar_colorStartAndEndBg;
    }

    public int getMnCalendar_colorRangeBg() {
        return mnCalendar_colorRangeBg;
    }

    private void setMnCalendar_colorRangeBg(int mnCalendar_colorRangeBg) {
        this.mnCalendar_colorRangeBg = mnCalendar_colorRangeBg;
    }

    public int getMnCalendar_colorRangeText() {
        return mnCalendar_colorRangeText;
    }

    private void setMnCalendar_colorRangeText(int mnCalendar_colorRangeText) {
        this.mnCalendar_colorRangeText = mnCalendar_colorRangeText;
    }

    public int getMnCalendar_colorTitle() {
        return mnCalendar_colorTitle;
    }

    private void setMnCalendar_colorTitle(int mnCalendar_colorTitle) {
        this.mnCalendar_colorTitle = mnCalendar_colorTitle;
    }

    public int getMnCalendar_countMonth() {
        return mnCalendar_countMonth;
    }

    private void setMnCalendar_countMonth(int mnCalendar_countMonth) {
        this.mnCalendar_countMonth = mnCalendar_countMonth;
    }

    @Override
    public String toString() {
        return "MNCalendarVerticalConfig{" +
                "mnCalendar_showLunar=" + mnCalendar_showLunar +
                ", mnCalendar_showWeek=" + mnCalendar_showWeek +
                ", mnCalendar_titleFormat='" + mnCalendar_titleFormat + '\'' +
                ", mnCalendar_colorTitle=" + mnCalendar_colorTitle +
                ", mnCalendar_colorWeek=" + mnCalendar_colorWeek +
                ", mnCalendar_colorSolar=" + mnCalendar_colorSolar +
                ", mnCalendar_colorLunar=" + mnCalendar_colorLunar +
                ", mnCalendar_colorBeforeToday=" + mnCalendar_colorBeforeToday +
                ", mnCalendar_colorStartAndEndBg=" + mnCalendar_colorStartAndEndBg +
                ", mnCalendar_colorRangeBg=" + mnCalendar_colorRangeBg +
                ", mnCalendar_colorRangeText=" + mnCalendar_colorRangeText +
                ", mnCalendar_countMonth=" + mnCalendar_countMonth +
                '}';
    }

    public static class Builder {
        private MNCalendarVerticalConfig mnCalendarConfig = null;

        public Builder() {
            this.mnCalendarConfig = new MNCalendarVerticalConfig();
        }

        public Builder setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
            mnCalendarConfig.setMnCalendar_showLunar(mnCalendar_showLunar);
            return this;
        }


        public Builder setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
            mnCalendarConfig.setMnCalendar_showWeek(mnCalendar_showWeek);
            return this;
        }


        public Builder setMnCalendar_titleFormat(String mnCalendar_titleFormat) {
            mnCalendarConfig.setMnCalendar_titleFormat(mnCalendar_titleFormat);
            return this;
        }


        public Builder setMnCalendar_colorTitle(String mnCalendar_colorTitle) {
            mnCalendarConfig.setMnCalendar_colorTitle(Color.parseColor(mnCalendar_colorTitle));
            return this;
        }


        public Builder setMnCalendar_colorWeek(String mnCalendar_colorWeek) {
            mnCalendarConfig.setMnCalendar_colorWeek(Color.parseColor(mnCalendar_colorWeek));
            return this;
        }


        public Builder setMnCalendar_colorSolar(String mnCalendar_colorSolar) {
            mnCalendarConfig.setMnCalendar_colorSolar(Color.parseColor(mnCalendar_colorSolar));
            return this;
        }


        public Builder setMnCalendar_colorLunar(String mnCalendar_colorLunar) {
            mnCalendarConfig.setMnCalendar_colorLunar(Color.parseColor(mnCalendar_colorLunar));
            return this;
        }


        public Builder setMnCalendar_colorBeforeToday(String mnCalendar_colorBeforeToday) {
            mnCalendarConfig.setMnCalendar_colorBeforeToday(Color.parseColor(mnCalendar_colorBeforeToday));
            return this;
        }


        public Builder setMnCalendar_colorStartAndEndBg(String mnCalendar_colorStartAndEndBg) {
            mnCalendarConfig.setMnCalendar_colorStartAndEndBg(Color.parseColor(mnCalendar_colorStartAndEndBg));
            return this;
        }


        public Builder setMnCalendar_colorRangeBg(String mnCalendar_colorRangeBg) {
            mnCalendarConfig.setMnCalendar_colorRangeBg(Color.parseColor(mnCalendar_colorRangeBg));
            return this;
        }


        public Builder setMnCalendar_colorRangeText(String mnCalendar_colorRangeText) {
            mnCalendarConfig.setMnCalendar_colorRangeText(Color.parseColor(mnCalendar_colorRangeText));
            return this;
        }

        public Builder setMnCalendar_countMonth(int mnCalendar_countMonth) {
            mnCalendarConfig.setMnCalendar_countMonth(mnCalendar_countMonth);
            return this;
        }

        public MNCalendarVerticalConfig build() {
            return mnCalendarConfig;
        }

    }


}
