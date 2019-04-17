package com.jojo.sample.easygreendao.db;

import com.jojo.sample.easygreendao.MyApplication;
import com.jojo.sample.easygreendao.db.gen.DaoMaster;
import com.jojo.sample.easygreendao.db.gen.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/30 4:20 PM
 * desc   : GreenDao数据库操作管理类
 */
public class DbManager {
    private final static String DB_NAME = "easydao.db";
    private static DbManager instance;
    private DaoMaster.DevOpenHelper openHelper;
    private DaoMaster writeDM;
    private DaoMaster readDM;
    private DaoSession daoSession;


    /***
     * 获取 DbManager 单例
     *
     * @return
     */
    public static DbManager getInstance() {
        DbManager dbManager = instance;
        if (dbManager == null) {
            synchronized (DbManager.class) {
                if (dbManager == null) {
                    instance = new DbManager();
                    dbManager = instance;
                }
            }
        }
        return dbManager;
    }

    private DbManager() {
        openHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), DB_NAME, null);
        writeDM = new DaoMaster(openHelper.getWritableDb());
        readDM = new DaoMaster(openHelper.getReadableDb());
    }

    /**
     * 获取 writeable Dao
     *
     * @param entityClass
     * @return
     */
    private AbstractDao getWriteDao(Class<? extends Object> entityClass) {
        daoSession = writeDM.newSession();
        return daoSession.getDao(entityClass);
    }

    /**
     * 获取 readable Dao
     *
     * @param entityClass
     * @return
     */
    private AbstractDao getReadDao(Class<? extends Object> entityClass) {
        daoSession = readDM.newSession();
        return daoSession.getDao(entityClass);
    }

    /**
     * 插入单个的实体:如果插入时主键id值已经在表中存在，则会抛出异常 UNIQUE constraint failed: USER_BEAN._id (Sqlite code 1555),
     *
     * @param entityClass
     * @return
     */
    public long insert(Class<? extends Object> entityClass, Object object) {
        if (null == object) {
            return -1L;
        }
        return getWriteDao(entityClass).insert(object);
    }

    /**
     * 插入或更新（数据存在则替换，数据不存在则插入）
     *
     * @param entityClass
     * @param object
     * @return
     */
    public long insertOrReplace(Class<? extends Object> entityClass, Object object) {
        if (null == object) {
            return -1L;
        }
        return getWriteDao(entityClass).insertOrReplace(object);
    }

    /**
     * 插入列表
     *
     * @param entityClass
     * @param objects
     */
    public void insertMultObject(Class<? extends Object> entityClass, List<? extends Object> objects) {
        if (objects == null && objects.size() == 0) {
            return;
        }
        getWriteDao(entityClass).insertInTx(objects);
    }

    /**
     * 插入或更新列表
     *
     * @param entityClass
     * @param objects
     */
    public void insertOrReplaceMultObject(Class<? extends Object> entityClass, List<? extends Object> objects) {
        if (objects == null && objects.size() == 0) {
            return;
        }
        getWriteDao(entityClass).insertOrReplaceInTx(objects);
    }

    /**
     * 更新某一条数据
     *
     * @param entityClass
     * @param object
     */
    public void update(Class<? extends Object> entityClass, Object object) {
        if (null == object) {
            return;
        }
        getWriteDao(entityClass).update(object);
    }
    /**
     * 更新多条数据
     *
     * @param entityClass
     * @param objects
     */
    public void updateMultObject(Class<? extends Object> entityClass, List<? extends Object> objects) {

        if (null == objects || objects.isEmpty()) {
            return;
        }
        getWriteDao(entityClass).updateInTx(objects);

    }

    /**
     * 查询某个类型对应的数据库中所有的实体
     *
     * @param entityClass
     * @return
     */
    public List<? extends Object> queryAll(Class<? extends Object> entityClass) {
        List<? extends Object> entitys = getWriteDao(entityClass).queryBuilder().list();
        return entitys;
    }

    /**
     * 精确查找某个实体
     *
     * @param entityClass 目标表名
     * @param properties  目标属性
     * @param objects     匹配条件
     * @return
     */
    public Object query(Class<? extends Object> entityClass, List<Property> properties, List<Object> objects) {
        if (null == properties || properties.isEmpty()) {
            return null;
        }
        int size = properties.size();
        QueryBuilder queryBuilder = getWriteDao(entityClass).queryBuilder();
        for (int i = 0; i < size; i++) {
            queryBuilder.where(properties.get(i).eq(objects.get(i)));
        }

        return queryBuilder.build().unique();
    }


    /**
     * 删除单个实体
     *
     * @param entityClass
     * @param object
     */
    public void delete(Class<? extends Object> entityClass, Object object) {
        if (null == object) {
            return;
        }
        getWriteDao(entityClass).delete(object);
    }

    /**
     * 删除某个类的所有实体
     *
     * @param entityClass
     */
    public void deleteAll(Class<? extends Object> entityClass) {
        getWriteDao(entityClass).deleteAll();
    }

}
