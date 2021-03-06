package com.jojo.sample.easygreendao.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jojo.sample.easygreendao.db.bean.BookBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK_BEAN".
*/
public class BookBeanDao extends AbstractDao<BookBean, Long> {

    public static final String TABLENAME = "BOOK_BEAN";

    /**
     * Properties of entity BookBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Book_id = new Property(0, Long.class, "book_id", true, "_id");
        public final static Property Name_cn = new Property(1, String.class, "name_cn", false, "NAME_CN");
        public final static Property Author = new Property(2, String.class, "author", false, "AUTHOR");
        public final static Property Comment = new Property(3, String.class, "comment", false, "COMMENT");
        public final static Property Covor_url = new Property(4, String.class, "covor_url", false, "COVOR_URL");
    }


    public BookBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BookBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: book_id
                "\"NAME_CN\" TEXT," + // 1: name_cn
                "\"AUTHOR\" TEXT," + // 2: author
                "\"COMMENT\" TEXT," + // 3: comment
                "\"COVOR_URL\" TEXT);"); // 4: covor_url
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BookBean entity) {
        stmt.clearBindings();
 
        Long book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindLong(1, book_id);
        }
 
        String name_cn = entity.getName_cn();
        if (name_cn != null) {
            stmt.bindString(2, name_cn);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(4, comment);
        }
 
        String covor_url = entity.getCovor_url();
        if (covor_url != null) {
            stmt.bindString(5, covor_url);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BookBean entity) {
        stmt.clearBindings();
 
        Long book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindLong(1, book_id);
        }
 
        String name_cn = entity.getName_cn();
        if (name_cn != null) {
            stmt.bindString(2, name_cn);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(4, comment);
        }
 
        String covor_url = entity.getCovor_url();
        if (covor_url != null) {
            stmt.bindString(5, covor_url);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BookBean readEntity(Cursor cursor, int offset) {
        BookBean entity = new BookBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // book_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name_cn
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // author
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // comment
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // covor_url
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BookBean entity, int offset) {
        entity.setBook_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName_cn(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthor(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setComment(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCovor_url(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BookBean entity, long rowId) {
        entity.setBook_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BookBean entity) {
        if(entity != null) {
            return entity.getBook_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BookBean entity) {
        return entity.getBook_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
