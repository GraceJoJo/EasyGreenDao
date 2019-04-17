package com.jojo.sample.easygreendao.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jojo.sample.easygreendao.adapter.BookListAdapter;
import com.jojo.sample.easygreendao.db.DbManager;
import com.jojo.sample.easygreendao.R;
import com.jojo.sample.easygreendao.constant.DataConstant;
import com.jojo.sample.easygreendao.db.bean.BookBean;
import com.jojo.sample.easygreendao.db.bean.UserBean;
import com.jojo.sample.easygreendao.db.gen.UserBeanDao;

import org.greenrobot.greendao.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试GreenDao 增删改查
 */
public class MainActivity extends AppCompatActivity {

    private UserBean userBean;
    private TextView tvResult;
    private RecyclerView recyclerview;
    private List<BookBean> dataList;
    private BookListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        recyclerview = findViewById(R.id.rv);

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(DataConstant.jsonData).getAsJsonArray();//获取JsonArray对象
        dataList = new ArrayList();
        for (JsonElement obj : jsonArray) {
            BookBean bookBean = gson.fromJson(obj, BookBean.class);
            dataList.add(bookBean);
        }

        userBean = new UserBean(1l, "18510286862", "小小花", "女");


        mAdapter = new BookListAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);
//        mAdapter.updatee(dataList);
    }

    public void jump(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
    }

    public void insert(View v) {
        long insert = DbManager.getInstance().insertOrReplace(UserBean.class, userBean);
        Log.e("TAG", "插入成功=" + insert);
        tvResult.setText("插入成功=" + insert);
    }

    public void update(View v) {
        userBean.setName("吃多了肉的胖小花");
        DbManager.getInstance().update(UserBean.class, userBean);
        Log.e("TAG", "更新成功");
        tvResult.setText("更新成功");
    }

    public void query(View v) {
        List<Property> properties = new ArrayList<>();
        List<Object> objects = new ArrayList();
        properties.add(UserBeanDao.Properties.Id);
        objects.add(userBean.getId());
        UserBean bean = (UserBean) DbManager.getInstance().query(UserBean.class, properties, objects);
        if (bean == null) {
            tvResult.setText("查询无数据");
        } else {
            Log.e("TAG", "查询成功=" + bean.toString());
            tvResult.setText(bean.toString());
        }
    }

    public void delete(View v) {
        DbManager.getInstance().delete(UserBean.class, userBean);
        Log.e("TAG", "删除成功");
        tvResult.setText("删除成功");
    }

    public void insertAll(View v) {
        DbManager.getInstance().insertOrReplaceMultObject(BookBean.class, dataList);
        tvResult.setText("插入List成功");
    }

    public void queryAll(View v) {
        List<BookBean> localList = (List<BookBean>) DbManager.getInstance().queryAll(BookBean.class);
        mAdapter.update(localList);
        tvResult.setText("查询List成功  list.size=" + localList.size());
    }

    public void deleteAll(View v) {
        DbManager.getInstance().deleteAll(BookBean.class);
        tvResult.setText("删除List成功");
    }

}
