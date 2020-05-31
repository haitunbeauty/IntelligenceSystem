package com.manage.intelligence.ui.activitys.maintaincenter;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.adapters.MainTainWorkerAdapter;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.bean.MainTainWorkerBean;
import com.manage.intelligence.utils.ToastUtil;
import com.manage.intelligence.views.CommonTextWatcher;

import java.util.ArrayList;

/**
*作者:libao
*添加时间:2020/5/31 10:22
*修改人和时间: 2020/5/31 10:22
*说明:派单页面  选择人员派单
*/
public class MainTainDispatchingActivity extends BaseActivity implements View.OnClickListener {

    private MainTainWorkerAdapter workerAdapter;
    private EditText commonSearchEt;
    ArrayList<MainTainWorkerBean> mMainTainWorkerBeans;

    @Override
    protected String getContentTitle() {
        return "人员选择";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_tain_dispatching;
    }

    @Override
    public void initView() {

        TextView commonSearchTv = findViewById(R.id.common_search_tv);
        commonSearchEt = findViewById(R.id.common_search_et);
        commonSearchTv.setVisibility(View.VISIBLE);
        commonSearchTv.setOnClickListener(this);
        commonSearchEt.addTextChangedListener(new CommonTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                search();
            }
        });

        ListView workerSelectLv = findViewById(R.id.worker_select_lv);
        workerAdapter = new MainTainWorkerAdapter(this);
        mMainTainWorkerBeans = new ArrayList<>();
        for (int i=0;i<5;i++){
            MainTainWorkerBean mainTainWorkerBean = new MainTainWorkerBean();
            mainTainWorkerBean.setSelected(false);
            mainTainWorkerBean.setMaintainOrders(i);
            mainTainWorkerBean.setName("张三"+i);
            mainTainWorkerBean.setWorkId("F200004"+i);
            mMainTainWorkerBeans.add(mainTainWorkerBean);
        }
        workerAdapter.setData(mMainTainWorkerBeans);
        workerSelectLv.setAdapter(workerAdapter);

        //
        TextView confirmTv = findViewById(R.id.confirm_Tv);
        confirmTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.common_search_tv:
                search();
                break;

            case R.id.confirm_Tv:
                ToastUtil.show(this,"确认");
                break;

        }

    }

    private void search() {
        String content = commonSearchEt.getText().toString();
        if (mMainTainWorkerBeans!=null && mMainTainWorkerBeans.size()>0){
            ArrayList<MainTainWorkerBean> mainTainWorkerBeans = new ArrayList<>();
            for (MainTainWorkerBean mainTainWorkerBean: mMainTainWorkerBeans){
                if (mainTainWorkerBean.getWorkId().contains(content)){
                    mainTainWorkerBeans.add(mainTainWorkerBean);
                }
            }
            if (mainTainWorkerBeans.size()>0){
                workerAdapter.setData(mainTainWorkerBeans);
                workerAdapter.notifyDataSetChanged();
            }else {
                ToastUtil.show(this,"未查询到结果");
            }

        }

    }
}
