package com.qixiang.got.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.LanguageAdapter;
import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.model.LanguageInfo;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class LanguageListFragment extends Fragment {
    View mView;
    public Activity context1;
    public Handler theHandler;
    private MyViewPager myViewPager;
    public LanguageListFragment(MyViewPager mp){
        myViewPager = mp;
    }
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_language,null);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        },400);
        return mView;
    }

    void initView(){
        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context1);
        RecyclerView rv = context1.findViewById(R.id.rv_languagelist);
        rv.setLayoutManager(layoutManager);
        //默认就是垂直方向的
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        //谷歌提供了一个默认的item删除添加的动画
        rv.setItemAnimator(new DefaultItemAnimator());

        //谷歌提供了一个DividerItemDecoration的实现类来实现分割线
        //往往我们需要自定义分割线的效果，需要自己实现ItemDecoration接口
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context1,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        //当item改变不会重新计算item的宽高
        //调用adapter的增删改差方法的时候就不会重新计算，但是调用nofityDataSetChange的时候还是会
        //所以往往是直接先设置这个为true，当需要布局重新计算宽高的时候才调用nofityDataSetChange
        rv.setHasFixedSize(true);

        //模拟列表数据
        List newsList = new ArrayList<>();
        LanguageInfo news;
        for (int i = 0; i < 2; i++) {
            news = new LanguageInfo();
            news.title = "中英文录入";
            newsList.add(news);
        }
        //设置适配器
        LanguageAdapter newsAdapter = new LanguageAdapter(newsList,context1);
        newsAdapter.setOnItemClick(new LanguageAdapter.OnItemClickListerner() {
            @Override
            public void OnItemClick(int position) {
                //Toast.makeText(getContext(), "position:7777"+position, Toast.LENGTH_SHORT).show();
                if(myViewPager != null){
                    switch (position){
                        case 0:myViewPager.setCurrentItem(3);break;
                    }
                }
            }
        });

        rv.setAdapter(newsAdapter);
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
    @Override
    public void onResume() {
        //避免重复添加item
        super.onResume();
    }

}


