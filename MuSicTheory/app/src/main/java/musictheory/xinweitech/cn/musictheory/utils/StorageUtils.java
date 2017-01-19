package musictheory.xinweitech.cn.musictheory.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import musictheory.xinweitech.cn.musictheory.constants.NetConstants;

public class StorageUtils {
	
	private static final String SDCARD_ROOT = Environment
			.getExternalStorageDirectory().getAbsolutePath()+"/";
	
	public static final String FILE_ROOT=SDCARD_ROOT+"testDM/";
	
	private static final long LOW_STORAGE_THRESHOLD = 1024*1024*10;
	
	static {
		mkDownloadDirs();
	}
	
	//创建目录
	private static void mkDownloadDirs(){
		File dir = new File(NetConstants.DOWNLOAD_PATH);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	
	public static boolean isSdCardWrittenable(){
		if(android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}

	public static long getAvailableStorage(){
		String storageDirectory =null;
		storageDirectory = Environment.getExternalStorageDirectory().toString();

		try {
			StatFs stat = new StatFs(storageDirectory);
			long avaliableSize = ((long)stat.getAvailableBlocks()*(long) stat.getBlockSize());
			return avaliableSize;

		} catch (RuntimeException e) {
			return 0;
		}
	}

	public static boolean checkAvailableStorage(){

		if(getAvailableStorage() < LOW_STORAGE_THRESHOLD){
			return false;
		}
		return true;
	}

	public static boolean isSDCardPresent(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public static void mkdir() throws IOException {
		File file =new File(FILE_ROOT);
		if(!file.exists() || !file.isDirectory()){
			file.mkdir();
		}
	}

	public static Bitmap getLoacalBitmap(String url){

		try {
			FileInputStream fis =new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String size(long size){

		if(size / (1024*1024) > 0){
			float tmpSize =(float) (size) / (float) (1024*1024);
			DecimalFormat df = new DecimalFormat("#.##");
			return ""+df.format(tmpSize)+"MB";
		}else if(size / 1024 >0){
			return ""+(size/ 1024)+"KB";
		}else {
			return ""+size+"B";
		}
	}

	public static void installAPK(File file, Context context){
		if(!file.exists())
			return;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
	public static boolean delete(File path){
		
		boolean result =true;
		if(path.exists()){
			if(path.isDirectory()){
				for(File child : path.listFiles()){
					result &= delete(child);
				}
				result &= path.delete();
			}
			if(path.isFile()){
				result &= path.delete();
			}
			if(!result){
				Log.e(null,"Delete failed;");
			}
			return result;
		}else {
			Log.e(null,"File does not exitst.");
			return false;
		}
	}
	
	
	
}



























