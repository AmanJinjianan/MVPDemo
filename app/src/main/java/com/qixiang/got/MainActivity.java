package com.qixiang.got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.fragment.CodingDetailFragment;
import com.qixiang.got.fragment.LanguageListFragment;
import com.qixiang.got.fragment.MainFragment;
import com.qixiang.got.ViewAdapter.ViewPagerFragmentAdapter;
import com.qixiang.got.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager mFragmentManager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    MyViewPager mViewpager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    private TextView tvMission,tvMy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_rootbar_mission).setOnClickListener(this);
        findViewById(R.id.btn_rootbar_my).setOnClickListener(this);

        tvMission = (TextView)findViewById(R.id.btn_rootbar_mission);
        tvMy = (TextView) findViewById(R.id.btn_rootbar_my);
        TextView[] rb = new TextView[2];
        rb[0] = tvMission;
        rb[1] = tvMy;
        for(TextView r:rb) {
            Drawable[] drawables = r.getCompoundDrawables();
            Rect rect = new Rect(0, 0, (int)(drawables[1].getMinimumWidth() / 2.5), (int)(drawables[1].getMinimumHeight() / 2.5));
            drawables[1].setBounds(rect);
            r.setCompoundDrawables(null, drawables[1], null, null);
        }

        mFragmentManager = getSupportFragmentManager();
        mViewpager = (MyViewPager) findViewById(R.id.ViewPagerLayout);
        MainFragment ss = new MainFragment(mViewpager);
        mFragmentList.add(ss);
        MyFragment mm = new MyFragment();
        mFragmentList.add(mm);
        LanguageListFragment lf = new LanguageListFragment(mViewpager);
        mFragmentList.add(lf);
        CodingDetailFragment cf = new CodingDetailFragment(mViewpager);
        mFragmentList.add(cf);

        mViewPagerFragmentAdapter =   new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        mViewpager.setScrollable(false);
        //mViewpager.addOnPageChangeListener(new ViewPagerOnPagerChangedLisenter());
        mViewpager.setAdapter(mViewPagerFragmentAdapter);
        mViewpager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_rootbar_mission: mViewpager.setCurrentItem(0);break;
            case R.id.btn_rootbar_my:mViewpager.setCurrentItem(1); break;
        }
    }
}
