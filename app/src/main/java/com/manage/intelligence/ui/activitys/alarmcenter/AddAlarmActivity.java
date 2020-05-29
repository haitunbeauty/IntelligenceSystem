package com.manage.intelligence.ui.activitys.alarmcenter;


import android.content.ContentResolver;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.utils.ToastUtil;

/**
*作者:libao
*添加时间:2020/5/29 20:58
*修改人和时间: 2020/5/29 20:58
*说明: 添加报警页面
*/
public class AddAlarmActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout userLl;
    private ImageView userIv;

    @Override
    protected String getContentTitle() {
        return "模具报警";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_alarm;
    }

    @Override
    public void initView() {
        userLl = findViewById(R.id.user_ll);
        userIv = findViewById(R.id.user_iv);
        userLl.setVisibility(View.VISIBLE);
        userIv.setVisibility(View.VISIBLE);
        userIv.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.user_iv:
                ToastUtil.show(this,"我是菜单");
                break;
        }

    }
}
