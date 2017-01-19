package musictheory.xinweitech.cn.musictheory.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
	public static Bitmap loadBitmap(String path) {
		return loadBitmap(path, 1);
	}
	
	public static Bitmap loadBitmap(String path, int inSampleSize) {
		Log.d("BitmapUtils", "loadBitmap path = " + path);
		Options options = new Options();

		options.inPreferredConfig  =  Bitmap.Config.RGB_565;
		options.inPurgeable  =  true ;
		options.inSampleSize = inSampleSize;
		return BitmapFactory.decodeFile(path,options);
	}
	
	public static Bitmap zoomBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale((float)targetWidth/w, (float)targetHeight/h);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}
	
	public static void saveBitmap(Bitmap photoBitmap, String path, String photoName) {
//		if (SDcardHelper.isSdcardAvailable()) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File photoFile = new File(path, photoName);
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
						fileOutputStream.flush();
					}
				}
			} catch (FileNotFoundException e) {
				photoFile.delete();
			} catch (IOException e) {
				photoFile.delete();
			} finally {
				try {
					if(fileOutputStream != null){
						fileOutputStream.close();
					}					
					photoBitmap=null;
				} catch (IOException e) {
				}
			}
//		}
	}
	
	public static void renameFile(String srcPath, String destPath){
		File file = new File(srcPath);
		if(file.exists()){
			file.renameTo(new File(destPath));
		}
	}
}
