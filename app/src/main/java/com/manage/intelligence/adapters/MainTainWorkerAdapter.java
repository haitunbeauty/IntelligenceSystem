package com.manage.intelligence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.MainTainWorkerBean;

import java.util.ArrayList;

/**
*作者:libao
*添加时间:2020/5/31 10:39
*修改人和时间: 2020/5/31 10:39
*说明:选择派工工人适配器
*/
public class MainTainWorkerAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MainTainWorkerBean> mMainTainWorkerBeans;

    public MainTainWorkerAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<MainTainWorkerBean> mainTainWorkerBeans){
        mMainTainWorkerBeans = mainTainWorkerBeans;
    }

    @Override
    public int getCount() {
        return mMainTainWorkerBeans == null ? 0 : mMainTainWorkerBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mMainTainWorkerBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.maintain_worder_list_item, null);

        TextView  workIdTv = view.findViewById(R.id.work_id_tv);
        TextView  maintainNumberTv = view.findViewById(R.id.maintain_number_tv);
        CheckBox isSelectCb = view.findViewById(R.id.is_select_cb);

        workIdTv.setText(mMainTainWorkerBeans.get(position).getWorkId()+"");
        maintainNumberTv.setText(mMainTainWorkerBeans.get(position).getMaintainOrders()+"(当前维修数)");

        isSelectCb.setChecked(mMainTainWorkerBeans.get(position).isSelected());//初始化  默认都没选择的
        isSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMainTainWorkerBeans.get(position).setSelected(isChecked);
            }
        });

        return view;
    }


    public ArrayList<MainTainWorkerBean>  getData(){
        return mMainTainWorkerBeans;
    }

}
