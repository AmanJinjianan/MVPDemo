package com.qixiang.got.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;
import com.qixiang.got.model.MainFraInfo;

import java.util.List;

/**
 * created by jinjianan
 * on w
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<MainFraInfo> list;

    private Context context;
    public OnItemClickListener monItemClick;

    public MainAdapter(List<MainFraInfo> list, Context con) {
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
        void onItemClick(View v,int position);
    }
    public void setOnItemClick(OnItemClickListener listener){
        this.monItemClick = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        v = LayoutInflater.from(context).inflate(R.layout.fragment_mainitem, null);

        v.setOnClickListener(this);

        RecyclerView.ViewHolder holder = null;
        holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).title.setText(list.get(position).title);
        //((MyViewHolder) holder).title.setTag(position);

        ((MyViewHolder) holder).itemView.setTag(position);
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