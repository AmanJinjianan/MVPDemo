package com.qixiang.got;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.RegisterContract;
import com.qixiang.got.presenter.LoginPresenter;
import com.qixiang.got.utils.ToastUtil;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{

    private EditText etUserName,etPsd;
    private TextView title_menu;
    ObjectAnimator objectAnimator;
    TextView tv;
    private boolean menuFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = (EditText)findViewById(R.id.et_userName);
        etPsd = (EditText)findViewById(R.id.et_psd);
        title_menu = (TextView)findViewById(R.id.title_menu);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    /***
     *
     * 网络请求get
     */
    @Override
    public void gotoMain(JSONObject job) {
        try {
            int rtnCode = job.getInt("rtnCode");
            String msg = job.getString("rtnMsg");
            ToastUtil.showMessage(msg);
            if(rtnCode == 0){

            }
        }catch (Exception e){
            ToastUtil.showMessage(""+e.getMessage());
        }
        Log.e("MVP","gotoMain....");

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        Log.e("MVP","setPresenter....");
    }

    @Override
    public void showToast(String msg) {
        Log.e("MVP","showtost....");
    }
}
