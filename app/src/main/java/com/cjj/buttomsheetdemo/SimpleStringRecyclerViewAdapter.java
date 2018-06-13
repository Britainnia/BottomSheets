package com.cjj.buttomsheetdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

    private Context mContext; //上下文
    private ArrayList<ShoppingDetail> dataList;
    private ItemClickListener mItemClickListener;


    public void setItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.tv);
        }


    }

    public SimpleStringRecyclerViewAdapter(Context context, ArrayList<ShoppingDetail> dataList) {
        super();
        this.mContext = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//            if (position == 0) {
//                holder.mImageView.setImageResource(R.drawable.a6);
//            } else if (position == 1) {
//                holder.mImageView.setImageResource(R.drawable.a5);
//            }
//
//            holder.mImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(mContext, "pos --->" + position, Toast.LENGTH_SHORT).show();
//                    mItemClickListener.onItemClick(position);
//                }
//            });
        ShoppingDetail item = dataList.get(position);
        holder.mTextView.setText(item.getName());

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(mContext, "pos --->" + position, Toast.LENGTH_SHORT).show();
                mItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
