package com.manage.intelligence.bean;

/**
*作者:libao
*添加时间:2020/5/31 10:35
*修改人和时间: 2020/5/31 10:35
*说明: 选择派工的工人 bean
*/
public class MainTainWorkerBean {

    private String workId;//工号
    private String name;//名字
    private int maintainOrders;//当前维修数
    private boolean isSelected;//是否选择

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaintainOrders() {
        return maintainOrders;
    }

    public void setMaintainOrders(int maintainOrders) {
        this.maintainOrders = maintainOrders;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
