package com.qixiang.got.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qixiang.got.R;
import com.qixiang.got.ViewAdapter.MyViewPager;
import com.qixiang.got.constant.ConstantData;
import com.qixiang.got.contract.CodingDetailContract;
import com.qixiang.got.model.CodeMissionBean;
import com.qixiang.got.presenter.CodingDetailPresenter;
import com.qixiang.got.utils.ToastUtil;

import org.json.JSONObject;

import java.util.ArrayList;

//import android.app.Fragment;

/**
 * Created by Administrator on 2018/7/19.
 */

public class CodingDetailFragment extends Fragment implements CodingDetailContract.View {
    View mView;

    private CodingDetailPresenter mt;
    private HorizontalScrollView horizontalScrollView;
    public LinearLayout container;
    public Activity context1;
    public Handler theHandler;
    private MyViewPager myViewPager;

    private Button btnNextMission;
    private TextView tvContent;
    private TextView tvRight,tvYours,tv_per_money;
    private EditText etContent;
    private LinearLayout llResult1,llResult2,llThreeBtn;
    private SpannableString mSpannableString;
    private CodeMissionBean cmb;
    public CodingDetailFragment(MyViewPager mp) {
        myViewPager = mp;
    }

    public void setTheHandler(Handler handler) {
        theHandler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_codingdetail, null);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //initView();
            }
        }, 300);
        return mView;
    }

    private void initView() {
        if (context1 != null) {
            tvContent = (TextView) context1.findViewById(R.id.tv_codingcontent);
            etContent = (EditText) context1.findViewById(R.id.et_coding_content);
            etContent.setVisibility(View.VISIBLE);
            llThreeBtn = context1.findViewById(R.id.ll_coding_three_btn);
            llThreeBtn.setVisibility(View.VISIBLE);

            tvYours = (TextView) context1.findViewById(R.id.tv_coding_you_result);
            tvRight = (TextView) context1.findViewById(R.id.tv_coding_right_result);

            tv_per_money = (TextView) context1.findViewById(R.id.tv_per_money);

            llResult1 = context1.findViewById(R.id.ll_coding_detail_result1);
            llResult1.setVisibility(View.GONE);
            llResult2 = context1.findViewById(R.id.ll_coding_detail_result2);
            llResult2.setVisibility(View.GONE);

            btnNextMission = (Button) context1.findViewById(R.id.btn_next_mission);
            btnNextMission.setText(getResources().getString(R.string.coding_next_mission));
            btnNextMission.setVisibility(View.GONE);

            context1.findViewById(R.id.btn_code_commit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tvContent != null && etContent != null){
                        if(etContent.getText().toString().trim().equals(""))
                        {
                            ToastUtil.showMessage("输入不可为空");
                            return;
                        }
                        String state = "1";
                        //检验失败
                        if(!tvContent.getText().toString().equals(etContent.getText().toString())){
                            llResult1.setVisibility(View.VISIBLE);
                            llResult2.setVisibility(View.VISIBLE);
                            tvYours.setText(etContent.getText().toString());
                            btnNextMission.setText(getResources().getString(R.string.coding_next_mission2));
                            btnNextMission.setBackgroundColor(getResources().getColor(R.color.color_white));
                            state = "4";
                        }else {
                            //校验成功
                            etContent.setVisibility(View.GONE);
                            btnNextMission.setText(getResources().getString(R.string.coding_next_mission));
                            btnNextMission.setBackgroundColor(getResources().getColor(R.color.color_btn_next));
                            state = "3";
                        }
                        if(cmb != null){
                            mt.commitResult(cmb.getRowId(),cmb.getReward(),state);
                        }
                        llThreeBtn.setVisibility(View.GONE);
                        etContent.setVisibility(View.GONE);
                        btnNextMission.setVisibility(View.VISIBLE);
                        etContent.setText("");
                    }
                }
            });

            btnNextMission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showMessage("Next mission");
                    llResult1.setVisibility(View.GONE);
                    llResult2.setVisibility(View.GONE);
                    btnNextMission.setVisibility(View.GONE);
                    etContent.setVisibility(View.VISIBLE);
                    llThreeBtn.setVisibility(View.VISIBLE);
                    if (mt != null)
                        mt.getMsg("1");
                }
            });
        }

        mt = new CodingDetailPresenter(this);
        mt.getMsg("1");
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
    }

    @Override
    public void sendToMain(JSONObject jb) {
        try {
            int rtnCode = jb.getInt("rtnCode");
            if (ConstantData.rtnCodeOK == rtnCode) {
                String data = jb.getString("data");
                JSONObject jsbData = new JSONObject(data);
                data = jsbData.getString("bean");
                jsbData = new JSONObject(data);
                cmb = new CodeMissionBean(jsbData.getString("rowId"),jsbData.getString("reward"),jsbData.getString("state"),ConstantData.userAccount);
                if(tvContent != null){
                    tvContent.setText(jsbData.getString("taskDesc"));
                    tvRight.setText(jsbData.getString("taskDesc"));

                    String content = "[单价] 本条任务录入正确可获 "+jsbData.getString("reward") +" 元";
                    mSpannableString = new SpannableString(content);
                    mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.coding_permoney)),15,content.length()-2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    tv_per_money.setText(mSpannableString);
                }

            } else {
                ToastUtil.showMessage("" + jb.getString("rtnMsg"));
            }
        } catch (Exception e) {
            ToastUtil.showMessage("" + e.getMessage());
        }
    }

    @Override
    public void setPresenter(CodingDetailContract.Presenter presenter) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //TODO now it's visible to user
            initView();
        } else {
            //TODO now it's invisible to user
            //int gg =5;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //TODO now it's invisible to user
    }

}


