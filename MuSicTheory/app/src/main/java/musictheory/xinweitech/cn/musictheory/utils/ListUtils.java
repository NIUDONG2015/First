package musictheory.xinweitech.cn.musictheory.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	public static List<String> getNumberList(int startNumber, int endNumber, int interval, String unit){
		ArrayList<String> list = new ArrayList<String>();
		for(int num = startNumber; num <= endNumber; num += interval){
			list.add(String.valueOf(num)+unit);
		}
		return list;
	}
	
	public static List<String> getNumberList(int startNumber, int endNumber, int interval, int digits/*位数*/, String unit/*单位*/){
		DecimalFormat formatter = new DecimalFormat("00");
		ArrayList<String> list = new ArrayList<String>();
		for(int num = startNumber; num <= endNumber; num += interval){
			list.add(formatter.format(num)+unit);
		}
		return list;
	}
}
