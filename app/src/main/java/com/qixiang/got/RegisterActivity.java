package com.qixiang.got;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qixiang.got.contract.RegisterContract;
import com.qixiang.got.presenter.LoginPresenter;
import com.qixiang.got.presenter.RegisterPresenter;
import com.qixiang.got.utils.ToastUtil;
import com.qixiang.got.view.VerifyCode;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{

    private EditText etUserName,etPsd,reCode;
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
        //reCode = (EditText)findViewById(R.id.re_code);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.ll_verifycode);
        //linearLayout.addView(new VerifyCode(this));
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etUserName.getText().toString().trim()) || TextUtils.isEmpty(etPsd.getText().toString().trim())){
                    ToastUtil.showMessage(getResources().getString(R.string.warn_login));
                    return;
                }else {
                    RegisterPresenter rp = new RegisterPresenter(RegisterActivity.this);
                    rp.register(etUserName.getText().toString().trim(),etPsd.getText().toString().trim());
                }

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
            ToastUtil.showMessage(""+msg);
            if(rtnCode == 0){
                finish();
            }
        }catch (Exception e){
            ToastUtil.showMessage(""+e.getMessage());
        }
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
