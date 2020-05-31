package com.manage.intelligence.ui.activitys.maintaincenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.ExperienceLibraryBean;
import com.manage.intelligence.utils.ToastUtil;

import java.io.Serializable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainTainIdeaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainTainIdeaFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final int EXPERIENCE_REQUEST_CODE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainTainIdeaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MainTainIdeaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainTainIdeaFragment newInstance(String param1) {
        MainTainIdeaFragment fragment = new MainTainIdeaFragment();
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
        View view = inflater.inflate(R.layout.fragment_main_tain_idea, container, false);
        TextView selectExperienceLibraryTv = view.findViewById(R.id.select_experience_library_tv);
        selectExperienceLibraryTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){

            case R.id.select_experience_library_tv:
                intent = new Intent(getActivity(),ExperienceLibaryActivity.class);
                break;

        }

        if (intent != null){
            startActivityForResult(intent,EXPERIENCE_REQUEST_CODE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            if (requestCode == EXPERIENCE_REQUEST_CODE){

                ExperienceLibraryBean experience_bean = (ExperienceLibraryBean) data.getSerializableExtra("experience_bean");
                if (experience_bean != null){
                    ToastUtil.show(getActivity(),"经验库回来的数据:"+experience_bean.getDescription());
                }else {
                    ToastUtil.show(getActivity(),"数据不对");
                }


            }

        }
    }
}