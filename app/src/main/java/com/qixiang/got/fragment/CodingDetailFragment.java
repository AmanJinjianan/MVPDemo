package com.qixiang.got.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.MyViewPager;

import java.util.ArrayList;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class CodingDetailFragment extends Fragment {
    View mView;

    private HorizontalScrollView horizontalScrollView;
    public LinearLayout container;
    public Activity context1;
    public Handler theHandler;
    private MyViewPager myViewPager;
    public CodingDetailFragment(MyViewPager mp){
        myViewPager = mp;
    }
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_codingdetail,null);
        }

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity context) {
        context1 = getActivity();

        super.onAttach(context);
    }

    private ArrayList<String> data = new ArrayList<>();
    @Override
    public void onResume() {
        //避免重复添加item
        super.onResume();
    }

}


