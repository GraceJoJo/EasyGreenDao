package com.jojo.sample.easygreendao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jojo.sample.easygreendao.R;
import com.jojo.sample.easygreendao.adapter.BookAdapter;
import com.jojo.sample.easygreendao.adapter.BookListAdapter;
import com.jojo.sample.easygreendao.constant.DataConstant;
import com.jojo.sample.easygreendao.db.DbManager;
import com.jojo.sample.easygreendao.db.bean.BookBean;
import com.jojo.sample.easygreendao.db.bean.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 4:11 PM
 * desc   : 操作包含自定义类型的实体
 */
public class SecondActivity extends AppCompatActivity {
    private List<BookEntity> dataList = new ArrayList();
    private TextView tvResult;
    private BookAdapter  mAdapter;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvResult = findViewById(R.id.tv_result);
        recyclerview = findViewById(R.id.rv);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(DataConstant.jsonlist).getAsJsonArray();//获取JsonArray对象
        for (JsonElement obj : jsonArray) {
            BookEntity bean = gson.fromJson(obj, BookEntity.class);
            dataList.add(bean);
        }


        mAdapter = new BookAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);
    }

    public void insertAll(View v) {
        DbManager.getInstance().insertOrReplaceMultObject(BookEntity.class, dataList);
        tvResult.setText("插入List成功");
    }

    public void queryAll(View v) {
        List<BookEntity> localList = (List<BookEntity>) DbManager.getInstance().queryAll(BookEntity.class);
        mAdapter.update(localList);
        tvResult.setText("查询List成功  list.size=" + localList.size());
    }

    public void deleteAll(View v) {
        DbManager.getInstance().deleteAll(BookEntity.class);
        tvResult.setText("删除List成功");
    }
}
