package com.manage.intelligence.ui.activitys.modules;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.manage.intelligence.R;

/**
*作者:libao
*添加时间:2020/5/24 13:38
*修改人和时间: 2020/5/24 13:38
*说明:模具页面
*/
public class ModuleActivity extends AppCompatActivity implements View.OnClickListener, ModuleFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        initView();

    }

    private void initView() {

        ImageView backIv = findViewById(R.id.back_iv);
        backIv.setOnClickListener(this);

        initFunctionView();

    }


    private void initFunctionView() {
        ModuleFragment moduleFragment = ModuleFragment.newInstance("test1", "test2");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.placeholder_fl,moduleFragment)
                .commit();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}