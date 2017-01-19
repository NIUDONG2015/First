package musictheory.xinweitech.cn.musictheory.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	public static String getTimeByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
	
	public static String getWeekendTime(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
			calendar.add(Calendar.DATE, 1); //系统默认周六是每周最后一天，需要周日时间，因此加1天
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	public static String getMeasureTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static int getHour(int time){
		return time / (60 * 60);
	}
	
	public static int getMinute(int time){
		return (time % (60 * 60))/(60);
	}
	
	public static int getDayNum(String year, String month){
		int yearNum = Integer.parseInt(year);
		int monthNum = Integer.parseInt(month);
		Calendar calendar = Calendar.getInstance();
		calendar.set(yearNum, monthNum, 0);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static int getAge(String birthday){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar birthdayCalendar = Calendar.getInstance();
		try {
			birthdayCalendar.setTime(sdf.parse(birthday));
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			return now.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getMeasureTime(int year, int month, int day, int hour, int minute, int second){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, second);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
}

























