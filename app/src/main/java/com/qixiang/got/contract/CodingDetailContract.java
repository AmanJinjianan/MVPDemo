package com.qixiang.got.contract;

import com.qixiang.got.base.BasePresenter;
import com.qixiang.got.base.BaseView;

import org.json.JSONObject;

/**
 * created by jinjianan
 * on 20191224
 */
public class CodingDetailContract {

    public interface View extends BaseView<Presenter>{
        void sendToMain(JSONObject jb);
        //void sendToMain2(JSONObject jb);
    }

    public interface Presenter extends BasePresenter{
        void getMsg(String type);
        void commitResult(String rowId,String reward,String state);
    }

}
