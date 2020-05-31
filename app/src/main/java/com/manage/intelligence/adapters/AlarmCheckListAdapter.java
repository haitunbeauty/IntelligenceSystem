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
        int status = mList.get(position).getStatus();
        if (status == 1){
            alarmStatusTv.setText("待审核");
        }else if (status == 2){
            alarmStatusTv.setText("待接单");
            alarmStatusTv.setBackgroundResource(R.drawable.shape_solid_yello_bg);
        }else if (status == 3){
            alarmStatusTv.setText("待处理");
            alarmStatusTv.setBackgroundResource(R.drawable.shape_solid_red_bg);
        }else if (status == 4){
            alarmStatusTv.setText("维修中");
            alarmStatusTv.setBackgroundResource(R.drawable.shape_solid_green_bg);
        }else {
            alarmStatusTv.setText("已审核");
            alarmStatusTv.setBackgroundResource(R.drawable.shape_solid_blue_bg);
        }


        return view;
    }
}
