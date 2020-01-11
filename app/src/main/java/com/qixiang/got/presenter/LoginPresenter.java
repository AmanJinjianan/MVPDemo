package com.qixiang.got.presenter;

import android.os.Looper;
import android.util.Log;

import com.qixiang.got.contract.LoginContract;
import com.qixiang.got.utils.httputils.HttpUtils;
import com.qixiang.got.utils.httputils.HttpUtils2;
import com.qixiang.got.utils.httputils.callback.CommCallback;

import org.json.JSONObject;
//import com.qixiang.got.utils.httputils.HttpUtils;
//import com.qixiang.got.utils.httputils.callback.CommCallback;

/**
 * created by jinjianan
 * on 20191224
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    public String userName="",psd="";
    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void login(String userName1,String psd1) {
        this.userName = userName1;
        this.psd = psd1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    JSONObject object = new JSONObject();
                    object.put("userName", userName);
                    object.put("passWord", psd);
                    JSONObject result = HttpUtils2.sendJsonPost("http://112.126.60.140:8080/users/toLogin", object);

                    mView.showToast("LoginLogin");
                    mView.gotoMain(result);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }


    public void getRobotAnswer(String robotUrl) {
        robotUrl = "http://112.126.60.140:8080/users/toLogin";
        try {
            JSONObject object = new JSONObject();
            object.put("userName", "admin");
            object.put("passWord", "123456");
            String json = object.toString();
            Log.d("CMEventUtil_appkey", json);
            HttpUtils.build().url(robotUrl)
                    .addParam("userName", "admin")
                    .addParam("passWord", "123456")
                    .onExecuteByPost(new CommCallback<String>() {
                        @Override
                        public void onSucceed(String result) {
                            Log.d("CMEventUtil_result", result);

                        }

                        @Override
                        public void onFailed(Throwable throwable) {
                            Log.d("throwable", "throwable:" + throwable);
                            throwable.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
