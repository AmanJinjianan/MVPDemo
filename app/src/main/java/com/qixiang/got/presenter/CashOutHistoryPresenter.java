package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CashOutHistoryContract;

import org.json.JSONObject;

import static com.qixiang.got.utils.httputils.HttpUtils2.sendJsonPost;
//import com.qixiang.got.utils.httputils.HttpUtils;
//import com.qixiang.got.utils.httputils.callback.CommCallback;

/**
 * created by jinjianan
 * on 20191224
 */
public class CashOutHistoryPresenter implements CashOutHistoryContract.Presenter {
    private CashOutHistoryContract.View mView;

    private String balance;
    public String userName="",psd="";
    public CashOutHistoryPresenter(CashOutHistoryContract.View view) {
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendJsonPost("http://112.126.60.140:8080/task/getDrawingsList",jsonObject);

                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
