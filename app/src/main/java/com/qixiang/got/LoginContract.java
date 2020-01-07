package com.qixiang.got;

import com.qixiang.got.base.BasePresenter;
import com.qixiang.got.base.BaseView;

/**
 * created by jinjianan
 * on 20191224
 */
public class LoginContract {

    public interface View extends BaseView<Presenter>{
        void gotoMain();
    }

    public interface Presenter extends BasePresenter{
        void login();
    }

}
