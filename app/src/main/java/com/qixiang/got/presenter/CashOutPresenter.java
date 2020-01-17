package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CashOutContract;
import com.qixiang.got.utils.httputils.module.Constant;

import org.json.JSONObject;

import static com.qixiang.got.utils.httputils.HttpUtils2.sendJsonPost;
import static com.qixiang.got.utils.httputils.HttpUtils2.sendMsgGet;
//import com.qixiang.got.utils.httputils.HttpUtils;
//import com.qixiang.got.utils.httputils.callback.CommCallback;

/**
 * created by jinjianan
 * on 20191224
 */
public class CashOutPresenter implements CashOutContract.Presenter {
    private CashOutContract.View mView;

    private String balance;
    public String userName="",psd="";
    public CashOutPresenter(CashOutContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMsg(String balance1) {
        this.balance = balance1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName", ConstantData.userAccount);
                        jsonObject.put("state","1");
                        jsonObject.put("drawingsBalance",balance);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendJsonPost("http://112.126.60.140:6789/task/getDrawings",jsonObject);

                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
