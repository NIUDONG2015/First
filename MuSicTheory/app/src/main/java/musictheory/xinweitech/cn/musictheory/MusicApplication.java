package musictheory.xinweitech.cn.musictheory;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/1/11.
 */

public class MusicApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Logger.init("Music");

    }
}
