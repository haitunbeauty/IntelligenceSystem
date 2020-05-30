package com.manage.intelligence.bean;

/**
*作者:libao
*添加时间:2020/5/30 18:58
*修改人和时间: 2020/5/30 18:58
*说明:报警审核列表 bean
*/
public class AlarmCheckListBean {

    private int id;
    private int status;
    private String materialNum;
    private String mouldNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String materialNum) {
        this.materialNum = materialNum;
    }

    public String getMouldNum() {
        return mouldNum;
    }

    public void setMouldNum(String mouldNum) {
        this.mouldNum = mouldNum;
    }
}
