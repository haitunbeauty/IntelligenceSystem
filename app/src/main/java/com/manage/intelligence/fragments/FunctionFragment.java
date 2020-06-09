package com.manage.intelligence.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.manage.intelligence.R;
import com.manage.intelligence.adapters.FunctionManageAdapter;
import com.manage.intelligence.bean.FunctionManageBean;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


public class FunctionFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;



    private ListView mListView;
    private List<FunctionManageBean> mFunctionList = new ArrayList<>();//
    private FunctionManageAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FunctionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FunctionFragment newInstance(int columnCount) {
        FunctionFragment fragment = new FunctionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_function, container, false);

        mListView = view.findViewById(R.id.function_list_view);
        mAdapter = new FunctionManageAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        requestFunctionData();
        return view;
    }

    /**
    * 请求功能菜单
    * */
    private void requestFunctionData() {

        mFunctionList.clear();
        for (int i=1;i<10;i++){
            ArrayList<FunctionManageBean.FunctionItem> functionItems = new ArrayList<>();
            for (int j=1;j<9;j++){
                FunctionManageBean.FunctionItem functionItem = new FunctionManageBean.FunctionItem();
                functionItem.setIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590304293909&di=2580811645083afc60cb264878d8b7d3&imgtype=0&src=http%3A%2F%2Fs4.sinaimg.cn%2Fmw690%2F002vP0COzy6IWWI1T9N33%26690");
                functionItem.setName("出库管理"+i*j);
                functionItem.setNum(j*1);
                functionItem.setId(j);
                functionItems.add(functionItem);
            }
            FunctionManageBean functionManageBean = new FunctionManageBean();
            functionManageBean.setTitle("模具管理"+i);
            functionManageBean.setFunctionItems(functionItems);
            mFunctionList.add(functionManageBean);
        }

        mAdapter.setData(mFunctionList);
        mAdapter.notifyDataSetChanged();

    }










}
