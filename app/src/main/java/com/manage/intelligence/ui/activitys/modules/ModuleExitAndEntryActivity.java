package com.manage.intelligence.ui.activitys.modules;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ijovo.customtabbar_lib.SegmentTabLayout;
import com.ijovo.customtabbar_lib.listener.OnTabSelectListener;
import com.ijovo.customtabbar_lib.utils.ViewFindUtils;
import com.manage.intelligence.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
*作者:libao
*添加时间:2020/5/24 13:38
*修改人和时间: 2020/5/24 13:38
*说明:模具出入库页面
*/
public class ModuleExitAndEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"入库", "出库"};

    private View mDecorView;
    private SegmentTabLayout tabLayout_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        mFragments.add(ModuleFragmentThree.newInstance(mTitles[0]));
        mFragments.add(ModuleFragmentFore.newInstance(mTitles[1]));
        initView();

    }

    private void initView() {

        ImageView backIv = findViewById(R.id.back_iv);
        backIv.setOnClickListener(this);
        mDecorView = getWindow().getDecorView();
        tabLayout_1 = ViewFindUtils.find(mDecorView, R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
        tl_3();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_iv:
                finish();
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