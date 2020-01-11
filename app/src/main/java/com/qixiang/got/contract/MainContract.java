package com.qixiang.got.contract;

import com.qixiang.got.base.BasePresenter;
import com.qixiang.got.base.BaseView;

import org.json.JSONObject;

/**
 * created by jinjianan
 * on 20191224
 */
public class MainContract {

    public interface View extends BaseView<Presenter>{
        void sendToMain(JSONObject jb);
    }

    public interface Presenter extends BasePresenter{
        void getMsg();
    }

}
