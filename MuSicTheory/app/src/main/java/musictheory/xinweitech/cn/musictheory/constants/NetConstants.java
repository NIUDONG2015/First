package musictheory.xinweitech.cn.musictheory.constants;

import android.os.Environment;

import java.io.File;

/**
 * Created by admin on 2016/12/30.
 */

public interface NetConstants {
    String HOST = "https://101.201.78.224:443/api/";
    String TEACHER_PERSONALINFO = "teacher/";
    String NIU = "NIUDONG2015";


    String APP_PATH = Environment.getExternalStorageDirectory().getPath()
            + File.separator + "ifitscale" + File.separator;

    String CRASH_LOG_PATH = APP_PATH + "crash" + File.separator;
    String LOG_PATH = APP_PATH + "log" + File.separator;

    String DOWNLOAD_PATH = APP_PATH + "download" + File.separator;

    String MSG_DOWNLOAD_APP = "MSG_DOWNLOAD_APP";
}
