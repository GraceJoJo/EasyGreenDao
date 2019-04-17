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
import com.jojo.sample.easygreendao.db.bean.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/2/21 3:46 PM
 * desc   : SecondActivity列表Adapter
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private List<BookEntity> mDatas = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.item_book, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText("著作名称：《" + mDatas.get(position).getName_cn() + "》");
        holder.tvAuthor.setText("作者：" + mDatas.get(position).getAuthor().getAuthor_name());
        //实际开发中需要动态判断Tags集合的size大小取数据展示，这里只是为了方便展示
        holder.tvTags.setText(mDatas.get(position).getTags().get(0).getName() + "、" + mDatas.get(position).getTags().get(1).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAuthor;
        TextView tvTags;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTags = itemView.findViewById(R.id.tv_tag);
        }
    }

    public void update(List<BookEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
}
