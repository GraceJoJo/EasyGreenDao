package com.jojo.sample.easygreendao.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/30 12:19 PM
 * desc   :
 */
@Entity
public class UserBean {
    @Id
    private Long id;
    public String mobile;//手机号
    public String name;//名字
    public String sex;//性别
    @Generated(hash = 1523357216)
    public UserBean(Long id, String mobile, String name, String sex) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.sex = sex;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
