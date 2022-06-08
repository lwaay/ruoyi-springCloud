package com.sinonc.iot.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date parseStr(String str) {
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}

	}
	public static Date parseStrToDate(String str) throws ParseException {
			Date d = new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
			return d;
	}
	
	public static String parseWxStr(String str){
		try {
			Date d = new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
			return parseDate(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseDate(new Date());
	}
	
	//日期字符串数值转换
	public static Long parseStr2(String dataStr,String format){
		if(StringUtils.isBlank(dataStr)){return 0l;}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d =sdf.parse(dataStr);
			return d.getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0l;
		}
	}
	
	public static Date parseStr(String str, String format) {
		try {
			Date d = new SimpleDateFormat(format).parse(str);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}

	}

	public static String parseDate(Date d) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
	}
	public static String parseSmallDate(Date d) {
		return new SimpleDateFormat("yyyy-MM-dd").format(d);
	}
	public static String parseDate2(Date d) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(d);
	}

	public static String parseDate(Date d, String format) {
		return new SimpleDateFormat(format).format(d);
	}

	public static String parseDate(long timeStamp, String format) {
		 Date d = new Date(timeStamp);
		return new SimpleDateFormat(format).format(d);
	}
	
	public static String getDay(int index) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, index);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
	}

	public static String getDay(int index, String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, index);
		return new SimpleDateFormat(format).format(cal.getTime());
	}
	
	/**日期字符串数值转换
	 * @param dataStr 时间串
	 * @param format 时间串格式参数
	 * */
	public static Long parseDateStr2Timestamp(String dataStr,String format){
		try {
			if (StringUtils.isEmpty(dataStr)) {
				return 0l;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = sdf.parse(dataStr);
			return d.getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0l;// 转换失败返回0l
		}
	}
	
	/**
	 * 时间撮格式化字符串
	 * @param timeStamp
	 * @param format
	 * */
	public static String parseTimestamp2Str(long timeStamp, String format) {
		if (timeStamp > 0) {
			Date d = new Date(timeStamp);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(d);
		}
		return "";
	}
	
	/**
	 * 检查当前时间月份时间差,根据偏差计算后是否属于同一年月
	 * @param timestamp 当前时间
	 * @param offset 偏差（分钟）
	 * @param format yyyyMM
	 * **/
	public static boolean isDeviateTimestamp(long timestamp,long offset,String format){
		long offSetMillis = 60l * offset * 1000l;
		long upper = timestamp + offSetMillis;
		Date d = new Date(upper);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dataStr1  = sdf.format(d);
		long lower = timestamp - offSetMillis;
		d.setTime(lower);
		String dataStr2  = sdf.format(d);
		return dataStr1.equals(dataStr2);
	}

	/** 
	 * 计算两个日期之间的天数差 
	 */
	public static int daysBetween(Date startDate, Date endDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		long time1 = cal.getTimeInMillis();

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		long time2 = cal.getTimeInMillis();

		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	/**
	 * 计算开始结束时间的间隔周数(循环周)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long weeksBetween(Date startDate,Date endDate){
		long ys = (endDate.getTime() - startDate.getTime()) / 86400000  % 7;
		if(ys == 0){
			return (endDate.getTime() - startDate.getTime()) / 86400000 / 7;
		}else{
			return (endDate.getTime() - startDate.getTime()) / 86400000 / 7 + 1;
		}
	}

	public static long getTimestamp(String date){
		 Date d=parseStr(date);
		return d.getTime();
	}
	
	public static long getYearMonthDaytamp(String date){
		 Date d=parseStrYearMonthDay(date);
		return d.getTime();
	}
	
	/**
	 * 只有年月日的日期
	 * @param str
	 * @return
	 */
	public static Date parseStrYearMonthDay(String str) {
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 返回当前时间的前一个小时
	 * */
	public static Date getStartTime(int hours){
		Calendar todayStart = Calendar.getInstance();
		todayStart.add(Calendar.HOUR_OF_DAY,-hours);
		return todayStart.getTime();
	}
	
	/**
	 * 获取当天的结束时间 
	 * */
	public static Date getEndTime(){
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}
	
	
	
	/**
	 * 获取上一个月份结束毫秒时间,当前月起始时间
	 * */
	public static long getPreMonthMillis(){
		Calendar currDate = Calendar.getInstance();
		//设置日期为本月最大日期
		currDate.set(Calendar.DAY_OF_MONTH, 1);
		currDate.set(Calendar.HOUR_OF_DAY, 0);
		currDate.set(Calendar.MINUTE, 0);
		currDate.set(Calendar.SECOND, 0);
		currDate.set(Calendar.MILLISECOND,0);
		return currDate.getTimeInMillis();
	}
	
	/**
	 * 根据日期格式获取下一个月的时间字符串,30分钟追加
	 * */
	public static String getDataStr(String format,int m){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, m);
		cal.add(Calendar.MINUTE,30);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 获取上一个工作日的日期的String类型
	 * @return
	 */
	public static String getYesterday(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (today == 1) {//如果是周一
			cal.roll(Calendar.DAY_OF_YEAR, -3);
		} else if (today == 0) {//如果是周日
			cal.roll(Calendar.DAY_OF_YEAR, -2);
		} else {//如果是周2到周6
			cal.roll(Calendar.DAY_OF_YEAR, -1);
		}
		return sf.format(cal.getTime()).toString();
	}
	
	/**
	 * 获取当前日期
	 * @param dateFormat
	 * @return
	 */
	public static String getToday(String dateFormat){
		Date date = new Date();
		SimpleDateFormat sf=new SimpleDateFormat(dateFormat);
		return sf.format(date).toString();
	}
}