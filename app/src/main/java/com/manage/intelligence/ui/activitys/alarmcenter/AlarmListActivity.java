package com.manage.intelligence.ui.activitys.alarmcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.ViewFindUtils;
import com.manage.intelligence.R;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.ui.activitys.modules.ModuleExitAndEntryActivity;
import com.manage.intelligence.ui.activitys.modules.ModuleFragmentFore;
import com.manage.intelligence.ui.activitys.modules.ModuleFragmentThree;
import com.manage.intelligence.utils.ToastUtil;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
*作者:libao
*添加时间:2020/5/30 12:03
*修改人和时间: 2020/5/30 12:03
*说明:报警列表页面
*/
public class AlarmListActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"驳回列表", "我的请求"};

    private View mDecorView;
    private SegmentTabLayout tabLayout_1;


    @Override
    protected String getContentTitle() {
        return "报警列表";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alarm_list;
    }

    @Override
    public void initView() {

        mFragments.add(AlarmRejectListFragment.newInstance(mTitles[0]));
        mFragments.add(AlarmRequestListFragment.newInstance(mTitles[1]));

        TextView commonSearchTv = findViewById(R.id.common_search_tv);
        EditText commonSearchEt = findViewById(R.id.common_search_et);
        commonSearchEt.setHint("模具号/设备号");
        commonSearchTv.setVisibility(View.VISIBLE);
        commonSearchTv.setOnClickListener(this);


        mDecorView = getWindow().getDecorView();
        tabLayout_1 = ViewFindUtils.find(mDecorView, R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
        tl_3();

    }

    private void tl_3() {
        final ViewPager viewPager = ViewFindUtils.find(mDecorView, R.id.vp);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabLayout_1.setTabData(mTitles);
        tabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                tabLayout_1.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.common_search_tv:
                ToastUtil.show(this,"搜索");
                break;

        }

    }
}
