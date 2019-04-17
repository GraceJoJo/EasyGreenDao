package com.jojo.sample.easygreendao.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jojo.sample.easygreendao.MyApplication;
import com.jojo.sample.easygreendao.R;
import com.jojo.sample.easygreendao.db.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 3:23 PM
 * desc   : MainActivity列表Adapter
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {
    private List<BookBean> mDatas = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.item_book, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText("item=" + position + mDatas.get(position).getName_cn());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public void update(List<BookBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
}
