package com.qixiang.got.fragment;

import android.app.Activity;
import android.content.Intent;
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
import com.qixiang.got.ViewAdapter.MultipleMissionAdapter;
import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.WebActivity;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.MultipleMissionContract;
import com.qixiang.got.model.MultipleMissionInfo;
import com.qixiang.got.model.MultipleMissionInfo;
import com.qixiang.got.presenter.MultipleMissionPresenter;
import com.qixiang.got.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class MultipleMissionlFragment extends Fragment implements MultipleMissionContract.View {
    View mView;

    public Activity context1;
    public Handler theHandler;
    private MyViewPager myViewPager;
    List<MultipleMissionInfo> cList = new ArrayList<MultipleMissionInfo>();

    public MultipleMissionlFragment(MyViewPager mp){
        myViewPager = mp;
    }
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_multiplemission,null);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        },400);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    MultipleMissionAdapter newsAdapter;
    void initView(){
        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context1);
        RecyclerView rv = context1.findViewById(R.id.rv_multiplemission);
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
        //cList.add(new MultipleMissionInfo("微赚22",1,"Nihaohao"));

        //设置适配器
        newsAdapter = new MultipleMissionAdapter(cList,context1);
        newsAdapter.setOnItemClick(new MultipleMissionAdapter.OnItemClickListerner() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "未开发"+position, Toast.LENGTH_SHORT).show();
                if(myViewPager != null && cList != null){
                    if(position < cList.size()){
                        //String d = cList.get(position).getTaskDesc();
                        int h = 4;
                        Intent intent = new Intent(getContext(), WebActivity.class);
                        startActivity(intent);
                    }else
                    {

                    }
                }
            }
        });
        rv.setAdapter(newsAdapter);

        MultipleMissionPresenter mmp = new MultipleMissionPresenter(this);
        mmp.getMsg();
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
    JSONObject jb;
    @Override
    public void sendToMain(JSONObject jb1) {
        jb = jb1;
        context1.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    int rtnCode = jb.getInt("rtnCode");
                    if (ConstantData.rtnCodeOK == rtnCode) {
                        String data = jb.getString("data");
                        JSONObject jsbData = new JSONObject(data);
                        JSONArray jsonArray = jsbData.getJSONArray("beans");

                        for (int i = 0;i<jsonArray.length();i++){
                            jsbData = (JSONObject)jsonArray.get(i);
                            MultipleMissionInfo chf = new MultipleMissionInfo(jsbData.getString("rowId"),jsbData.getString("taskName"),jsbData.getInt("state"),jsbData.getString("taskDesc"));
                            cList.add(chf);
                        }

                        newsAdapter.notifyDataSetChanged();

                    } else {
                        ToastUtil.showMessage("" + jb.getString("rtnMsg"));
                    }
                } catch (Exception e) {
                    ToastUtil.showMessage("" + e.getMessage());
                }
            }
        });

    }

    @Override
    public void setPresenter(MultipleMissionContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }
}


