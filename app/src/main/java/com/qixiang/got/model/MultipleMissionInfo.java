package com.qixiang.got.model;

/**
 * created by jinjianan
 * on
 */
public class MultipleMissionInfo {

    public String taskDesc;
    public String title;
    public int state;
    private String rowId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }



    public MultipleMissionInfo(String rowId,String title, int state, String taskDesc) {
        this.rowId = rowId;
        this.title = title;
        this.state = state;
        this.taskDesc = taskDesc;
    }
}
