package com.manage.intelligence.ui.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.base.MyApplication;
import com.manage.intelligence.utils.ToastUtil;

import org.w3c.dom.Text;

/**
*作者:libao
*添加时间:2020/5/24 12:57
*修改人和时间: 2020/5/24 12:57
*说明: 个人中心
*/
public class MeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();

    }

    private void initView() {

        TextView title = findViewById(R.id.title_tv);
        ImageView backIv = findViewById(R.id.back_iv);
        TextView exit = findViewById(R.id.exit_tv);
        title.setText(R.string.activity_me);
        backIv.setOnClickListener(this);
        exit.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;

            case R.id.exit_tv:
                MyApplication.getInstance().getAppDB().userDao().deleteAll();
                setResult(RESULT_OK);
                finish();
                break;

        }

    }


}
