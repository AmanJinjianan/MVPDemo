package com.qixiang.got.presenter;

import android.os.Looper;

import com.qixiang.got.LoginContract;

/**
 * created by jinjianan
 * on 20191224
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view){
        this.mView = view;
        mView.setPresenter(this);
    }
    @Override
    public void login() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    Looper.prepare();
                    mView.showToast("LoginLogin");
                    mView.gotoMain();
                    Looper.loop();
                }catch (Exception e){

                }
            }
        }).start();
    }
}
