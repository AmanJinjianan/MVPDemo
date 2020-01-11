package com.qixiang.got.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qixiang.got.R;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.MyContract;
import com.qixiang.got.model.MyFraItemInfo;
import com.qixiang.got.presenter.MyPresenter;
import com.qixiang.got.utils.ToastUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class MyFragment extends Fragment implements MyContract.View {
    View mView;

    private HorizontalScrollView horizontalScrollView;
    public LinearLayout container;
    public Activity context1;
    public Handler theHandler;

    private MyPresenter mt;
    public TextView tvAccount,tvAccountValue,tvAccountRemain;
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_my,null);
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



        ListView lv=(ListView)context1.findViewById(R.id.lv_order);
        List<MyFraItemInfo> data=new ArrayList<MyFraItemInfo>();
        data.add(new MyFraItemInfo(R.drawable.img, "张三1", "18812345678", 3, "18:20", ""));

        OrderAdapter adapter2=new OrderAdapter(data);
        lv.setAdapter(adapter2);

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
        if(mt != null){
            mt.getMsg();
        }
    }

    @Override
    public void sendToMain(JSONObject jb) {
        try {
            int rtnCode = jb.getInt("rtnCode");
            String data = jb.getString("data");
            JSONObject jsbData = new JSONObject(data);
            data = jsbData.getString("bean");
            jsbData = new JSONObject(data);
            if (ConstantData.rtnCodeOK == rtnCode){
                if(tvAccount != null){
                    tvAccountValue.setText(jsbData.getString("holdBalance"));
                }
                if(tvAccountRemain != null){
                    tvAccountRemain.setText(jsbData.getString("auditBalance"));
                }
                //tvAccountValue.setText();
            }else {
                ToastUtil.showMessage(""+jb.getString("rtnMsg"));
            }
        }catch (Exception e){
            ToastUtil.showMessage(""+e.getMessage());
        }
    }

    @Override
    public void setPresenter(MyContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }
}


