package com.cjj.buttomsheetdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * 购物车列表适配器
 */

public class ShoppingCarListAdapter extends RecyclerView.Adapter<ShoppingCarListAdapter.ViewHolder> {
    private Context mContext; //上下文
    private ArrayList<ShoppingDetail> dataList;

    private AddClickListener addListener;
    private RemoveClickListener removeListener;

    public void setAddClickListener(AddClickListener listener) {
        addListener = listener;
    }

    public interface AddClickListener {
        void onItemClick(int pos);
    }

    public void setRemoveClickListener(RemoveClickListener listener) {
        removeListener = listener;
    }

    public interface RemoveClickListener {
        void onItemClick(int pos);
    }

    public ShoppingCarListAdapter(Context context, ArrayList<ShoppingDetail> dataList) {
        super();
        this.mContext = context;
        this.dataList = dataList;
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_car_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ShoppingDetail item = dataList.get(position);
        holder.tv_name.setText(item.getName());
        holder.tv_count.setText(String.valueOf(item.getNumber()));
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListener.onItemClick(position);
//                int addnum = item.getNumber();
//                if (addnum < 30) {
//                    item.setNumber(++addnum);
//                    holder.tv_count.setText(item.getNumber());
//                }
            }
        });
        holder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeListener.onItemClick(position);
//                int rmvnum = item.getNumber();
//                if (rmvnum > 1) {
//                    item.setNumber(--rmvnum);
//                    holder.tv_count.setText(item.getNumber());
//                }
//                else {
//                    dataList.remove(position);
//                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_count;
        ImageView iv_add, iv_remove;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            iv_add = (ImageView) view.findViewById(R.id.iv_add);
            iv_remove = (ImageView) view.findViewById(R.id.iv_remove);
            tv_count = (TextView) view.findViewById(R.id.tv_count);
        }

    }
}
