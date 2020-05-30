package com.manage.intelligence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.AlarmCheckListBean;
import com.manage.intelligence.ui.activitys.alarmcenter.AlarmCheckListActivity;

import java.util.ArrayList;

public class AlarmCheckListAdapter  extends BaseAdapter {

    private Context mContext;
    private ArrayList<AlarmCheckListBean> mList;

    public AlarmCheckListAdapter(AlarmCheckListActivity context) {
        mContext = context;
    }

    public void setData(ArrayList<AlarmCheckListBean> list){
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public AlarmCheckListBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.alarm_check_list_item, null);

        TextView alarmStatusTv = view.findViewById(R.id.alarm_status_tv);
        if (mList.get(position).getStatus() == 1){
            alarmStatusTv.setText("待审核");
        }else {
            alarmStatusTv.setText("已审核");
            alarmStatusTv.setBackgroundResource(R.drawable.shape_solid_blue_bg);
        }


        return view;
    }
}
