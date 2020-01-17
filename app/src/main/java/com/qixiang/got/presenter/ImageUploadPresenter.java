package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.ImageUploadContract;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

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
                        jsonObject.put("fileName", readStream(fileName));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    JSONObject jsb = sendJsonPost("http://112.126.60.140:6789/task/fileUpload",jsonObject);

                    mView.sendToMain(jsb);
                    Looper.loop();
                } catch (Exception e) {

                }
            }
        }).start();
    }

    /**
     * 照片转byte二进制
     * @param imagepath 需要转byte的照片路径
     * @return 已经转成的byte
     * @throws Exception
     */
    public byte[] readStream(String imagepath) throws Exception {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }
}
