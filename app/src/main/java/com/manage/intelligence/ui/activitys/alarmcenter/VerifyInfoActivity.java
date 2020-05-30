package com.manage.intelligence.ui.activitys.alarmcenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.manage.intelligence.R;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.utils.ToastUtil;

/**
*作者:libao
*添加时间:2020/5/30 22:01
*修改人和时间: 2020/5/30 22:01
*说明:验证信息页面
*/
public class VerifyInfoActivity extends BaseActivity implements View.OnClickListener {

    private MainTainStatusBean mainTainStatusBean;//isMainTain 1\2\3  ;  isRepeatMainTain 1/2/3
    private ImageView isMaintainIv;
    private ImageView notMaintainIv;
    private ImageView isRepeatMaintainIv;
    private ImageView notRepeatMaintainIv;
    private LinearLayout isRepeatMaintainLl;

    @Override
    protected String getContentTitle() {
        return "待验证信息";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_info;
    }

    @Override
    public void initView() {

        mainTainStatusBean = new MainTainStatusBean();
        mainTainStatusBean.setIsMainTain(1);
        mainTainStatusBean.setIsRepeatMainTain(1);

        TextView commitTv = findViewById(R.id.commit_tv);

        isMaintainIv = findViewById(R.id.is_maintain_iv);
        notMaintainIv = findViewById(R.id.not_maintain_iv);
        isRepeatMaintainIv = findViewById(R.id.is_repeat_maintain_iv);
        notRepeatMaintainIv = findViewById(R.id.not_repeat_maintain_iv);
        isRepeatMaintainLl = findViewById(R.id.is_repeat_maintain_ll);
        commitTv.setOnClickListener(this);
        isMaintainIv.setOnClickListener(this);
        notMaintainIv.setOnClickListener(this);
        isRepeatMaintainIv.setOnClickListener(this);
        notRepeatMaintainIv.setOnClickListener(this);

        //处理状态
        if (mainTainStatusBean.getIsMainTain() == 1){
            isRepeatMaintainLl.setVisibility(View.GONE);
        }

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ToastUtil.show(VerifyInfoActivity.this,"rating"+String.valueOf(v));
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.commit_tv:
                commit();
                break;

            case R.id.is_maintain_iv:
                mainTainStatusBean.setIsRepeatMainTain(1);
                mainTainStatusBean.setIsMainTain(2);
                isRepeatMaintainLl.setVisibility(View.GONE);
                isMaintainIv.setImageResource(R.mipmap.ic_selected_green);
                notMaintainIv.setImageResource(R.mipmap.ic_selected_normal);
                break;

            case R.id.not_maintain_iv:
                isRepeatMaintainLl.setVisibility(View.VISIBLE);
                mainTainStatusBean.setIsMainTain(3);
                isMaintainIv.setImageResource(R.mipmap.ic_selected_normal);
                notMaintainIv.setImageResource(R.mipmap.ic_selected_green);
                break;

            case R.id.is_repeat_maintain_iv:
                mainTainStatusBean.setIsRepeatMainTain(2);
                isRepeatMaintainIv.setImageResource(R.mipmap.ic_selected_green);
                notRepeatMaintainIv.setImageResource(R.mipmap.ic_selected_normal);
                break;

            case R.id.not_repeat_maintain_iv:
                mainTainStatusBean.setIsRepeatMainTain(3);
                isRepeatMaintainIv.setImageResource(R.mipmap.ic_selected_normal);
                notRepeatMaintainIv.setImageResource(R.mipmap.ic_selected_green);
                break;

        }
    }

    /**
    * 提交
    * */
    private void commit() {


        if (mainTainStatusBean.getIsMainTain() == 1){
            ToastUtil.show(this,"请勾选是否维修");
            return;
        } else if (mainTainStatusBean.getIsMainTain() == 3){
            if (mainTainStatusBean.getIsRepeatMainTain() == 1){
                ToastUtil.show(this,"请勾选是否重新维修");
                return;
            }
        }


        setResult(RESULT_OK);
        finish();

    }


    public class MainTainStatusBean{

        private int isMainTain;
        private int isRepeatMainTain;

        public int getIsMainTain() {
            return isMainTain;
        }

        public void setIsMainTain(int isMainTain) {
            this.isMainTain = isMainTain;
        }

        public int getIsRepeatMainTain() {
            return isRepeatMainTain;
        }

        public void setIsRepeatMainTain(int isRepeatMainTain) {
            this.isRepeatMainTain = isRepeatMainTain;
        }
    }

}
