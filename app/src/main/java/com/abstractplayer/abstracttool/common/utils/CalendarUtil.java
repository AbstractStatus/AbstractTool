package com.abstractplayer.abstracttool.common.utils;

import java.util.Calendar;

public class CalendarUtil {
    //默认月视图显示6 * 7天
    public final static int DEFAULT_MONTH_VIEW_DAYS_NUM = 42;

    //默认星期从周日开始
    public enum WeekDay{
        Sunday,
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday
    }


    //获取某日在月视图内的pos, 0~42
    public static int getTodayPosition(int[][] dates){
        int[] curTimeInfo = getCurTimeIntArrInfo();
        for(int i = 0; i < DEFAULT_MONTH_VIEW_DAYS_NUM; i++){
            if(dates[i][0] == curTimeInfo[0]
                && dates[i][1] == curTimeInfo[1]
                && dates[i][2] == curTimeInfo[3]){
                return i;
            }
        }
        return -1;
    }


    //日期的绝对id
    public static int getRawPosition(int year, int month, int dayOfMonth){
        return dayOfMonth + month * 100 + (year - 2000) * 10000;
    }


    //获取当前的年和月
    public static int[] getNowYearAndMonth(){
        Calendar calendar = Calendar.getInstance();
        return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1};
    }


    //根据年和月的数据获取上一个年和月的数据
    public static int[] getPreYearAndMonth(int year, int month){
        return getAddYearAndMonth(year, month, -1);
    }


    //根据年和月的数据获取下一个年和月的数据
    public static int[] getNextYearAndMonth(int year, int month){
        return getAddYearAndMonth(year, month, 1);
    }


    //根据年和月的数据获取月差值年和月的数据
    public static int[] getAddYearAndMonth(int year, int month, int addMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        calendar.add(Calendar.MONTH, addMonth);
        return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1};
    }


    //获取某个月份42天的第一天日历数据
    public static int[] get42FirstDateByYearAndMonth(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int curMonthFirstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -curMonthFirstDayOfWeek);
        calendar.add(Calendar.DATE, 1);
        int[] res = getOneDayData(calendar);
        return res;
    }


    //获取某个月份42天的最后天日历数据
    public static int[] get42LastDateByYearAndMonth(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int curMonthFirstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -curMonthFirstDayOfWeek);
        for(int i = 0; i < DEFAULT_MONTH_VIEW_DAYS_NUM; i++){
            calendar.add(Calendar.DATE, 1);
        }
        int[] res = getOneDayData(calendar);
        return res;
    }


    //获取某个月份42天的某一天的日历数据
    public static int[] get42PosDateByYearAndMonth(int year, int month, int pos){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int curMonthFirstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -curMonthFirstDayOfWeek);
        for(int i = 0; i < pos + 1; i++){
            calendar.add(Calendar.DATE, 1);
        }
        int[] res = getOneDayData(calendar);
        return res;
    }




    /**
     *获取某个月份的42天日历数据
     * @param year
     * @param month 从1开始
     * @return
     */
    public static int[][] get42DaysDataByYearAndMonth(int year, int month){
        Calendar calendar = Calendar.getInstance();
        int[][] res = new int[DEFAULT_MONTH_VIEW_DAYS_NUM][5];

        calendar.set(year, month - 1, 1);
        int curMonthFirstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -curMonthFirstDayOfWeek);

        for(int i = 0; i < DEFAULT_MONTH_VIEW_DAYS_NUM; i++){
            calendar.add(Calendar.DATE, 1);
            res[i] = getOneDayData(calendar, month);
        }

        return res;
    }


    /**
     * 获取当前月份的42天日历数据
     * @return [[year, month, dayOfMonth, dayOfWeek],
     * [year, month, dayOfMonth, dayOfWeek]...]
     */
    public static int[][] get42DaysDataByCurMonth(){
        Calendar calendar = Calendar.getInstance();
        int[][] res = new int[DEFAULT_MONTH_VIEW_DAYS_NUM][4];

        //从0开始计算的月
        int curMonth = calendar.get(Calendar.MONTH);
        int curYear = calendar.get(Calendar.YEAR);
        calendar.set(curYear, curMonth, 1);
        int curMonthFirstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -curMonthFirstDayOfWeek);

        for(int i = 0; i < DEFAULT_MONTH_VIEW_DAYS_NUM; i++){
            calendar.add(Calendar.DATE, 1);
            res[i] = getOneDayData(calendar);
        }

        return res;
    }


    //获取某天的日历数据，包括年、月、日、星期
    public static int[] getOneDayData(Calendar calendar){
        int[] res = new int[4];

        res[0] = calendar.get(Calendar.YEAR);
        res[1] = calendar.get(Calendar.MONTH) + 1;
        res[2] = calendar.get(Calendar.DAY_OF_MONTH);
        res[3] = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return res;
    }

    //获取某天的日历数据，包括年、月、日、星期、是否本月
    public static int[] getOneDayData(Calendar calendar, int month){
        int[] res = new int[5];

        res[0] = calendar.get(Calendar.YEAR);
        res[1] = calendar.get(Calendar.MONTH) + 1;
        res[2] = calendar.get(Calendar.DAY_OF_MONTH);
        res[3] = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        res[4] = res[1] == month ? 1 : 0;
        return res;
    }


    /**
     * 获取当前时间所有日历数据
     * -----------------------
     * 注意：
     * Month返回从0开始，故调用需要加一
     * DayOfWeek是星期几，从星期天开始计算，且1代表星期天
     * DAY_OF_WEEK_IN_MONTH表示从本月最开始的周日计算，现在是第几个星期
     * HOUR_OF_DAY是24小时制
     * HOUR是12小时制
     * MILLISECOND不是long类型那个，是这一秒内多少毫秒了
     */
    public static int[] getCurTimeIntArrInfo(){
        Calendar calendar = Calendar.getInstance();
        int[] res = new int[12];

        res[0] = calendar.get(Calendar.YEAR);
        res[1] = calendar.get(Calendar.MONTH) + 1;
        res[2] = calendar.get(Calendar.DAY_OF_YEAR);
        res[3] = calendar.get(Calendar.DAY_OF_MONTH);
        res[4] = calendar.get(Calendar.DAY_OF_WEEK);
        res[5] = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        res[6] = calendar.get(Calendar.HOUR_OF_DAY);
        res[7] = calendar.get(Calendar.HOUR);
        res[8] = calendar.get(Calendar.MINUTE);
        res[9] = calendar.get(Calendar.SECOND);
        res[10] = calendar.get(Calendar.MILLISECOND);
        res[11] = calendar.get(Calendar.DATE);

        return res;
    }


    //打印当前时间所有日历数据
    public static String getCurTimeStringInfo(){
        int[] curTimeIntArrInfo = getCurTimeIntArrInfo();

        return "\nYEAR  " +
                curTimeIntArrInfo[0] +
                "\nMONTH  " +
                curTimeIntArrInfo[1] +
                "\nDAY_OF_YEAR  " +
                curTimeIntArrInfo[2] +
                "\nDAY_OF_MONTH  " +
                curTimeIntArrInfo[3] +
                "\nDAY_OF_WEEK  " +
                curTimeIntArrInfo[4] +
                "\nDAY_OF_WEEK_IN_MONTH  " +
                curTimeIntArrInfo[5] +
                "\nHOUR_OF_DAY  " +
                curTimeIntArrInfo[6] +
                "\nHOUR  " +
                curTimeIntArrInfo[7] +
                "\nMINUTE  " +
                curTimeIntArrInfo[8] +
                "\nSECOND  " +
                curTimeIntArrInfo[9] +
                "\nMILLISECOND  " +
                curTimeIntArrInfo[10] +
                "\nDATE  " +
                curTimeIntArrInfo[11];

    }


    //打印年月日
    public static String formatToYearMonthDay(int year, int month, int dayOfMonth){
        return year + "-" + (month < 10 ? "0" + month : month)
                + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
    }

    /**
     *打印年月日-星期几
     * @param year 年
     * @param month 月
     * @param dayOfMonth 日
     * @param dayOfWeek 从周日算起
     */
    public static String formatToYearMonthDayAndOfWeek(int year, int month, int dayOfMonth, int dayOfWeek){
        return year + "-" + month + "-" + dayOfMonth + "-"
                + WeekDay.values()[dayOfWeek - 1].toString();
    }


    //打印二维整形数组
    public static String format2DIntArr(int[][] arr){
        int n1 = arr == null ? 0 : arr.length;
        int n2 = arr != null && arr[0] != null ? arr[0].length : 0;
        StringBuilder res = new StringBuilder();
        for(int i1 = 0; i1 < n1; i1++){
            for(int i2 = 0; i2 < n2; i2++){
                res.append(arr[i1][i2]).append("  ");
            }
            res.append("\n");
        }

        return res.toString();
    }


    /**
     * 测试日历单例对象的数据获取是否一致
     * 单例，结果相同
     */
    public static String get10000TimesMillisecond(){
        Calendar calendar = Calendar.getInstance();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < 10000; i++){
            res.append("\n").append(calendar.get(Calendar.MILLISECOND));
        }
        return res.toString();
    }

    //xxxx-xx-xx
    public static int[] decodeDate(String s){
        int[] res = new int[3];

        String[] ss = s.split("-");

        for(int i = 0; i < res.length; i++){
            res[i] = Integer.parseInt(ss[i]);
        }

        return res;
    }

    //xx:xx:xx
    public static int[] decodeTime(String s){
        int[] res = new int[3];

        String[] ss = s.split(":");

        for(int i = 0; i < res.length; i++){
            res[i] = Integer.parseInt(ss[i]);
        }

        return res;
    }

    //xxxx-xx-xx xx:xx:xx
    public static int[] decodeDateTime(String s){
        int[] res = new int[6];

        String[] ss = s.split(" ");

        int[] res1 = decodeDate(ss[0]);
        int[] res2 = decodeTime(ss[1]);

        for(int i = 0; i < res.length; i++){
            if(i < 3){
                res[i] = res1[i];
            }
            else{
                res[i] = res2[i - 3];
            }
        }

        return res;
    }


    //年月日时分秒
    public static int[] getCurTime(){
        Calendar calendar = Calendar.getInstance();
        int[] res = new int[6];

        res[0] = calendar.get(Calendar.YEAR);
        res[1] = calendar.get(Calendar.MONTH) + 1;
        res[2] = calendar.get(Calendar.DAY_OF_MONTH);
        res[3] = calendar.get(Calendar.HOUR_OF_DAY);
        res[4] = calendar.get(Calendar.MINUTE);
        res[5] = calendar.get(Calendar.SECOND);

        return res;
    }


    //获取当前 年、月、日 数据
    public static int[] getCurDate(){
        Calendar calendar = Calendar.getInstance();
        int[] res = new int[3];

        res[0] = calendar.get(Calendar.YEAR);
        res[1] = calendar.get(Calendar.MONTH) + 1;
        res[2] = calendar.get(Calendar.DAY_OF_MONTH);

        return res;
    }

    //获取当前 年、月、日 标准字符串
    public static String getCurDateString(){
        Calendar calendar = Calendar.getInstance();
        int[] date = getCurDate();
        return getDateString(date);
    }

    public static String getDateString(int[] date){
        String res = "";
        res += String.valueOf(date[0]);
        res += date[1] < 10 ? "0" + date[1] : String.valueOf(date[1]);
        res += date[2] < 10 ? "0" + date[2] : String.valueOf(date[2]);

        return res;
    }
}
