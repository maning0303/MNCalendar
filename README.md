# MNCalendar
一个简单的日历控件,高度颜色自定义,水平方向和垂直方向,支持手势滑动,支持区间范围选择
[![](https://jitpack.io/v/maning0303/MNCalendar.svg)](https://jitpack.io/#maning0303/MNCalendar)

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
	     compile 'com.github.maning0303:MNCalendar:V1.0.2'
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
            public void onClick(Date date, Lunar lunar) {
                //阳历转换阴历
                //Lunar solarToLunar = LunarCalendarUtils.solarToLunar(date);

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
                            .setMnCalendar_TitleDateFormat("yyyy年MM月") //标题样式(默认:yyyy-MM)
                            .build();
    mnCalendar.setConfig(build);

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
#### V1.0.2:
        1:水平日历单选增加阴历返回
        2:水平日历标题样式增加自定义
        3:垂直日历优化快速滑动卡顿问题



## 感谢：
#### [OneCalendarView](https://github.com/MorochoRochaDarwin/OneCalendarView)


## 推荐:
Name | Describe |
--- | --- |
[GankMM](https://github.com/maning0303/GankMM) | （Material Design & MVP & Retrofit + OKHttp & RecyclerView ...）Gank.io Android客户端：每天一张美女图片，一个视频短片，若干Android，iOS等程序干货，周一到周五每天更新，数据全部由 干货集中营 提供,持续更新。 |
[MNUpdateAPK](https://github.com/maning0303/MNUpdateAPK) | Android APK 版本更新的下载和安装,适配7.0,简单方便。 |
[MNImageBrowser](https://github.com/maning0303/MNImageBrowser) | 交互特效的图片浏览框架,微信向下滑动动态关闭 |
[MNCalendar](https://github.com/maning0303/MNCalendar) | 简单的日历控件练习，水平方向日历支持手势滑动切换，跳转月份；垂直方向日历选取区间范围。 |
[MClearEditText](https://github.com/maning0303/MClearEditText) | 带有删除功能的EditText |
[MNCrashMonitor](https://github.com/maning0303/MNCrashMonitor) | Debug监听程序崩溃日志,展示崩溃日志列表，方便自己平时调试。 |
[MNProgressHUD](https://github.com/maning0303/MNProgressHUD) | MNProgressHUD是对常用的自定义弹框封装,加载ProgressDialog,状态显示的StatusDialog和自定义Toast,支持背景颜色,圆角,边框和文字的自定义。 |
[MNXUtilsDB](https://github.com/maning0303/MNXUtilsDB) | xUtils3 数据库模块单独抽取出来，方便使用。 |
[MNVideoPlayer](https://github.com/maning0303/MNVideoPlayer) | SurfaceView + MediaPlayer 实现的视频播放器，支持横竖屏切换，手势快进快退、调节音量，亮度等。------代码简单，新手可以看一看。 |
[MNZXingCode](https://github.com/maning0303/MNZXingCode) | 快速集成二维码扫描和生成二维码 |
[MNChangeSkin](https://github.com/maning0303/MNChangeSkin) | Android夜间模式，通过Theme实现 |
[SwitcherView](https://github.com/maning0303/SwitcherView) | 垂直滚动的广告栏文字展示。 |
[MNPasswordEditText](https://github.com/maning0303/MNPasswordEditText) | 类似微信支付宝的密码输入框。 |
[MNSwipeToLoadDemo](https://github.com/maning0303/MNSwipeToLoadDemo) | 利用SwipeToLoadLayout实现的各种下拉刷新效果（饿了吗，京东，百度外卖，美团外卖，天猫下拉刷新等）。 |


