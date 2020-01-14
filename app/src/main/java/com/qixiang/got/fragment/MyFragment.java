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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.CashOurHistoryActivity;
import com.qixiang.got.LoginActivity;
import com.qixiang.got.MainActivity;
import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.MyFraItemAdapter;
import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.MyContract;
import com.qixiang.got.model.MyFraItemInfo;
import com.qixiang.got.presenter.MyPresenter;
import com.qixiang.got.utils.ToastUtil;
import com.qixiang.got.utils.httputils.module.Constant;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class MyFragment extends Fragment implements MyContract.View {
    View mView;

    public Activity context1;
    public Handler theHandler;

    private MyPresenter mt;
    public TextView tvAccount, tvAccountValue, tvAccountRemain;
    private MyViewPager myViewPager;

    public MyFragment(MyViewPager mp) {
        myViewPager = mp;
    }

    public void setTheHandler(Handler handler) {
        theHandler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_my, null);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        }, 300);
        return mView;
    }

    private void initView() {
        tvAccount = context1.findViewById(R.id.tv_my_account);

        tvAccountValue = context1.findViewById(R.id.tv_account);
        tvAccountRemain = context1.findViewById(R.id.tv_account_remain);
        tvAccount.setText(ConstantData.userAccount);


        //LinearLayoutManager是用来做列表布局，也就是单列的列表
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context1);
        RecyclerView rv = context1.findViewById(R.id.rv_my_fra_item);
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
        List newsList = new ArrayList<>();
        MyFraItemInfo news1 = new MyFraItemInfo("官方公告", R.drawable.ic_advting);
        newsList.add(news1);
        MyFraItemInfo news2 = new MyFraItemInfo("我的分享任务", R.drawable.ic_advting);
        newsList.add(news2);
        MyFraItemInfo news3 = new MyFraItemInfo("我的打字任务", R.drawable.ic_advting);
        newsList.add(news3);
        MyFraItemInfo news4 = new MyFraItemInfo("余额提现", R.drawable.ic_advting);
        newsList.add(news4);
        MyFraItemInfo news5 = new MyFraItemInfo("微信收款码", R.drawable.ic_advting);
        newsList.add(news5);
        MyFraItemInfo news6 = new MyFraItemInfo("提现记录", R.drawable.ic_cashout);
        newsList.add(news6);
        MyFraItemInfo news7 = new MyFraItemInfo("退出登录", R.drawable.ic_logout);
        newsList.add(news7);

        //设置适配器
        MyFraItemAdapter newsAdapter = new MyFraItemAdapter(newsList, context1);
        newsAdapter.setOnItemClick(new MyFraItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //int kk  = 00;
                switch (position) {
                    case 3:
                        myViewPager.setCurrentItem(6);
                        break;
                    case 4:
                        myViewPager.setCurrentItem(7);
                        break;
                    case 5:
                        Intent intent1 = new Intent(getActivity(), CashOurHistoryActivity.class);
                        startActivity(intent1);
                        break;
                    case 6:
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    default:
                        myViewPager.setCurrentItem(50);
                        break;
                }
                //Toast.makeText(getContext(), "position555:" + myViewPager.getChildCount(), Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(newsAdapter);

        mt = new MyPresenter(this);
        mt.getMsg();
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
        if (mt != null) {
            mt.getMsg();
        }
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
                        data = jsbData.getString("bean");
                        jsbData = new JSONObject(data);
                        if (tvAccount != null) {
                            tvAccountValue.setText(jsbData.getString("holdBalance"));
                            ConstantData.userAccountMoney = jsbData.getString("holdBalance");
                        }
                        if (tvAccountRemain != null) {
                            tvAccountRemain.setText(jsbData.getString("auditBalance"));
                        }
                        //tvAccountValue.setText();
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
    public void setPresenter(MyContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }
}


