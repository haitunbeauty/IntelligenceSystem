package com.manage.intelligence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.AlarmRejectListBean;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;

/**
*作者:libao
*添加时间:2020/5/30 17:51
*修改人和时间: 2020/5/30 17:51
*说明: 报警驳回列表适配器
*/
public class AlarmRejectListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<AlarmRejectListBean> mAlarmRejectListBeans;

    public AlarmRejectListAdapter(FragmentActivity activity) {
        mContext = activity;
    }

    public void setData(ArrayList<AlarmRejectListBean> alarmRejectListBeans){
        mAlarmRejectListBeans = alarmRejectListBeans;
    }

    @Override
    public int getCount() {
        return mAlarmRejectListBeans == null ? 0 : mAlarmRejectListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlarmRejectListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.alarm_reject_list_item, null);
        TextView alarmOrderNumberTv = view.findViewById(R.id.alarm_order_number_tv);
        alarmOrderNumberTv.setText(mAlarmRejectListBeans.get(position).getNumOne());
        return view;
    }
}
