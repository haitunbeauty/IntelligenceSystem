package com.manage.intelligence.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.manage.intelligence.R;

public abstract class BaseActivity extends AppCompatActivity {

    private View rootView;
    private View content;
    private ImageView mBack;
    private TextView mTitle;
    private FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContent();
        setContentView(rootView);
        initView();
    }

    private void addContent() {
        rootView = View.inflate(this, R.layout.activity_base, null);
        content = View.inflate(this, getLayoutId(), null);
        mBack = (ImageView) rootView.findViewById(R.id.back_iv);
        mTitle = (TextView) rootView.findViewById(R.id.title_tv);
        flContent = (FrameLayout) rootView.findViewById(R.id.base_fl);
        mTitle.setText(getContentTitle() == null ? "" : getContentTitle());
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(content,params);
        }
    }


    /**
     * title赋值
     * @return
     */
    protected abstract String getContentTitle();

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    public abstract void initView();


}
