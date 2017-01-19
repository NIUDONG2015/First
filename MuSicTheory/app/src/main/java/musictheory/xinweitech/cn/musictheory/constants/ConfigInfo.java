package musictheory.xinweitech.cn.musictheory.constants;

/**
 * Created by niudong on 2017/1/11.
 */

public class ConfigInfo {
    private static String HOST = "http://101.201.78.224/api/";

    public static String getHOST() {
        return HOST;
    }

    public static void setHOST(String HOST) {
        ConfigInfo.HOST = HOST;
    }
}
