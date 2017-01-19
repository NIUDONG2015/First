package musictheory.xinweitech.cn.musictheory.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 * <p>
 * <p>
 * 活动管理器
 */

public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);

    }


    public static void RemoveActivity(Activity activity) {

        activities.remove(activity);
    }


    public static void finishAll() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();

            }
        }

    }
}
