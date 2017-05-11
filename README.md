# MNCalendar
一个简单的日历控件

## 截图
### 水平方向日历
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mncalendar_gif01.gif)
<br>
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/calendar_001.png)
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/calendar_002.png)

### 垂直方向日历

![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mncalendar_gif02.gif)
<br>
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mn_calendar_vertical_01.png)
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mn_calendar_vertical_02.png)


## 如何添加
### 1:Gradle添加：
#### 1.在Project的build.gradle中添加仓库地址

``` gradle
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

#### 2.在app目录下的build.gradle中添加依赖
``` gradle
	dependencies {
	     compile 'com.github.maning0303:MNCalendar:V1.0.0'
	}
```

### 2:源码Model添加：
#### 直接关联calendarlibrary

``` gradle

	compile project(':calendarlibrary')

```

## 水平日历使用方法:

### 1:XML添加:
``` java

    <com.maning.calendarlibrary.MNCalendar
        android:id="@+id/mnCalendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

```

### 2:代码使用:

``` java

     /**
      * Item点击监听
      */
     mnCalendar.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {

         @Override
         public void onClick(Date date) {
             Toast.makeText(context, "单击:" + sdf2.format(mnCalendar.getCurrentDate()), Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onLongClick(Date date) {
             Toast.makeText(context, "长按:" + sdf2.format(mnCalendar.getCurrentDate()), Toast.LENGTH_SHORT).show();
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

# -------------------------华丽分割线--------------------------------

## 垂直日历使用方法:

### 1:XML添加:
``` java

    <com.maning.calendarlibrary.MNCalendarVertical
            android:id="@+id/mnCalendarVertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

```

### 2:代码使用:

``` java

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
             .setMnCalendar_countMonth(3)                    //显示多少月(默认6个月)
             .build();
     mnCalendarVertical.setConfig(mnCalendarVerticalConfig);

```

### 3:其它方法查看Demo详情


## 感谢：
#### [OneCalendarView](https://github.com/MorochoRochaDarwin/OneCalendarView)