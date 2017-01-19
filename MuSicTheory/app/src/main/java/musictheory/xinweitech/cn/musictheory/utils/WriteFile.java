package musictheory.xinweitech.cn.musictheory.utils;

import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;

public class WriteFile {
	private static final String filePath="/sdcard/ifitscale/add_reminder_log.txt";
	static File file = new File(filePath);
	 public static void WriteTxtFile(String strcontent)
	    {
	      //每次写入时，都换行写
	      String strContent=strcontent+"\n";
	      try {
	           //File file = new File(filePath);
	           if (!file.exists()) {
	            Log.d("TestFile", "Create the file:" + filePath);
	            file.createNewFile();
	           }
	           RandomAccessFile raf = new RandomAccessFile(file, "rw");
	           raf.seek(file.length());
	           raf.write(strContent.getBytes());
	           raf.close();
	      } catch (Exception e) {
	           Log.e("TestFile", "Error on write File.");
	          }
	    }
}
