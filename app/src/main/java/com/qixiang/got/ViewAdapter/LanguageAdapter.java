package com.qixiang.got.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;
import com.qixiang.got.model.LanguageInfo;

import java.util.List;

/**
 * created by jinjianan
 * on w
 */
public class LanguageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<LanguageInfo> list;

    private Context context;
    public OnItemClickListerner monItemClick;
    public LanguageAdapter(List<LanguageInfo> list, Context con) {
        this.list = list;
        this.context = con;
    }

    public interface OnItemClickListerner{
        void OnItemClick(int v);
    }
    public void setOnItemClick(OnItemClickListerner listerner){
        this.monItemClick = listerner;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        v = LayoutInflater.from(context).inflate(R.layout.fragment_languageitem, null);
        v.setOnClickListener(this);
        RecyclerView.ViewHolder holder = null;
        holder = new MyLanguageViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyLanguageViewHolder) holder).title.setText(list.get(position).title);
        ((MyLanguageViewHolder) holder).itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if(monItemClick != null){
            monItemClick.OnItemClick((int)v.getTag());
        }
    }
}

class MyLanguageViewHolder extends RecyclerView.ViewHolder {
    public TextView title;

    public MyLanguageViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.language_content);
    }
}