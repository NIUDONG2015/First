package musictheory.xinweitech.cn.musictheory.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**

 */

public class MyReceiver extends BroadcastReceiver {
    private static MyReceiver myReceiver;
    private ConnectivityManager connectivityManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "网络连接成功！",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "网络连接失败！",
                    Toast.LENGTH_SHORT).show();
        }
    }

    static {
        myReceiver = new MyReceiver();

    }

    public static MyReceiver getInstance() {
        return myReceiver;
    }



}
