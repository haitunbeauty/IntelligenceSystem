package com.manage.intelligence.ui.activitys.modules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.manage.intelligence.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ModuleFragmentThree#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModuleFragmentThree extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";

    private String mParam;

    public ModuleFragmentThree() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter
     * @return A new instance of fragment ModuleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModuleFragmentThree newInstance(String param) {
        ModuleFragmentThree fragment = new ModuleFragmentThree();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_module, container, false);
        Button twoBtn = view.findViewById(R.id.two_btn);
        twoBtn.setText("入库");


        return view;
    }



}
