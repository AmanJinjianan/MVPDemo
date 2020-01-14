package com.qixiang.got.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.CashOutHistroyAdapter;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CashOutHistoryContract;
import com.qixiang.got.model.CashOutHistoryInfo;
import com.qixiang.got.model.CodeMissionBean;
import com.qixiang.got.presenter.CashOutHistoryPresenter;
import com.qixiang.got.utils.ToastUtil;
import com.qixiang.got.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class CashOutHistoryFragment extends Fragment implements CashOutHistoryContract.View {
    View mView;

    public Activity context1;
    public Handler theHandler;
    private CashOutHistoryPresenter mt;
    private TextView tvCashOut,tvCashOutConfirm;
    private EditText etCashOutValue;
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_cashout_history,null);
        }
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context1 = getActivity();
    }

    @Override
    public void onAttach(Activity context) {
        context1 = getActivity();
        super.onAttach(context);
    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //TODO now it's visible to user
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    initView();
                }
            },100);
        } else {
            //TODO now it's invisible to user
            //int gg =5;
        }
    }
    RecyclerView rv;
    private void initView() {
        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context1);
        rv = context1.findViewById(R.id.rv_cashout_history);
        rv.setLayoutManager(layoutManager);
        //默认就是垂直方向的
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        //谷歌提供了一个默认的item删除添加的动画
        rv.setItemAnimator(new DefaultItemAnimator());

        //谷歌提供了一个DividerItemDecoration的实现类来实现分割线
        //往往我们需要自定义分割线的效果，需要自己实现ItemDecoration接口
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context1, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        //当item改变不会重新计算item的宽高
        //调用adapter的增删改差方法的时候就不会重新计算，但是调用nofityDataSetChange的时候还是会
        //所以往往是直接先设置这个为true，当需要布局重新计算宽高的时候才调用nofityDataSetChange
        rv.setHasFixedSize(true);

        //模拟列表数据
//        List newsList = new ArrayList<>();
//        CashOutHistoryInfo news;
//        for (int i = 0; i < 7; i++) {
//            news = new CashOutHistoryInfo();
//            news.money = "打码任务";
//            news.time = "2019-01-17";
//            news.state = "2";
//            newsList.add(news);
//        }
        //设置适配器
//        CashOutHistroyAdapter newsAdapter = new CashOutHistroyAdapter(newsList, context1);
//        rv.setAdapter(newsAdapter);

        CashOutHistoryPresenter chp = new CashOutHistoryPresenter(this);
        chp.getMsg("");
//        newsAdapter.setOnItemClick(new CashOutHistroyAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//
//            }
//        });
    }

    private ArrayList<String> data = new ArrayList<>();
    @Override
    public void onResume() {
        //避免重复添加item
        super.onResume();
    }

    @Override
    public void sendToMain(JSONObject jb) {
        ToastUtil.showMessage("结果已经返回");

        try {
            int rtnCode = jb.getInt("rtnCode");
            if (ConstantData.rtnCodeOK == rtnCode) {
                String data = jb.getString("data");
                JSONObject jsbData = new JSONObject(data);
                JSONArray jsonArray = jsbData.getJSONArray("beans");
                List<CashOutHistoryInfo> cList = new ArrayList<CashOutHistoryInfo>();
                for (int i = 0;i<jsonArray.length();i++){
                    jsbData = (JSONObject)jsonArray.get(i);
                    CashOutHistoryInfo  chf = new CashOutHistoryInfo(jsbData.getString("drawingsBalance"),jsbData.getString("creatTime"),jsbData.getString("state"));
                    cList.add(chf);
                }

                CashOutHistroyAdapter newsAdapter = new CashOutHistroyAdapter(cList, context1);
                if(rv != null)
                    rv.setAdapter(newsAdapter);

//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String dateString = formatter.format(currentTime);

            } else {
                ToastUtil.showMessage("" + jb.getString("rtnMsg"));
            }
        } catch (Exception e) {
            ToastUtil.showMessage("" + e.getMessage());
        }
    }

    @Override
    public void setPresenter(CashOutHistoryContract.Presenter presenter) {

    }

    private String getTimeString(int longTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(longTime);
    }
    @Override
    public void showToast(String msg) {

    }
}


