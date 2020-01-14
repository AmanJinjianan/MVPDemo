package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CodingDetailContract;
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
public class CodingDetailPresenter implements CodingDetailContract.Presenter {
    private CodingDetailContract.View mView;

    private String type;
    public String rowId="",reward="",state="";
    public CodingDetailPresenter(CodingDetailContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMsg(String type1) {
        this.type = type1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName", ConstantData.userAccount);
                        //(1-打码,2-打字,3-分享,4-综合)
                        jsonObject.put("taskType", type);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendMsgGet("http://112.126.60.140:8080/task/getTaskByUserName",jsonObject);
                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

    @Override
    public void commitResult(String rowId1,String reward1,String state1) {
        this.rowId = rowId1;
        this.reward = reward1;
        this.state = state1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("rowId", rowId);
                        jsonObject.put("reward", reward);
                        jsonObject.put("state", state);
                        jsonObject.put("performer", ConstantData.userAccount);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendJsonPost("http://112.126.60.140:8080/task/saveResult",jsonObject);
                    //mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
