package com.manage.intelligence.ui.activitys.alarmcenter;


import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        userIv.setImageResource(R.mipmap.ic_menu_list);
        userIv.setOnClickListener(this);

        //需要操作的组件
        ImageView scanIv = findViewById(R.id.scan_iv);
        CheckBox checkBox = findViewById(R.id.checkbox);
        TextView materialNumberTv = findViewById(R.id.material_number_tv);//料号
        LinearLayout maintainLl = findViewById(R.id.maintain_ll);//维保性质
        LinearLayout urgencyLl = findViewById(R.id.urgency_ll);//紧急程度
        LinearLayout checkPersonLl = findViewById(R.id.check_person_ll);//审核人
        TextView commitTv = findViewById(R.id.commit_tv);//提交

        EditText deviceNumberEt = findViewById(R.id.device_number_et);//设备号
        EditText mouldNumberEt = findViewById(R.id.mould_number_et);//模具号
        EditText descriptionEt = findViewById(R.id.description_et);//描述
        EditText remarkEt = findViewById(R.id.remark_et);//备注

        scanIv.setOnClickListener(this);
        materialNumberTv.setOnClickListener(this);
        maintainLl.setOnClickListener(this);
        urgencyLl.setOnClickListener(this);
        checkPersonLl.setOnClickListener(this);
        commitTv.setOnClickListener(this);

    }



private Intent mIntent;
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.user_iv:
                mIntent = new Intent(this,AlarmListActivity.class);
                break;

            case R.id.scan_iv:
                ToastUtil.show(this,"扫描");
                break;

            case R.id.material_number_tv:
                ToastUtil.show(this,"料号");
                break;

            case R.id.maintain_ll:
                ToastUtil.show(this,"维保性质");
                break;

            case R.id.urgency_ll:
                ToastUtil.show(this,"紧急程度");
                break;

            case R.id.check_person_ll:
                ToastUtil.show(this,"审核人");
                break;

            case R.id.commit_tv:
                ToastUtil.show(this,"提交");
                break;

        }

        if (mIntent != null){
            startActivity(mIntent);
        }

    }
}
