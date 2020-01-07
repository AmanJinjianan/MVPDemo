package com.qixiang.got.base;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);//用于绑定view
    void showToast(String msg);//将通用方法封装到这里
    //可以写上你常用的方法
}
