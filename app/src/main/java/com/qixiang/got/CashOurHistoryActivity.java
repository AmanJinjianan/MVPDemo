package com.qixiang.got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qixiang.got.ViewAdapter.CashOutHistroyAdapter;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CashOutHistoryContract;
import com.qixiang.got.model.CashOutHistoryInfo;
import com.qixiang.got.presenter.CashOutHistoryPresenter;
import com.qixiang.got.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CashOurHistoryActivity extends AppCompatActivity implements CashOutHistoryContract.View, View.OnClickListener {

    private Activity context1;
    private CashOutHistroyAdapter newsAdapter;
    List<CashOutHistoryInfo> cList = new ArrayList<CashOutHistoryInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_our_history);
        context1 = this;

        initView();
    }

    RecyclerView rv;
    Button btn_cashout_back;
    private TextView tvMission,tvMy;
    private void initView() {

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

        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context1);
        rv = context1.findViewById(R.id.rv_cashout_history22);
        rv.setLayoutManager(layoutManager);
        //默认就是垂直方向的
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        //谷歌提供了一个默认的item删除添加的动画
        rv.setItemAnimator(new DefaultItemAnimator());

        //谷歌提供了一个DividerItemDecoration的实现类来实现分割线
        //往往我们需要自定义分割线的效果，需要自己实现ItemDecoration接口
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context1, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
        newsAdapter = new CashOutHistroyAdapter(cList, context1);
        if(rv != null){
            rv.setAdapter(newsAdapter);
        }
        //当item改变不会重新计算item的宽高
        //调用adapter的增删改差方法的时候就不会重新计算，但是调用nofityDataSetChange的时候还是会
        //所以往往是直接先设置这个为true，当需要布局重新计算宽高的时候才调用nofityDataSetChange
        //rv.setHasFixedSize(true);

        CashOutHistoryPresenter chp = new CashOutHistoryPresenter(this);
        chp.getMsg("");
    }
    JSONObject jb;
    @Override
    public void sendToMain(JSONObject jb1) {
        ToastUtil.showMessage("结果已经返回");
        jb = jb1;
        runOnUiThread(new Runnable() {
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
                            CashOutHistoryInfo  chf = new CashOutHistoryInfo(jsbData.getString("drawingsBalance"),jsbData.getString("creatTime"),jsbData.getString("state"));
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
    public void setPresenter(CashOutHistoryContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_rootbar_mission:
                //mViewpager.setCurrentItem(0);
                break;
            case R.id.btn_rootbar_my:
                finish();
                //mViewpager.setCurrentItem(111);
                break;
        }
    }
}
