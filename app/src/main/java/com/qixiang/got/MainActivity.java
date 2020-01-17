package com.qixiang.got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.fragment.CashOutFragment;
import com.qixiang.got.fragment.CashOutHistoryFragment;
import com.qixiang.got.fragment.CodingDetailFragment;
import com.qixiang.got.fragment.CodingDetailFragment2;
import com.qixiang.got.fragment.EmptyFragment;
import com.qixiang.got.fragment.ImageUploadFragment;
import com.qixiang.got.fragment.LanguageListFragment;
import com.qixiang.got.fragment.MainFragment;
import com.qixiang.got.ViewAdapter.ViewPagerFragmentAdapter;
import com.qixiang.got.fragment.MultipleMissionlFragment;
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
        findViewById(R.id.title_right).setOnClickListener(this);

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
        MyFragment mm = new MyFragment(mViewpager);
        mFragmentList.add(mm);
        LanguageListFragment lf = new LanguageListFragment(mViewpager);
        mFragmentList.add(lf);
        CodingDetailFragment cf = new CodingDetailFragment(mViewpager);
        mFragmentList.add(cf);
        CodingDetailFragment2 cf2 = new CodingDetailFragment2(mViewpager);
        mFragmentList.add(cf2);
        MultipleMissionlFragment mmf = new MultipleMissionlFragment(mViewpager);
        mFragmentList.add(mmf);
        CashOutFragment cof = new CashOutFragment();
        mFragmentList.add(cof);
        ImageUploadFragment ef22 = new ImageUploadFragment(mViewpager);
        mFragmentList.add(ef22);
        CashOutHistoryFragment cohf = new CashOutHistoryFragment();
        mFragmentList.add(cohf);
        EmptyFragment ef = new EmptyFragment(mViewpager);
        mFragmentList.add(ef);

        mViewPagerFragmentAdapter =   new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        mViewpager.setScrollable(false);
        //mViewpager.addOnPageChangeListener(new ViewPagerOnPagerChangedLisenter());
        mViewpager.setAdapter(mViewPagerFragmentAdapter);
        mViewpager.setCurrentItem(0);
        checkPermission();
    }
    ArrayList<String> unPermissionList;
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermission = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };
            unPermissionList = new ArrayList<String>();
            try {
                for (int i = 0; i < mPermission.length; i++) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, mPermission[i]) != PackageManager.PERMISSION_GRANTED) {
                        unPermissionList.add(mPermission[i]);
                    }
                }
            } catch (Exception e) {
            }

        }else {
            return;
        }
        if (unPermissionList.isEmpty()) {
            //Toast.makeText(BleConnectActivity.this, "Init.............:", Toast.LENGTH_LONG).show();
            //都授权了。。。
        }else {
            try {
                String[] permissionStrings = unPermissionList.toArray(new String[unPermissionList.size()]);
                ActivityCompat.requestPermissions(this, permissionStrings, 1);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Exception11:"+e.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_rootbar_mission:
                mViewpager.setCurrentItem(0);
                break;
            case R.id.btn_rootbar_my:
                mViewpager.setCurrentItem(1);
                break;
            case R.id.title_right:
                mViewpager.setCurrentItem(1);
                break;
        }
    }
}
