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
import com.manage.intelligence.adapters.AlarmRequestListAdapter;
import com.manage.intelligence.bean.AlarmRejectListBean;
import com.manage.intelligence.bean.AlarmRequestListBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmRequestListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmRequestListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private AlarmRequestListAdapter alarmRequestListAdapter;
    private ArrayList<AlarmRequestListBean> mAlarmRequestListBeans = new ArrayList<>();


    public AlarmRequestListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AlarmRequestListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmRequestListFragment newInstance(String param1) {
        AlarmRequestListFragment fragment = new AlarmRequestListFragment();
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


        View view = inflater.inflate(R.layout.fragment_alarm_request_list, container, false);

        ListView alarmRejectLv = view.findViewById(R.id.alarm_request_lv);
        alarmRequestListAdapter = new AlarmRequestListAdapter(getActivity());
        mAlarmRequestListBeans.clear();
        for (int i=0;i<5;i++){
            AlarmRequestListBean alarmRequestListBean = new AlarmRequestListBean();
            alarmRequestListBean.setNumOne("request一"+i);
            alarmRequestListBean.setNumTwo("request二"+i);
            alarmRequestListBean.setNumThree("request三"+i);
            mAlarmRequestListBeans.add(alarmRequestListBean);
        }
        alarmRequestListAdapter.setData(mAlarmRequestListBeans);
        alarmRejectLv.setAdapter(alarmRequestListAdapter);
        return view;
    }

    //搜索
    public void search(String content) {

        if (!TextUtils.isEmpty(content)){
            if (mAlarmRequestListBeans.size()>0){
                ArrayList<AlarmRequestListBean> alarmRequestListBeans = new ArrayList<>();
                for (int i=0;i<mAlarmRequestListBeans.size();i++){
                    if (mAlarmRequestListBeans.get(i).getNumOne().contains(content)){
                        alarmRequestListBeans.add(mAlarmRequestListBeans.get(i));
                    }
                }
                if (alarmRequestListBeans.size()>0){
                    alarmRequestListAdapter.setData(alarmRequestListBeans);
                    alarmRequestListAdapter.notifyDataSetChanged();
                }
            }
        }else {
            if (mAlarmRequestListBeans.size()>0){
                alarmRequestListAdapter.setData(mAlarmRequestListBeans);
                alarmRequestListAdapter.notifyDataSetChanged();
            }
        }
    }
}
