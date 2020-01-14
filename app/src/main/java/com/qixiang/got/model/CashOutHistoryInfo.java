package com.qixiang.got.model;

/**
 * created by jinjianan
 * on
 */
public class CashOutHistoryInfo {
    public CashOutHistoryInfo(String money, String time, String state) {
        this.money = money;
        this.time = time;
        this.state = state;
    }

    public String getmoney() {
        return money;
    }

    public void setmoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getstate() {
        return state;
    }

    public void setstate(String state) {
        this.state = state;
    }

    public String money;
    public String time;
    public String state;

}
