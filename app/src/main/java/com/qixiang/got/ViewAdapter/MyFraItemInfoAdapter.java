package com.qixiang.got.ViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qixiang.got.MainActivity;
import com.qixiang.got.R;
import com.qixiang.got.model.MyFraItemInfo;

import java.util.List;

/**
 * created by jinjianan
 * on 20200111
 */
public class MyFraItemInfoAdapter extends BaseAdapter {
    private List<MyFraItemInfo> data;

    private Activity context;
    public MyFraItemInfoAdapter(List<MyFraItemInfo>data,Activity con){
        this.data=data;
        this.context = con;
    }

    //说明listview有多少个条目
    @Override
    public int getCount() {
        return data.size();
    }

    //得到指定position 的条目
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    //得到条目的ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    //说明每个条目的布局
    //convertView:缓存的条目
    //parent:ListView
    //返回值作为ListView的一个条目
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyFraItemInfo MyFraItemInfo=data.get(position);
        if(convertView == null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.myfragmentdetalitem, null);
        }
        TextView tvName=(TextView) convertView.findViewById(R.id.tv_myframitem);
        //设置数据
        if(tvName != null)
            tvName.setText(MyFraItemInfo.getTitle());

        return convertView;
    }

}
