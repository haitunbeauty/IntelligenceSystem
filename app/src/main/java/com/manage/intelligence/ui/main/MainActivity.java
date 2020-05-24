package com.manage.intelligence.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.bean.FunctionManageBean;
import com.manage.intelligence.fragments.FunctionFragment;
import com.manage.intelligence.ui.activitys.MeActivity;
import com.manage.intelligence.ui.activitys.modules.ModuleActivity;
import com.manage.intelligence.utils.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FunctionFragment.OnListFragmentInteractionListener {

    private static final int ME_REQUEST = 1;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {


        LinearLayout userLl = findViewById(R.id.user_ll);
        ImageView userIv = findViewById(R.id.user_iv);
        userLl.setVisibility(View.VISIBLE);
        userIv.setVisibility(View.VISIBLE);

        ImageView backIv = findViewById(R.id.back_iv);

        backIv.setOnClickListener(this);
        userIv.setOnClickListener(this);

        initFunctionView();
    }

    private void initFunctionView() {
        FunctionFragment functionFragment = FunctionFragment.newInstance(4);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.placeholder_fl,functionFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;

            case R.id.user_iv:
                mIntent = new Intent(this, MeActivity.class);
                startActivityForResult(mIntent,ME_REQUEST);
                break;
        }

    }

    @Override
    public void onListFragmentInteraction(FunctionManageBean.FunctionItem item) {
        ToastUtil.show(this,item.getName());
        mIntent = new Intent(this,ModuleActivity.class);
        startActivity(mIntent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ME_REQUEST && resultCode == RESULT_OK){
            finish();
        }
    }
}
