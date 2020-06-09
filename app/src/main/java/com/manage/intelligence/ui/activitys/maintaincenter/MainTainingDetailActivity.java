package com.manage.intelligence.ui.activitys.maintaincenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TextView;

import com.ijovo.customtabbar_lib.SegmentTabLayout;
import com.ijovo.customtabbar_lib.listener.OnTabSelectListener;
import com.ijovo.customtabbar_lib.utils.ViewFindUtils;
import com.manage.intelligence.R;
import com.manage.intelligence.base.BaseActivity;
import com.manage.intelligence.utils.ToastUtil;

import java.util.ArrayList;

/**
*作者:libao
*添加时间:2020/5/31 20:05
*修改人和时间: 2020/5/31 20:05
*说明:维修单详情页面(维修中)
*/
public class MainTainingDetailActivity extends BaseActivity implements View.OnClickListener {


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"报警信息", "维修意见","更换设备", "处理过程"};

    private View mDecorView;
    private SegmentTabLayout tabLayout_1;


    @Override
    protected String getContentTitle() {
        return "维修单详情";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_taining_detail;
    }

    @Override
    public void initView() {


        mFragments.add(AlarmInfoFragment.newInstance(mTitles[0]));
        mFragments.add(MainTainIdeaFragment.newInstance(mTitles[1]));
        mFragments.add(ReplaceEquipmentFragment.newInstance(mTitles[1]));
        mFragments.add(TreatingProcessesFragment.newInstance(mTitles[1]));
        mDecorView = getWindow().getDecorView();
        tabLayout_1 = ViewFindUtils.find(mDecorView, R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
        tl_3();

        //
        TextView textView = findViewById(R.id.commit_tv);
        textView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.commit_tv:
                ToastUtil.show(this,"提交");
                break;

        }

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

}