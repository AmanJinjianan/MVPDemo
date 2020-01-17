package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.MultipleMissionContract;
import com.qixiang.got.utils.httputils.Constant;

import org.json.JSONObject;

import static com.qixiang.got.utils.httputils.HttpUtils2.sendMsgGet;
//import com.qixiang.got.utils.httputils.HttpUtils;
//import com.qixiang.got.utils.httputils.callback.CommCallback;

/**
 * created by jinjianan
 * on 20191224
 */
public class MultipleMissionPresenter implements MultipleMissionContract.Presenter {
    private MultipleMissionContract.View mView;

    public String userName="",psd="";
    public MultipleMissionPresenter(MultipleMissionContract.View view) {
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
                    JSONObject jsonObject = new JSONObject();
                    try {
                        //jsonObject.put("performer", ConstantData.userAccount);
                        jsonObject.put("taskType","4");
                        //jsonObject.put("state","2");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendMsgGet("http://112.126.60.140:6789/task/getMytask",jsonObject);

                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
