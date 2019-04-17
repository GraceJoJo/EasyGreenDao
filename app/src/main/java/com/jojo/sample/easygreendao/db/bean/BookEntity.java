package com.jojo.sample.easygreendao.db.bean;

import com.jojo.sample.easygreendao.db.converter.AuthorBeanConverter;
import com.jojo.sample.easygreendao.db.converter.TagBeanConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 5:29 PM
 * desc   : 带自定义类型属性的实体
 */
@Entity
public class BookEntity {
    @Id
    private Long book_id;
    private String name_cn;
    @Convert(columnType = String.class, converter = TagBeanConverter.class)
    private List<TagBean> tags;
    @Convert(columnType = String.class, converter = AuthorBeanConverter.class)
    private AuthorBean author;
    //添加次标记之后不会生成数据库表的列
    @Transient
    public List<String> others = new ArrayList<>();

    @Generated(hash = 1926229834)
    public BookEntity(Long book_id, String name_cn, List<TagBean> tags,
                      AuthorBean author) {
        this.book_id = book_id;
        this.name_cn = name_cn;
        this.tags = tags;
        this.author = author;
    }

    @Generated(hash = 1373651409)
    public BookEntity() {
    }

    public Long getBook_id() {
        return this.book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getName_cn() {
        return this.name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public List<TagBean> getTags() {
        return this.tags;
    }

    public void setTags(List<TagBean> tags) {
        this.tags = tags;
    }

    public AuthorBean getAuthor() {
        return this.author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

}
