package musictheory.xinweitech.cn.musictheory.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import musictheory.xinweitech.cn.musictheory.constants.NetConstants;

public class FileLogUtil {
	private String mFilePath;
	
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分
	private static final boolean DEBUG = true;
	
	public FileLogUtil(){
		if(DEBUG){
			mFilePath = NetConstants.LOG_PATH + "log_" + format.format(new Date()) + ".txt";
		}
	}
	
	public void appendLog(String log){
		if(DEBUG){
			String time = format.format(new Date()) + "    ";
			log = time + log;
			FileWriter fw = null;
			try {
				fw = new FileWriter(mFilePath, true);
				fw.append(log);
				fw.append("\n");
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(fw != null){
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
