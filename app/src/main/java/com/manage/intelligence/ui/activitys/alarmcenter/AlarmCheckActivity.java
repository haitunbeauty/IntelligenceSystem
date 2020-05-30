package com.manage.intelligence.ui.activitys.alarmcenter;


import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.manage.intelligence.R;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.ui.main.MainActivity;
import com.manage.intelligence.utils.DateTimePickerUtils;

import java.util.Date;

/**
*作者:libao
*添加时间:2020/5/30 19:57
*修改人和时间: 2020/5/30 19:57
*说明:报警审核页面
*/
public class AlarmCheckActivity extends BaseActivity implements View.OnClickListener {


    private TimePickerView pickerView;

    @Override
    protected String getContentTitle() {
        return "审核页面";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alarm_check;
    }

    @Override
    public void initView() {

        initTimePicker();


        LinearLayout alarmCheckSelectIdeaTypeLl = findViewById(R.id.alarm_check_select_idea_type_ll);//选择意见类型
        LinearLayout alarmCheckSelectMaintainTimeLl = findViewById(R.id.alarm_check_select_maintain_time_ll);//选择日期
        Button cancelBtn = findViewById(R.id.cancel_btn);
        Button confirmBtn = findViewById(R.id.confirm_btn);

        alarmCheckSelectIdeaTypeLl.setOnClickListener(this);
        alarmCheckSelectMaintainTimeLl.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

    }


    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(AlarmCheckActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
          .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
          .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
          .setLineSpacingMultiplier(5.0f)
          .isAlphaGradient(true)
          .build();

        DateTimePickerUtils.initPickerView(pickerView);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.alarm_check_select_idea_type_ll:
                showIdeaPop();
                break;

            case R.id.alarm_check_select_maintain_time_ll:
                showDatePop(v);
                break;

            case R.id.cancel_btn:
                finish();
                break;

            case R.id.confirm_btn:
                setResult(RESULT_OK);
                finish();
                break;

        }

    }

    public void showIdeaPop(){

    }

    public void showDatePop(View v){
        if (pickerView != null) {
            pickerView.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
        }
    }

}
