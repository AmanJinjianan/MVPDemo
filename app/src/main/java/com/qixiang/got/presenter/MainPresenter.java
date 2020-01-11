package com.qixiang.got.presenter;

import android.os.Looper;
import android.util.Log;

import com.qixiang.got.contract.LoginContract;
import com.qixiang.got.contract.MainContract;
import com.qixiang.got.fragment.MainFragment;
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
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    JSONObject object = new JSONObject();
                    object.put("userName", "admin");
                    object.put("passWord", "123456");
                    JSONObject result = HttpUtils2.sendJsonPost("http://112.126.60.140:8080/users/toLogin", object);

                    mView.sendToMain(result);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
