package com.qixiang.got.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qixiang.got.R;
import com.qixiang.got.model.CashOutHistoryInfo;

import java.util.List;

/**
 * created by jinjianan
 * on w
 */
public class CashOutHistroyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CashOutHistoryInfo> list;

    private Context context;
    public OnItemClickListener monItemClick;

    public CashOutHistroyAdapter(List<CashOutHistoryInfo> list, Context con) {
        this.list = list;
        this.context = con;
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
        v = LayoutInflater.from(context).inflate(R.layout.fragment_cashout_histroy_item, null);

        RecyclerView.ViewHolder holder = null;
        holder = new MyViewHolderHistroy(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolderHistroy) holder).money.setText(list.get(position).money);
        ((MyViewHolderHistroy) holder).time.setText(list.get(position).time);
        ((MyViewHolderHistroy) holder).state.setText(list.get(position).state);

        ((MyViewHolderHistroy) holder).itemView.setTag(position);
//        ((MyViewHolderHistroy) holder).time.setText(list.get(position).time);
//        ((MyViewHolderHistroy) holder).source.setText(list.get(position).source);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

class MyViewHolderHistroy extends RecyclerView.ViewHolder {
    public TextView money, time, state;

    public MyViewHolderHistroy(View itemView) {
        super(itemView);
        money = itemView.findViewById(R.id.tv_cash_out_money);
        time = itemView.findViewById(R.id.tv_cash_out_time);
        state = itemView.findViewById(R.id.tv_cash_out_state);
    }
}