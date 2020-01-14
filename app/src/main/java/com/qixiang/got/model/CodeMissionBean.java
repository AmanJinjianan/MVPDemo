package com.qixiang.got.model;

/**
 * created by jinjianan
 * on
 */
public class CodeMissionBean {

    public CodeMissionBean(String rowId, String reward, String state, String performer) {
        this.rowId = rowId;
        this.reward = reward;
        this.state = state;
        this.performer = performer;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String rowId,reward,state,performer;




}
