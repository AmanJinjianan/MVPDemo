package com.qixiang.got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qixiang.got.presenter.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //initView();
//        findViewById(R.id.ggh).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("jlfalglawjlfj;a");
//            }
//        });
        LoginPresenter dd = new LoginPresenter(this);
        dd.login();
    }


    @Override
    public void gotoMain() {
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
