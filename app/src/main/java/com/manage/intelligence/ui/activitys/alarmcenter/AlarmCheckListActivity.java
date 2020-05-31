package com.manage.intelligence.ui.activitys.alarmcenter;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.adapters.AlarmCheckListAdapter;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.bean.AlarmCheckListBean;
import com.manage.intelligence.ui.activitys.maintaincenter.MainTainDealDetailActivity;
import com.manage.intelligence.ui.activitys.maintaincenter.MainTainListDetailActivity;
import com.manage.intelligence.utils.ToastUtil;
import com.manage.intelligence.views.CommonTextWatcher;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
*作者:libao
*添加时间:2020/5/30 18:51
*修改人和时间: 2020/5/30 18:51
*说明:报警审核列表页面
*/
public class AlarmCheckListActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int CHECK_REQUEST_CODE = 1;
    private static final int VERIFY_REQUEST_CODE = 2;
    private static final int MAINTAIN_DETAIL_REQUEST_CODE = 3;
    private static final int MAINTAIN_DEAL_DETAIL_REQUEST_CODE = 4;
    private EditText commonSearchEt;

    @Override
    protected String getContentTitle() {
        return "报警审核";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alarm_check_list;
    }

    @Override
    public void initView() {

        TextView commonSearchTv = findViewById(R.id.common_search_tv);
        commonSearchEt = findViewById(R.id.common_search_et);
        commonSearchEt.setHint("模具号/设备号");
        commonSearchTv.setVisibility(View.VISIBLE);
        commonSearchTv.setOnClickListener(this);
        commonSearchEt.addTextChangedListener(new CommonTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                search(s.toString());
            }
        });

        ListView checkListLv = findViewById(R.id.check_list_lv);
        AlarmCheckListAdapter alarmCheckListAdapter = new AlarmCheckListAdapter(this);
        ArrayList<AlarmCheckListBean> alarmCheckListBeans = new ArrayList<>();
        for (int i=0;i<6;i++){
            AlarmCheckListBean alarmCheckListBean = new AlarmCheckListBean();
            alarmCheckListBean.setId(i);
            alarmCheckListBean.setMaterialNum("NO123456");
            alarmCheckListBean.setMouldNum("MO654321");
            alarmCheckListBean.setStatus(i);//状态  1、待审核
            alarmCheckListBeans.add(alarmCheckListBean);
        }
        alarmCheckListAdapter.setData(alarmCheckListBeans);
        checkListLv.setAdapter(alarmCheckListAdapter);

        checkListLv.setOnItemClickListener(this);


    }

    private void search(String content) {
        if (!TextUtils.isEmpty(content)){
            ToastUtil.show(this,content);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_search_tv:
                search(commonSearchEt.getText().toString());
                break;
        }
    }

    /**
    * 审核页面
    * */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent mIntent;
        AlarmCheckListBean alarmCheckListBean = (AlarmCheckListBean) parent.getItemAtPosition(position);
        if (alarmCheckListBean!=null){
            switch (alarmCheckListBean.getStatus()){
                case 1://待审核
                    mIntent = new Intent(this,AlarmCheckActivity.class);
                    startActivityForResult(mIntent,CHECK_REQUEST_CODE);
                    break;
                case 2://待接单
                    mIntent = new Intent(this, MainTainListDetailActivity.class);
                    startActivityForResult(mIntent,MAINTAIN_DETAIL_REQUEST_CODE);
                    break;
                case 3://待处理
                    mIntent = new Intent(this, MainTainDealDetailActivity.class);
                    startActivityForResult(mIntent,MAINTAIN_DEAL_DETAIL_REQUEST_CODE);
                    break;
                    default:
                        mIntent = new Intent(this,VerifyInfoActivity.class);
                        startActivityForResult(mIntent,VERIFY_REQUEST_CODE);
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == CHECK_REQUEST_CODE){
                ToastUtil.show(this,"审核回来了");
            }else if (requestCode == VERIFY_REQUEST_CODE){
                ToastUtil.show(this,"验证信息回来了");
            }else if (requestCode == MAINTAIN_DETAIL_REQUEST_CODE){
                ToastUtil.show(this,"待接单详情回来了");
            }else if (requestCode == MAINTAIN_DEAL_DETAIL_REQUEST_CODE){
                ToastUtil.show(this,"待处理详情回来了");
            }

        }

    }
}
