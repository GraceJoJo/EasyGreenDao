package com.jojo.sample.easygreendao.db.converter;

import com.alibaba.fastjson.JSON;
import com.jojo.sample.easygreendao.db.bean.AuthorBean;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 5:43 PM
 * desc   : BookEntity的AuthorBean属性转换器
 */
public class AuthorBeanConverter implements PropertyConverter<AuthorBean, String> {
    @Override
    public AuthorBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        return JSON.parseObject(databaseValue, AuthorBean.class);
    }

    @Override
    public String convertToDatabaseValue(AuthorBean entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return JSON.toJSONString(entityProperty);
    }
}
