package com.manage.intelligence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.AlarmRequestListBean;
import java.util.ArrayList;
import androidx.fragment.app.FragmentActivity;

/**
 *作者:libao
 *添加时间:2020/5/30 17:51
 *修改人和时间: 2020/5/30 17:51
 *说明: 报警请求列表适配器
 */
public class AlarmRequestListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<AlarmRequestListBean> mAlarmRequestListBeans;

    public AlarmRequestListAdapter(FragmentActivity activity) {
        mContext = activity;
    }

    public void setData(ArrayList<AlarmRequestListBean> alarmRequestListBeans){
        mAlarmRequestListBeans = alarmRequestListBeans;
    }

    @Override
    public int getCount() {
        return mAlarmRequestListBeans == null ? 0 : mAlarmRequestListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlarmRequestListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.alarm_request_list_item, null);
        TextView alarmOrderNumberTv = view.findViewById(R.id.alarm_order_number_tv);
        alarmOrderNumberTv.setText(mAlarmRequestListBeans.get(position).getNumOne());
        return view;
    }
}
