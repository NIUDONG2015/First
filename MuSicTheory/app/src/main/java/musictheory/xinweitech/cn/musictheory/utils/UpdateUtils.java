package musictheory.xinweitech.cn.musictheory.utils;

import android.content.Context;

import java.io.File;

import musictheory.xinweitech.cn.musictheory.constants.NetConstants;

public class UpdateUtils {
	/**
	 * 下载文件处理，如果已经下载了，那么开始安装
	 */
	public void downloadNewVersionApp(Context context, String url, String version){
		if(checkDownloadFileExits(version)){
			File file =new File(NetConstants.DOWNLOAD_PATH + getDownloadAppName(version));
			StorageUtils.installAPK(file, context);
			return ;
		}
	/*	Intent intent = new Intent(context, MainService.class);
		intent.setAction(IFitScaleConstains.MSG_DOWNLOAD_APP);
		intent.putExtra("url", url);
		intent.putExtra("name", getDownloadAppName(version));
		context.startService(intent);*/
	}
	
	private String getDownloadAppName(String version){
		return "ifitscale_"+version+".apk";
	}
	
	/**
	 * 判断文件是否存在
	 */
	private boolean checkDownloadFileExits(String version){
		File file=new File(NetConstants.DOWNLOAD_PATH+getDownloadAppName(version));
		if(file.exists()){
			return true;
		}
		return false;
	}
}
