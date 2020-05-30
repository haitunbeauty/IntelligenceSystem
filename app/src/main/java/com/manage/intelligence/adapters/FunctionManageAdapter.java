package com.manage.intelligence.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manage.intelligence.R;
import com.manage.intelligence.bean.FunctionManageBean;
import com.manage.intelligence.ui.activitys.alarmcenter.AddAlarmActivity;
import com.manage.intelligence.ui.activitys.alarmcenter.AlarmCheckListActivity;
import com.manage.intelligence.ui.activitys.modules.ModuleActivity;
import com.manage.intelligence.ui.activitys.modules.ModuleExitAndEntryActivity;
import com.manage.intelligence.utils.PhoneUtil;
import com.manage.intelligence.utils.ToastUtil;

import java.util.List;

import androidx.fragment.app.FragmentActivity;

/**
*作者:libao
*添加时间:2020/5/24 11:57
*修改人和时间: 2020/5/24 11:57
*说明:功能菜单适配器
*/
public class FunctionManageAdapter extends BaseAdapter {

    private Context mContext;
    private List<FunctionManageBean> mFunctionList;


    public FunctionManageAdapter(FragmentActivity activity) {
        this.mContext = activity;

    }

    public void setData(List<FunctionManageBean> functionList){
        this.mFunctionList = functionList;
    }


    @Override
    public int getCount() {
        return mFunctionList == null ? 0 : mFunctionList.size();
    }

    @Override
    public FunctionManageBean getItem(int position) {
        return mFunctionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_function_manage, null);
        TextView titleTv = view.findViewById(R.id.item_title_tv);
        GridView gridView = view.findViewById(R.id.item_grid_view);
        gridView.setTag(position);
        titleTv.setText(mFunctionList.get(position).getTitle()+"");


        List<FunctionManageBean.FunctionItem> functionItems = mFunctionList.get(position).getFunctionItems();
        GridViewAdapter gridViewAdapter = new GridViewAdapter(functionItems);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = null;
                FunctionManageBean.FunctionItem functionItem = mFunctionList.get((int)parent.getTag()).getFunctionItems().get(position);
                if (functionItem != null){
                    switch (functionItem.getId()){
                        case 1:
                            mIntent = new Intent(mContext, ModuleActivity.class);
                            break;
                        case 2:
                            mIntent = new Intent(mContext, ModuleExitAndEntryActivity.class);
                            break;
                        case 3:
                            mIntent = new Intent(mContext, AddAlarmActivity.class);
                            break;
                        case 4:
                            mIntent = new Intent(mContext, AlarmCheckListActivity.class);
                            break;
                    }
                    if (mIntent != null){
                        mContext.startActivity(mIntent);
                    }else {
                        ToastUtil.show(mContext,"功能尚未开放");
                    }

                }
            }
        });

        return view;
    }



    class GridViewAdapter extends BaseAdapter {

        private List<FunctionManageBean.FunctionItem> mDatas;
        private int mItemWidthHeight;
        public GridViewAdapter(List<FunctionManageBean.FunctionItem> datas) {
            this.mDatas = datas;
            mItemWidthHeight = PhoneUtil.getScreenWidth((Activity) mContext) / 4;
        }

        @Override
        public int getCount() { return mDatas.size();}
        @Override
        public Object getItem(int position) { return null;}
        @Override
        public long getItemId(int position) { return 0;}
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(mContext, R.layout.item_function_manage_child, null);
            ImageView iconIV = convertView.findViewById(R.id.item_icon_iv);
            TextView childNameTV = convertView.findViewById(R.id.item_name_tv);
            FrameLayout mainLayout = convertView.findViewById(R.id.item_child_main_layout);
//            TextView redPointHintTV = convertView.findViewById(R.id.tem_unread_red_point_hint_tv);
            TextView unreadCountHintTV = convertView.findViewById(R.id.item_unread_count_hint_tv);

            AbsListView.LayoutParams params = new AbsListView.LayoutParams(mItemWidthHeight, mItemWidthHeight);
            mainLayout.setLayoutParams(params);


            Glide.with(mContext).load(mDatas.get(position).getIcon()).into(iconIV);
            childNameTV.setText(mDatas.get(position).getName()+"");

            int data = mDatas.get(position).getNum();
            if (position == 2 && data != 0){//待我审核：有多少待审批流程就显示气泡数字；
                setPopNum(unreadCountHintTV,data);
            }else {
                unreadCountHintTV.setVisibility(View.GONE);
            }

            return convertView;
        }


        /**
         * 设置菜单右上角气泡
         */
        private void setPopNum(TextView unreadCountHintTV,int data) {
            if (data < 10){
                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(40,40, Gravity.RIGHT | Gravity.TOP);
                params1.setMargins(0,mItemWidthHeight/8,mItemWidthHeight/6,0);
                unreadCountHintTV.setLayoutParams(params1);
                unreadCountHintTV.setBackgroundResource(R.drawable.ic_function_unread_count_img);
            }else if (data < 100){
                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(50,40,Gravity.RIGHT | Gravity.TOP);
                params1.setMargins(0,mItemWidthHeight/8,mItemWidthHeight/8,0);
                unreadCountHintTV.setLayoutParams(params1);
                unreadCountHintTV.setBackgroundResource(R.drawable.ic_function_unread_count_img);
            }else if (data < 1000){
                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(60,40,Gravity.RIGHT | Gravity.TOP);
                params1.setMargins(0,mItemWidthHeight/8,mItemWidthHeight/10,0);
                unreadCountHintTV.setLayoutParams(params1);
                unreadCountHintTV.setBackgroundResource(R.drawable.ic_function_unread_count_img);
            }else {
                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(75,40,Gravity.RIGHT | Gravity.TOP);
                params1.setMargins(0,mItemWidthHeight/8,mItemWidthHeight/10,0);
                unreadCountHintTV.setLayoutParams(params1);
                unreadCountHintTV.setBackgroundResource(R.drawable.ic_function_unread_count_img);
            }
            unreadCountHintTV.setPadding(0,0,0,5);
            unreadCountHintTV.setText(data+"");
            unreadCountHintTV.setVisibility(View.VISIBLE);
        }

    }

}
