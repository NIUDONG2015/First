package musictheory.xinweitech.cn.musictheory.presenter;


import musictheory.xinweitech.cn.musictheory.entity.User;
import musictheory.xinweitech.cn.musictheory.ui.activity.LoginActivity;

/**
 * Created by Administrator on 2016/12/29.
 */

public class LoginPresenter {
    //我需要调用View里边的数据
    private LoginActivity activity;

    public LoginPresenter(LoginActivity activity) {//传入上下文额
        this.activity = activity;
    }

    public void longin(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);

/*

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserLoginNet userLoginNet = new UserLoginNet();
                boolean login = userLoginNet.sendUserLoginInfo(user);
                if (login)
                    activity.success();
                else
                    activity.failed();

            }
        }).start();
*/


    }


}



