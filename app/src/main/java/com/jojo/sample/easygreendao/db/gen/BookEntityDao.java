package com.jojo.sample.easygreendao.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jojo.sample.easygreendao.db.bean.AuthorBean;
import com.jojo.sample.easygreendao.db.converter.AuthorBeanConverter;
import com.jojo.sample.easygreendao.db.converter.TagBeanConverter;
import java.util.List;

import com.jojo.sample.easygreendao.db.bean.BookEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK_ENTITY".
*/
public class BookEntityDao extends AbstractDao<BookEntity, Long> {

    public static final String TABLENAME = "BOOK_ENTITY";

    /**
     * Properties of entity BookEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Book_id = new Property(0, Long.class, "book_id", true, "_id");
        public final static Property Name_cn = new Property(1, String.class, "name_cn", false, "NAME_CN");
        public final static Property Tags = new Property(2, String.class, "tags", false, "TAGS");
        public final static Property Author = new Property(3, String.class, "author", false, "AUTHOR");
    }

    private final TagBeanConverter tagsConverter = new TagBeanConverter();
    private final AuthorBeanConverter authorConverter = new AuthorBeanConverter();

    public BookEntityDao(DaoConfig config) {
        super(config);
    }
    
    public BookEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: book_id
                "\"NAME_CN\" TEXT," + // 1: name_cn
                "\"TAGS\" TEXT," + // 2: tags
                "\"AUTHOR\" TEXT);"); // 3: author
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BookEntity entity) {
        stmt.clearBindings();
 
        Long book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindLong(1, book_id);
        }
 
        String name_cn = entity.getName_cn();
        if (name_cn != null) {
            stmt.bindString(2, name_cn);
        }
 
        List tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(3, tagsConverter.convertToDatabaseValue(tags));
        }
 
        AuthorBean author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, authorConverter.convertToDatabaseValue(author));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BookEntity entity) {
        stmt.clearBindings();
 
        Long book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindLong(1, book_id);
        }
 
        String name_cn = entity.getName_cn();
        if (name_cn != null) {
            stmt.bindString(2, name_cn);
        }
 
        List tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(3, tagsConverter.convertToDatabaseValue(tags));
        }
 
        AuthorBean author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, authorConverter.convertToDatabaseValue(author));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BookEntity readEntity(Cursor cursor, int offset) {
        BookEntity entity = new BookEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // book_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name_cn
            cursor.isNull(offset + 2) ? null : tagsConverter.convertToEntityProperty(cursor.getString(offset + 2)), // tags
            cursor.isNull(offset + 3) ? null : authorConverter.convertToEntityProperty(cursor.getString(offset + 3)) // author
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BookEntity entity, int offset) {
        entity.setBook_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName_cn(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTags(cursor.isNull(offset + 2) ? null : tagsConverter.convertToEntityProperty(cursor.getString(offset + 2)));
        entity.setAuthor(cursor.isNull(offset + 3) ? null : authorConverter.convertToEntityProperty(cursor.getString(offset + 3)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BookEntity entity, long rowId) {
        entity.setBook_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BookEntity entity) {
        if(entity != null) {
            return entity.getBook_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BookEntity entity) {
        return entity.getBook_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
