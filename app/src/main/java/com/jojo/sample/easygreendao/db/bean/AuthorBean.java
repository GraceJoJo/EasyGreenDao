package com.jojo.sample.easygreendao.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 5:41 PM
 * desc   :
 */
@Entity
public class AuthorBean {
    @Id
    private Long id;
    private String author_name;
    private String identity;
    @Generated(hash = 1987753500)
    public AuthorBean(Long id, String author_name, String identity) {
        this.id = id;
        this.author_name = author_name;
        this.identity = identity;
    }
    @Generated(hash = 1694633584)
    public AuthorBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor_name() {
        return this.author_name;
    }
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    public String getIdentity() {
        return this.identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
