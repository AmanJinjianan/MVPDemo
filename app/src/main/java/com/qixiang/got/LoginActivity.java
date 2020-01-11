package com.qixiang.got;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.LoginContract;
import com.qixiang.got.presenter.LoginPresenter;
import com.qixiang.got.utils.ToastUtil;

import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.qixiang.got.utils.httputils.HttpUtils2.sendMsgGet;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private EditText etUserName,etPsd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = (EditText)findViewById(R.id.et_userName);
        etPsd = (EditText)findViewById(R.id.et_psd);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();
            if(TextUtils.isEmpty(etUserName.getText().toString().trim()) || TextUtils.isEmpty(etPsd.getText().toString().trim())){
               ToastUtil.showMessage(getResources().getString(R.string.warn_login));
               return;
            }else {
                LoginPresenter dd = new LoginPresenter(LoginActivity.this);
                dd.login(etUserName.getText().toString().trim(),etPsd.getText().toString().trim());
            }

            }
        });
        //initView();
//        findViewById(R.id.ggh).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("jlfalglawjlfj;a");
//            }
//        });

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
                ConstantData.userAccount = etUserName.getText().toString().trim();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){
            ToastUtil.showMessage(""+e.getMessage());
        }
        Log.e("MVP","gotoMain....");


    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        Log.e("MVP","setPresenter....");
    }

    @Override
    public void showToast(String msg) {
        Log.e("MVP","showtost....");
    }
}
