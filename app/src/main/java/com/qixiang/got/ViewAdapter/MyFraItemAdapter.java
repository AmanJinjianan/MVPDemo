package com.qixiang.got.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;
import com.qixiang.got.model.MyFraItemInfo;

import java.util.List;

/**
 * created by jinjianan
 * on w
 */
public class MyFraItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<MyFraItemInfo> list;

    private Context context;
    public OnItemClickListener monItemClick;

    public MyFraItemAdapter(List<MyFraItemInfo> list, Context con) {
        this.list = list;
        this.context = con;
    }

    @Override
    public void onClick(View v) {
        if(monItemClick != null)
        {
            monItemClick.onItemClick(v,(int)v.getTag());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }
    public void setOnItemClick(OnItemClickListener listener){
        this.monItemClick = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        v = LayoutInflater.from(context).inflate(R.layout.myfragmentdetalitem, null);

        v.setOnClickListener(this);

        RecyclerView.ViewHolder holder = null;
        holder = new MyFraViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyFraViewHolder) holder).title.setText(list.get(position).title);
        ((MyFraViewHolder) holder).itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

class MyFraViewHolder extends RecyclerView.ViewHolder {
    public TextView title, time, source;

    public MyFraViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_myframitem);
    }
}