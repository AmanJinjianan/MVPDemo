package com.qixiang.got.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qixiang.got.R;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CashOutContract;
import com.qixiang.got.presenter.CashOutPresenter;
import com.qixiang.got.presenter.CodingDetailPresenter;
import com.qixiang.got.utils.ToastUtil;
import com.qixiang.got.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class CashOutFragment extends Fragment implements CashOutContract.View {
    View mView;

    public Activity context1;
    public Handler theHandler;
    private CashOutPresenter mt;
    private TextView tvCashOut,tvCashOutConfirm;
    private EditText etCashOutValue;
    public void setTheHandler(Handler handler){
        theHandler = handler;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_cashout,null);
        }
        context1 = getActivity();
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

    private void initView() {
        context1 = getActivity();
        tvCashOut = context1.findViewById(R.id.tv_cashout);
        tvCashOut.setText(ConstantData.userAccountMoney);

        etCashOutValue = context1.findViewById(R.id.et_cashout_value);
        tvCashOutConfirm = context1.findViewById(R.id.tv_cashout_confirm);
        tvCashOutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String balance = etCashOutValue.getText().toString().trim();
                if(TextUtils.isEmpty(balance)){
                    Toast.makeText(getContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Utils.isNumber(balance)){
                    mt.getMsg(balance);
                }else {
                    Toast.makeText(getContext(),"输入非法",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mt = new CashOutPresenter(this);
    }

    private ArrayList<String> data = new ArrayList<>();
    @Override
    public void onResume() {
        //避免重复添加item
        super.onResume();
    }

    @Override
    public void sendToMain(JSONObject jb) {
        ToastUtil.showMessage("申请已经提交");
    }

    @Override
    public void setPresenter(CashOutContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }
}


