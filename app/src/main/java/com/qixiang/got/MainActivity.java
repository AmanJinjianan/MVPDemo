package com.qixiang.got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qixiang.got.Fragment.MainFragment;
import com.qixiang.got.Utils.DeviceInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    ViewPager mViewpager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    private TextView tvMission,tvMy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        tvMission = (TextView)findViewById(R.id.btn_rootbar_mission);
        tvMy = (TextView) findViewById(R.id.btn_rootbar_my);
//        Drawable drawableMy = getResources().getDrawable(R.drawable.ic_launcher_background);
//        drawableMy.setBounds(10,10,10,10);
//        tvMy.setCompoundDrawables(null,drawableMy,null,null);

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
        MainFragment ss = new MainFragment();

        mFragmentList.add(ss);
        mViewPagerFragmentAdapter =   new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        mViewpager = (ViewPager) findViewById(R.id.ViewPagerLayout);

//        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,DeviceInfoUtils.getDeviceInfo().getScreenHeight()-860);
//        mViewpager.setLayoutParams(ll);

        //mViewpager.addOnPageChangeListener(new ViewPagerOnPagerChangedLisenter());
        mViewpager.setAdapter(mViewPagerFragmentAdapter);
        mViewpager.setCurrentItem(0);
    }
}
