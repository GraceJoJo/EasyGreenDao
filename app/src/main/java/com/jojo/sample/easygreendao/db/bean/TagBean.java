package com.jojo.sample.easygreendao.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 5:30 PM
 * desc   :
 */
@Entity
public class TagBean {
    @Id
    private String id;
    private String name;
    @Generated(hash = 1442273273)
    public TagBean(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 814153312)
    public TagBean() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
