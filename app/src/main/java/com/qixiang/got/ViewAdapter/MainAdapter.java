package com.qixiang.got.ViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;

import java.util.List;

/**
 * created by jinjianan
 * on w
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MainFraInfo> list;

    private Context context;
    public MainAdapter(List<MainFraInfo> list, Context con) {
        this.list = list;
        this.context = con;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        v = LayoutInflater.from(context).inflate(R.layout.fragment_maininfo, null);
        RecyclerView.ViewHolder holder = null;
        holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).title.setText(list.get(position).title);
//        ((MyViewHolder) holder).time.setText(list.get(position).time);
//        ((MyViewHolder) holder).source.setText(list.get(position).source);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title, time, source;

    public MyViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
//        source = itemView.findViewById(R.id.source);
//        time = itemView.findViewById(R.id.time);
    }
}