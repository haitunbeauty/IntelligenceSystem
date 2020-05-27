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
import com.manage.intelligence.ui.activitys.modules.ModuleExitAndEntryActivity;
import com.manage.intelligence.utils.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        TextView titleTv = findViewById(R.id.title_tv);
        ImageView backIv = findViewById(R.id.back_iv);
        userLl.setVisibility(View.VISIBLE);
        userIv.setVisibility(View.VISIBLE);
        backIv.setOnClickListener(this);
        userIv.setOnClickListener(this);
        titleTv.setText("主页");

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ME_REQUEST && resultCode == RESULT_OK){
            finish();
        }
    }
}
