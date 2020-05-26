package com.manage.intelligence.adapters;

import android.app.Activity;
import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.manage.intelligence.R;
import com.manage.intelligence.bean.CommonTitleAdapterBean;

import java.util.List;


/**
*作者:libao
*添加时间:2020/5/26 7:47
*修改人和时间: 2020/5/26 7:47
*说明: 标题选择适配器的基类
*/
public class CommonTitleAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommonTitleAdapterBean> mList;


    public CommonTitleAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void setData(List<CommonTitleAdapterBean> list){
        this.mList = list;
    }

    @Override
    public int getCount() { return mList == null ? 0 : mList.size(); }
    @Override
    public Object getItem(int position) { return mList.get(position);}
    @Override
    public long getItemId(int position) {  return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView =  View.inflate(mContext, R.layout.item_common_title,null);

        TextView titleTv = convertView.findViewById(R.id.item_title_tv);

        titleTv.setText(mList==null ? "" : mList.get(position).getTitle());

        return convertView;
    }




}
