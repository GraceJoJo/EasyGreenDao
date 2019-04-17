# EasyGreenDao
GreenDao在实际项目中的运用，以及遇到的坑及解决

# 《一》GreenDao简单介绍：<p>
# 1.GreenDao是什么？<p>
官网介绍：GreenDao是Android的一个对象关系映射（ORM）数据库。

**【对象关系映射ORM】:** Object Relational Mapping，对象/关系数据库，是一种将对象层次结构映射成关系型结构的方法。将面向对象语言程序中的对象自动持久化到关系数据库中。本质上就是将数据从一种形式转换到另外一种形式。**简单的理解，其实就是Java中每个对象，都对应数据库表中的一条数据。**<p>
#2.为什么要选择GreenDao？
GreenDao特点：
* 性能最大化（Maximum performance）
* 易于使用的强大API（Easy to use powerful APIs）
* 最小内存消耗（Minimal memory consumption）
* 一个小于100KB的精简的库（Small library size (<100KB)）
* 数据库加密以保证数据的高安全性 （Database encryption）·

（以上均来自[官网的介绍](http://greenrobot.org/greendao/)）
# 《二》如何使用GreenDao？<p>
可以直接参考Demo的实现：[EasyGreenDao](https://github.com/GraceJoJo/EasyGreenDao)<p>
![easygreendao.gif](https://upload-images.jianshu.io/upload_images/3828835-01f8d5fd6ffbcf7f.gif?imageMogr2/auto-orient/strip)
<p>**Demo功能包括：**
*1、简单实体bean和集合List的数据库操作。*
*2、复杂实体的数据库操作，如何处理自定义类型的实体及属性转换器的使用。*
例如项目中常常会遇到类似下面这种复杂List的数据：<p>
    ![image.png](https://upload-images.jianshu.io/upload_images/3828835-e69c7a45284ef82b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/380)
**接下来讲解一下GreenDao的使用步骤：**
（1）build.gradle中添加greendao依赖，配置数据库版本号、自动生成类的存放目录等。
```
apply plugin: 'org.greenrobot.greendao'
android {
    compileSdkVersion 26
    buildToolsVersion "26.0.2"
    //greendao
    greendao {
        schemaVersion 1  //数据库版本号，数据库升级时使用
        daoPackage 'com.xxxx.xxx.db.gen'  // 为自动生成的类指定目录
        targetGenDir 'src/main/java'
    }
}
dependencies {
  //greendao
    compile 'org.greenrobot:greendao:3.2.0'
}
```
(2)项目目录下的build.gradle添加：
```
   //greendao
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.0'
```
(3)声明一个数据库对应的实体类,以用户实体为例
```
@Entity
public class UserBean {
    @Id
    private Long id;
    public String mobile;//手机号
    public String name;//名字
    public String sex;//性别
  }
```
Rebuild或Make Project，会自动生成UserBean的get/set方法及对应的数据库操作类UserBeanDao及DaoMaster、DaoSession。
（4）创建一个数据库操作管理类DbManager,对数据库相关操作进行统一封装处理。（此处只粘贴了部分代码，完整代码请戳：[EasyGreenDao](https://github.com/GraceJoJo/EasyGreenDao)）
```
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
}
```
（5）例如用户登录成功后，需要存储一个当前用户的信息。
```
UserBean   userBean = new UserBean(1l, "18510286862", "小小花", "女");
 long insert = DbManager.getInstance(). insertOrReplace(UserBean.class, userBean);
```
其他增删改查的操作请看[EasyGreenDao](https://github.com/GraceJoJo/EasyGreenDao)，这里就不赘述了。


# 《三》关于自动生成的几个类：<p>
**DaoMaster**：DaoMaster 负责管理数据库对象(SQLiteDatabase)和 DAO 类(对象)，我们可以通过它内部类 OpenHelper 和 DevOpenHelper SQLiteOpenHelper 创建不同模式的 SQLite 数据库。
 **DaoSession**：管理所有的Dao对象。提供通用的插入、查询、更新和删除实体的持久化方法。
**xxxDao**：每个被@Entity注解的实体，都有对应的xxxDao对象，通过该xxxDao可以对实体进行增删改查操作。
<p>
    
# 《四》GreenDao注解的说明：<p>
```
- @Id :主键 long/Long型，可以通过@Id(autoincrement = true)设置自增长 
- @Property：设置一个非默认关系映射所对应的列名，默认是的使用字段名 举例：@Property (nameInDb=”name”) 
- @NotNul：设置数据库表当前列不能为空 
- @Transient ：添加次标记之后不会生成数据库表的列 
1.)索引注解 
- @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束 
- @Unique：向数据库列添加了一个唯一的约束 
2.）关系注解 
- @ToOne：定义与另一个实体（一个实体对象）的关系 
- @ToMany：定义与多个实体对象的关系
```
主键的声明只可能是下面几种情况，否则会出错。
自增使用的id必须是Long类型，而非long类型。不然不会自增
```
 @Id(autoincrement = true)
 private Long id;
```
```
 @Id
 private Long id;
```
```
 @Id
 private long id;
```

不需要主键自增的情况，可以使用String类型。
```
 @Id
 private String id;
```
<p>
    
# 《五》GreenDao采坑——可能遇到的问题及解决方案<p>
  (1) App每次升级版本时，如果对@Entity注解的类有过修改，例如某个数据库实体新增了某字段或修改了字段等，记得在build.gradle中更新schemaVersion数据库版本，否则覆盖安装后会崩溃。例如在实体BookEntity新增了des字段，则会报如下错误：
> Caused by: android.database.sqlite.SQLiteException: table BOOK_ENTITY has no column named DES (Sqlite code 1): , while compiling: INSERT OR REPLACE INTO "BOOK_ENTITY" ("_id","DES","NAME_CN","TAGS","AUTHOR") VALUES (?,?,?,?,?), (OS error - 2:No such file or directory)...
<p>
（2）GreenDao 存储数据时，实体里面用@Id 的字段，不可以为int类型，否则会报下面的错误<p>
>  java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Long...
                                                       
@Id类型为Long类型时：
>  android.database.sqlite.SQLiteException: no such column: T._id (Sqlite code 1): , while compiling: SELECT T."_id",T."IMG",T."SORT",T."TYPE",T."LINK",T."PARAM",T."ADD_TIME",T."STATUS",T."BANNERS" FROM "SUBJECT_BEAN" T, (OS error - 2:No such file or directory)...

（3）使用insert插入时，第一次插入正常，如果再插入时如果主键id值已经存在，则会抛出下面异常。所以我们要求在数据库中插入时，某条记录不存在则插入，存在则更新。即使用 DbManager.getInstance().insertOrReplace();替代DbManager.getInstance().insert()方法。<p>

>  Caused by: android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: USER_BEAN._id (Sqlite code 1555), (OS error - 2:No such file or directory)

（4）GreenDao 存储嵌套的集合数据时奇怪的Bug
![image.png](https://upload-images.jianshu.io/upload_images/3828835-dc3c2e1397d986b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
databaseValue==null,但是最后存储在集合中的size=1,数据为null????之前开发遇到的bug，暂时还未找到出现原因。

本文参考：
[GreenDao官网](http://greenrobot.org/greendao/)
[GreenDao源码]([https://github.com/greenrobot/greenDAO](https://link.jianshu.com/?t=https://github.com/greenrobot/greenDAO).
)


