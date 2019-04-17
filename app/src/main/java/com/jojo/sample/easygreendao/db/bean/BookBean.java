package com.jojo.sample.easygreendao.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 1:57 PM
 * desc   : 书
 */
@Entity
public class BookBean {
    @Id
    private Long book_id;
    private String name_cn;
    private String author;
    private String comment;
    private String covor_url;
    @Generated(hash = 941737583)
    public BookBean(Long book_id, String name_cn, String author, String comment,
            String covor_url) {
        this.book_id = book_id;
        this.name_cn = name_cn;
        this.author = author;
        this.comment = comment;
        this.covor_url = covor_url;
    }
    @Generated(hash = 269018259)
    public BookBean() {
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
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getCovor_url() {
        return this.covor_url;
    }
    public void setCovor_url(String covor_url) {
        this.covor_url = covor_url;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "book_id=" + book_id +
                ", name_cn='" + name_cn + '\'' +
                ", author='" + author + '\'' +
                ", comment='" + comment + '\'' +
                ", covor_url='" + covor_url + '\'' +
                '}';
    }
}
