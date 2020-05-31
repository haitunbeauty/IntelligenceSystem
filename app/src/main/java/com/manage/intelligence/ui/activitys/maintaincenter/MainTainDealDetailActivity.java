package com.manage.intelligence.ui.activitys.maintaincenter;

import android.view.View;
import android.widget.Button;

import com.manage.intelligence.R;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.utils.ToastUtil;

/**
*作者:libao
*添加时间:2020/5/31 14:17
*修改人和时间: 2020/5/31 14:17
*说明:待处理详情页面
*/
public class MainTainDealDetailActivity extends BaseActivity implements View.OnClickListener {



    @Override
    protected String getContentTitle() {
        return "待处理详情";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_tain_deal_detail;
    }

    @Override
    public void initView() {

        Button revokeBtn = findViewById(R.id.revoke_btn);
        Button startMainBtn = findViewById(R.id.start_main_btn);
        revokeBtn.setOnClickListener(this);
        startMainBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.revoke_btn:
                ToastUtil.show(this,"撤单");
                break;

            case R.id.start_main_btn:
                ToastUtil.show(this,"开始维修");
                break;

        }
    }
}