package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.ImageUploadContract;

import org.json.JSONObject;

import static com.qixiang.got.utils.httputils.HttpUtils2.sendJsonPost;
//import com.qixiang.got.utils.httputils.HttpUtils;
//import com.qixiang.got.utils.httputils.callback.CommCallback;

/**
 * created by jinjianan
 * on 20191224
 */
public class ImageUploadPresenter implements ImageUploadContract.Presenter {
    private ImageUploadContract.View mView;

    private String fileName;
    public String userName="",psd="";
    public ImageUploadPresenter(ImageUploadContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMsg(String fileName1) {
        this.fileName = fileName1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName", ConstantData.userAccount);
                        jsonObject.put("fileName", fileName);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendJsonPost("http://112.126.60.140:8080/task/fileUpload",jsonObject);

                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
