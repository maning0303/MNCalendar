# MNCalendar
一个简单的日历控件,颜色自定义,水平方向和垂直方向,支持手势滑动,支持区间范围选择；
[![](https://jitpack.io/v/maning0303/MNCalendar.svg)](https://jitpack.io/#maning0303/MNCalendar)

## 截图
### 水平方向日历
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mn_calendar_001.jpg)
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mn_calendar_002.jpg)
![](https://github.com/maning0303/MNCalendar/raw/master/screenshots/mn_calendar_003.jpg)

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
	     compile 'com.github.maning0303:MNCalendar:V1.0.3'
	}
```

### 2:源码Module添加：
#### 直接关联calendarlibrary

``` gradle

	compile project(':calendarlibrary')

```

## 水平日历使用方法:

### 1:XML添加:
``` java

    //相关自定义属性
    <declare-styleable name="MNCalendar">
            <!--最大支持的年数:不包括当前年-->
            <attr name="mnCalendar_maxYear" format="integer" />
            <!--最小支持的年数：包括当前年-->
            <attr name="mnCalendar_minYear" format="integer" />
    </declare-styleable>

    <com.maning.calendarlibrary.MNCalendar
        android:id="@+id/mnCalendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:mnCalendar_maxYear="2101"
        app:mnCalendar_minYear="1900" />

```

### 2:代码使用:

``` java

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


    /**
     *  自定义设置相关
     */
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


    //设置日历相关事件
    ArrayList<MNCalendarEventModel> mEventDatas = new ArrayList<>();
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

```

### 3:其它方法查看Demo详情

### -------------------------华丽分割线--------------------------------

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


## 更新日志:
#### TODO ：下一版本计划
        1:去掉RecyclerView 使用View绘制每月
        2:优化卡顿

#### V1.0.3:
        1:水平日历单选去掉阴历返回（返回后自己转换，提供转换方法）
        2:水平方向切换使用ViewPager
        3:增加日历点击效果
        4:增加日历事件集合
        5:自定义属性最大最小年支持

#### V1.0.2:
        1:水平日历单选增加阴历返回
        2:水平日历标题样式增加自定义
        3:垂直日历优化快速滑动卡顿问题



## 感谢：
#### [OneCalendarView](https://github.com/MorochoRochaDarwin/OneCalendarView)
#### [CalendarView](https://github.com/huanghaibin-dev/CalendarView)
