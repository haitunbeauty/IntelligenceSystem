package com.manage.intelligence.ui.activitys.alarmcenter;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.manage.intelligence.R;
import com.manage.intelligence.adapters.AlarmRejectListAdapter;
import com.manage.intelligence.bean.AlarmRejectListBean;
import com.manage.intelligence.bean.AlarmRequestListBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmRejectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmRejectListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private AlarmRejectListAdapter alarmRejectListAdapter;
    private ArrayList<AlarmRejectListBean> mAlarmRejectListBeans;


    public AlarmRejectListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AlarmRejectListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmRejectListFragment newInstance(String param1) {
        AlarmRejectListFragment fragment = new AlarmRejectListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm_reject_list, container, false);

        ListView alarmRejectLv = view.findViewById(R.id.alarm_reject_lv);
        alarmRejectListAdapter = new AlarmRejectListAdapter(getActivity());
        mAlarmRejectListBeans = new ArrayList<>();
        for (int i=0;i<5;i++){
            AlarmRejectListBean alarmRejectListBean = new AlarmRejectListBean();
            alarmRejectListBean.setNumOne("第一"+i);
            alarmRejectListBean.setNumTwo("第二"+i);
            alarmRejectListBean.setNumThree("第三"+i);
            mAlarmRejectListBeans.add(alarmRejectListBean);
        }
        alarmRejectListAdapter.setData(mAlarmRejectListBeans);
        alarmRejectLv.setAdapter(alarmRejectListAdapter);

        return view;
    }

    //搜索
    public void search(String content) {

        if (!TextUtils.isEmpty(content)){
            if (mAlarmRejectListBeans.size()>0){
                ArrayList<AlarmRejectListBean> alarmRejectListBeans = new ArrayList<>();
                for (int i=0;i<mAlarmRejectListBeans.size();i++){
                    if (mAlarmRejectListBeans.get(i).getNumOne().contains(content)){
                        alarmRejectListBeans.add(mAlarmRejectListBeans.get(i));
                    }
                }
                if (alarmRejectListBeans.size()>0){
                    alarmRejectListAdapter.setData(alarmRejectListBeans);
                    alarmRejectListAdapter.notifyDataSetChanged();
                }
            }
        }else {
            if (mAlarmRejectListBeans.size()>0){
                alarmRejectListAdapter.setData(mAlarmRejectListBeans);
                alarmRejectListAdapter.notifyDataSetChanged();
            }
        }

    }
}
