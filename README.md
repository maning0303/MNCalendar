# MNCalendar
一个简单的日历控件

## 截图
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/calendar.gif)
\<br>
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/calendar_001.png)
 \<br>
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/calendar_002.png)
\<br>
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/calendar_003.png)
\<br>


## 如何添加
### 源码Model添加：
#### 1.直接关联calendarlibrary

``` gradle

	compile project(':calendarlibrary')

```

## 使用方法:

### 1:XML添加:
``` java

    <com.maning.calendarlibrary.MNCalendar
        android:id="@+id/mnCalendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

```

### 2:使用方法:

``` java

    /**
     * Item点击监听
     */
    mnCalendar.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {
        @Override
        public void onCalendarItemClick(Date date) {
            Toast.makeText(context, "点击了:" + sdf.format(date), Toast.LENGTH_SHORT).show();
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


    /**
     *  自定义设置相关
     */
    MNCalendarConfig build = new MNCalendarConfig.Builder()
                            .setMnCalendar_colorWeek("#00ff00")         //星期栏的文字的颜色
                            .setMnCalendar_colorLunar("#FF0000")        //阴历的文字的颜色
                            .setMnCalendar_colorSolar("#9BCCAF")        //阳历的文字的颜色
                            .setMnCalendar_colorTodayBg("#00FFFF")      //今天圆形背景的颜色
                            .setMnCalendar_colorTodayText("#000000")    //今天文字的颜色
                            .setMnCalendar_colorOtherMonth("#F1EDBD")   //不是本月的文字颜色
                            .setMnCalendar_colorTitle("#FF0000")        //标题的颜色(包括文字和左右箭头)
                            .setMnCalendar_showLunar(true)              //是不是显示阴历
                            .setMnCalendar_showWeek(true)               //是不是显示星期栏
                            .setMnCalendar_showTitle(true)              //是不是显示标题栏
                            .build();
    mnCalendar.setConfig(build);

```

### 3:其它方法查看Demo详情