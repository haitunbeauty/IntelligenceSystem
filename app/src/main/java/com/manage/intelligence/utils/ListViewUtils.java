package com.manage.intelligence.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
*作者:libao
*添加时间:2020/5/24 12:41
*修改人和时间: 2020/5/24 12:41
*说明:动态设置 ListView 和 GridView 的高度
*/
public class ListViewUtils {

    public static void setListViewHeightNoHeader(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i,null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.invalidate();
    }




    public static void setGridViewHeight(GridView gridview) {
        ListAdapter listAdapter = gridview.getAdapter();
        if (listAdapter == null) {
            return;
        }
//        int numColumns= gridview.getNumColumns(); // 有多少列
        int numColumns= 4; // 有多少列
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += numColumns) {
            View listItem = listAdapter.getView(i, null, gridview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // 获取gridview的布局参数
        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height = totalHeight;
        gridview.setLayoutParams(params);
        gridview.invalidate();
    }

}
