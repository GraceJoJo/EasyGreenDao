package com.jojo.sample.easygreendao.db.converter;

import com.google.gson.Gson;
import com.jojo.sample.easygreendao.db.bean.TagBean;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/31 5:32 PM
 * desc   : BookEntity的List<TagBean>属性转换器
 */
public class TagBeanConverter  implements PropertyConverter<List<TagBean>, String> {
    //从数据库存储的拼接的json字符串逐个解析成实体放到List中返回(取)
    @Override
    public List<TagBean> convertToEntityProperty(String databaseValue) {
//        Elog.e("TAG", "function----convertToEntityProperty" + databaseValue);
        if (databaseValue == null) {
            return null;
        }
        List<String> list_str = Arrays.asList(databaseValue.split("#&#"));
        List<TagBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
//            Elog.e("TAG", "json-转实体=" + s);
            list_transport.add(new Gson().fromJson(s, TagBean.class));
        }
        return list_transport;
    }

    /**
     * 将每个VoiceBean转成gson字符串，以“#&#”为分割，拼接起来存储在数据库中(存)
     * @param voiceBeanList
     * @return
     */
    @Override
    public String convertToDatabaseValue(List<TagBean> voiceBeanList) {
        if (voiceBeanList == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (TagBean tagBean : voiceBeanList) {
                String str = new Gson().toJson(tagBean);
                sb.append(str);
                sb.append("#&#");
            }
//            Elog.e("TAG", "sb.toString=" + sb.toString());
            return sb.toString();
        }
    }
}
