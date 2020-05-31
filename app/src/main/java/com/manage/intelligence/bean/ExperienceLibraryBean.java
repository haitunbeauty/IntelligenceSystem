package com.manage.intelligence.bean;

import com.manage.intelligence.base.BaseBean;

/**
*作者:libao
*添加时间:2020/5/31 22:28
*修改人和时间: 2020/5/31 22:28
*说明:经验库bean
*/
public class ExperienceLibraryBean extends BaseBean {

    private int number;//经验代码
    private String description;//描述
    private String option;//注意事项
    private String method;//对策 方法

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
