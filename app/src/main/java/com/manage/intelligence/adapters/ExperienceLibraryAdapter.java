package com.manage.intelligence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.ExperienceLibraryBean;

import java.util.ArrayList;

/**
*作者:libao
*添加时间:2020/5/31 22:24
*修改人和时间: 2020/5/31 22:24
*说明:经验库适配器
*/
public class ExperienceLibraryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ExperienceLibraryBean> mList;


    public ExperienceLibraryAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<ExperienceLibraryBean> list){
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ExperienceLibraryBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_experience_library, null);

        return view;
    }
}
