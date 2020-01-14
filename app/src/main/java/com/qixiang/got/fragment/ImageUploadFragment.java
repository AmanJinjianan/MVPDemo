package com.qixiang.got.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.ImageUploadContract;
import com.qixiang.got.presenter.ImageUploadPresenter;
import com.qixiang.got.presenter.MyPresenter;
import com.qixiang.got.utils.ToastUtil;
import com.qixiang.got.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class ImageUploadFragment extends Fragment implements ImageUploadContract.View {
    View mView;

    public Activity context1;
    public Handler theHandler;
    private ImageUploadPresenter mt;
    private Button btnImagePick, btnImageStartUp;
    private ImageView ivMySelect;

    private MyViewPager myViewPager;

    public ImageUploadFragment(MyViewPager mp) {
        myViewPager = mp;
    }

    public void setTheHandler(Handler handler) {
        theHandler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_imge_upload, null);
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
            }, 300);
        } else {
            //TODO now it's invisible to user
            //int gg =5;
        }
    }

    private void initView() {
        mt = new ImageUploadPresenter(this);

        ivMySelect = context1.findViewById(R.id.iv_myselect);
        btnImagePick = context1.findViewById(R.id.btn_image_pick);
        btnImagePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicFromP();
            }
        });
        btnImageStartUp = context1.findViewById(R.id.btn_start_uploading);
        btnImageStartUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mt.getMsg("");
            }
        });
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
    public void setPresenter(ImageUploadContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }

    private void getPicFromP() {
        //通过intent去找到本机中所有属性为image的文件；
        Intent intent = new Intent();
        intent.setType("image/*");
        //ACTION_GET_CONTENT 允许用户选择特殊种类的数据，并返回，ACTION_Pick
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取到的图片为uri格式，imageView控件可以设置uri
        Uri uri = data.getData();
        Log.e("onActivityResult: ", String.valueOf(uri));
        if (ivMySelect != null)
            ivMySelect.setImageURI(uri);
    }
}
