package com.qixiang.got.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.MainAdapter;
import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.model.MainFraInfo;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class MainFragment extends Fragment {
    View mView;

    private HorizontalScrollView horizontalScrollView;
    public LinearLayout container;
    public Activity context1;
    public Handler theHandler;

    private MyViewPager myViewPager;
    public MainFragment(MyViewPager mp){
        myViewPager = mp;
    }
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_main,null);
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
        RecyclerView rv = context1.findViewById(R.id.rv_main11);
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
        MainFraInfo news;
        for (int i = 1; i < 10; i++) {
            news = new MainFraInfo();
            news.title = "打码任务";
            news.source = "腾讯新闻" ;
            news.time = "2019-01-17";
            newsList.add(news);
        }
        //设置适配器
        MainAdapter newsAdapter = new MainAdapter(newsList,context1);
        newsAdapter.setOnItemClick(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "position555:"+position, Toast.LENGTH_SHORT).show();
                if(myViewPager != null){
                   switch (position){
                       case 0:myViewPager.setCurrentItem(2);break;
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

    private String cities[] = new String[]{"London", "Bangkok", "Paris", "Dubai", "Istanbul", "New York",
            "Singapore", "Kuala Lumpur", "Hong Kong", "Tokyo", "Barcelona",
            "Vienna", "Los Angeles", "Prague", "Rome", "Seoul", "Mumbai", "Jakarta",
            "Berlin", "Beijing", "Moscow", "Taipei", "Dublin", "Vancouver"};
    private ArrayList<String> data = new ArrayList<>();
    @Override
    public void onResume() {


        //避免重复添加item


        super.onResume();
    }

}


