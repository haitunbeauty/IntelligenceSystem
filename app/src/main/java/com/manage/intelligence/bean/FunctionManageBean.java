package com.manage.intelligence.bean;

import java.io.Serializable;
import java.util.List;

/**
* 功能菜单
* */
public class FunctionManageBean implements Serializable {

    private String title;

    private List<FunctionItem> functionItems;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FunctionItem> getFunctionItems() {
        return functionItems;
    }

    public void setFunctionItems(List<FunctionItem> functionItems) {
        this.functionItems = functionItems;
    }

    public static class FunctionItem {

        private String name;
        private String icon;
        private int num;//未处理的消息

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


}
