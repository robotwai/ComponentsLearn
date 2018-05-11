package com.example.jkb.myapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jkb on 18/5/11.
 */

public class TimeUtils {
    public static String CalculateTime(String time){
        long nowTime=System.currentTimeMillis();  //获取当前时间的毫秒数
        String msg = null;
        time = time.replaceAll("[A-Z]+"," ").trim();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//指定时间格式
        Date setTime = null;  //指定时间

        try {
            setTime = sdf.parse(time);  //将字符串转换为指定的时间格式
        } catch (ParseException e) {

            e.printStackTrace();
        }
        //时间显示少了八小时所以这里需要再加上
        long reset=setTime.getTime()+8*60*60*1000;   //获取指定时间的毫秒数
        long dateDiff=nowTime-reset;

        if(dateDiff<0){
            msg = "刚刚";
        }else{

            long dateTemp1=dateDiff/1000; //秒
            long dateTemp2=dateTemp1/60; //分钟
            long dateTemp3=dateTemp2/60; //小时
            long dateTemp4=dateTemp3/24; //天数
            long dateTemp5=dateTemp4/30; //月数
            long dateTemp6=dateTemp5/12; //年数

            if(dateTemp6>0){
                msg = dateTemp6+"年前";

            }else if(dateTemp5>0){
                msg = dateTemp5+"个月前";

            }else if(dateTemp4>0){
                msg = dateTemp4+"天前";

            }else if(dateTemp3>0){
                msg = dateTemp3+"小时前";

            }else if(dateTemp2>0){
                msg = dateTemp2+"分钟前";

            }else if(dateTemp1>0){
                msg = "刚刚";

            }
        }
        return msg;

    }
}
