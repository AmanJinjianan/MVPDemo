package com.qixiang.got.model;

/**
 * created by jinjianan
 * on
 */
public class MyFraItemInfo {
    public String title;

    public int getIcID() {
        return icID;
    }

    public void setIcID(int icID) {
        this.icID = icID;
    }

    private int icID;
    public MyFraItemInfo(String title,int icID){
        this.title = title;
        this.icID = icID;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
