package com.manage.intelligence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.manage.intelligence.R;

/**
*作者:libao
*添加时间:2020/5/31 23:39
*修改人和时间: 2020/5/31 23:39
*说明:包养信息适配器
*/
public class MainTainInfoAdapter extends BaseAdapter {

    private Context mContext;

    public MainTainInfoAdapter(Context context) {
        mContext = context;
    }

    public void setData() {

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_maintain_info, null);

        return view;
    }

}
