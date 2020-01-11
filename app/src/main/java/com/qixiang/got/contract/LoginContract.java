package com.qixiang.got.contract;

import com.qixiang.got.base.BasePresenter;
import com.qixiang.got.base.BaseView;

import org.json.JSONObject;

/**
 * created by jinjianan
 * on 20191224
 */
public class LoginContract {

    public interface View extends BaseView<Presenter>{
        void gotoMain(JSONObject jb);
    }

    public interface Presenter extends BasePresenter{
        void login(String userName,String psd);
    }

}
