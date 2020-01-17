package com.qixiang.got.presenter;

import android.os.Looper;
import android.util.Log;

import com.qixiang.got.contract.MyContract;
import com.qixiang.got.utils.httputils.HttpUtils;
import com.qixiang.got.utils.httputils.HttpUtils2;
import com.qixiang.got.utils.httputils.callback.CommCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.qixiang.got.utils.httputils.HttpUtils2.sendMsgGet;
//import com.qixiang.got.utils.httputils.HttpUtils;
//import com.qixiang.got.utils.httputils.callback.CommCallback;

/**
 * created by jinjianan
 * on 20191224
 */
public class MyPresenter implements MyContract.Presenter {
    private MyContract.View mView;

    public String userName="",psd="";
    public MyPresenter(MyContract.View view) {
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
//                    Map<String,String> data = new HashMap<>();
//                    data.put("userName","admin");
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName","admin");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendMsgGet("http://112.126.60.140:6789/users/getUserInfo",jsonObject);

                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
