package com.manage.intelligence.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.manage.intelligence.R;
import com.manage.intelligence.utils.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        ImageView backIv = findViewById(R.id.back_iv);
        ImageView userIv = findViewById(R.id.user_iv);
        backIv.setOnClickListener(this);
        userIv.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;

            case R.id.user_iv:
                ToastUtil.show(this,"我是个人信息");
                break;
        }

    }
}
