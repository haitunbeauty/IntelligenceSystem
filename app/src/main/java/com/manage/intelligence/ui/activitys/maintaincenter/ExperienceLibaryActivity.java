package com.manage.intelligence.ui.activitys.maintaincenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.adapters.ExperienceLibraryAdapter;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.bean.ExperienceLibraryBean;
import com.manage.intelligence.utils.ToastUtil;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
*作者:libao
*添加时间:2020/5/31 22:16
*修改人和时间: 2020/5/31 22:16
*说明:经验库页面
*/
public class ExperienceLibaryActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private static final int EXPERIENCE_DETAIL_REQUEST_CODE = 1;
    private ExperienceLibraryAdapter experienceLibaryAdapter;
    private ArrayList<ExperienceLibraryBean> list;

    @Override
    protected String getContentTitle() {
        return "经验库";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_experience_libary;
    }

    @Override
    public void initView() {

        TextView commonSearchTv = findViewById(R.id.common_search_tv);
        commonSearchTv.setVisibility(View.VISIBLE);
        commonSearchTv.setOnClickListener(this);


        ListView experienceLibaryLv = findViewById(R.id.experience_libary_lv);
        experienceLibaryAdapter = new ExperienceLibraryAdapter(this);
        list = new ArrayList<>();
        for (int i=0;i<5;i++){
            ExperienceLibraryBean experienceLibraryBean = new ExperienceLibraryBean();
            experienceLibraryBean.setNumber(i);
            experienceLibraryBean.setDescription("描述"+i);
            experienceLibraryBean.setMethod("处理方法"+i);
            experienceLibraryBean.setOption("对策"+i);
            list.add(experienceLibraryBean);
        }
        experienceLibaryAdapter.setData(list);
        experienceLibaryLv.setAdapter(experienceLibaryAdapter);
        experienceLibaryLv.setOnItemClickListener(this);

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.common_search_tv:
                ToastUtil.show(this,"搜索");
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ExperienceLibraryBean experienceLibraryBean = (ExperienceLibraryBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, ExperienceLibaryDetailActivity.class);
        startActivityForResult(intent,EXPERIENCE_DETAIL_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == EXPERIENCE_DETAIL_REQUEST_CODE){
                Intent intent = new Intent();
                intent.putExtra("experience_bean",data.getSerializableExtra("experience_bean"));
                setResult(RESULT_OK,intent);
                finish();
            }
        }

    }
}