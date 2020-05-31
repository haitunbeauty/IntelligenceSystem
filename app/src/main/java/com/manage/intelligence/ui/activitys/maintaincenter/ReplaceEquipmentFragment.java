package com.manage.intelligence.ui.activitys.maintaincenter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.manage.intelligence.R;
import com.manage.intelligence.adapters.MainTainInfoAdapter;
import com.manage.intelligence.utils.ToastUtil;

/**
*作者:libao
*添加时间:2020/5/31 23:33
*修改人和时间: 2020/5/31 23:33
*说明:更换设备
*/
public class ReplaceEquipmentFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public ReplaceEquipmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ReplaceEquipmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReplaceEquipmentFragment newInstance(String param1) {
        ReplaceEquipmentFragment fragment = new ReplaceEquipmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_replace_equipment, container, false);

        ListView maintainInfoLv = view.findViewById(R.id.maintain_info_lv);
        MainTainInfoAdapter mainTainInfoAdapter = new MainTainInfoAdapter(getActivity());
        mainTainInfoAdapter.setData();
        maintainInfoLv.setAdapter(mainTainInfoAdapter);

        Button maintainBtn = view.findViewById(R.id.maintain_btn);
        Button replaceBtn = view.findViewById(R.id.replace_btn);
        maintainBtn.setOnClickListener(this);
        replaceBtn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.maintain_btn:
                ToastUtil.show(getActivity(),"保养");
                break;

            case R.id.replace_btn:
                ToastUtil.show(getActivity(),"更换");
                break;
        }

    }
}