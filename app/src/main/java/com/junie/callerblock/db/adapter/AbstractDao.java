package com.junie.callerblock.db.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;

import com.junie.callerblock.db.IDatabase;
import com.junie.callerblock.db.helper.DbOpenHelper;
import com.junie.callerblock.gen.DaoMaster;
import com.junie.callerblock.gen.DaoSession;

import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by niejun on 2017/11/2.
 */

public abstract class AbstractDao<T, K> implements IDatabase<T, K> {

    private final static String TAG = AbstractDao.class.getSimpleName();

    private static final String DEFAULT_DATABASE_NAME = "callblock.db";

    private static DbOpenHelper mHelper;
    protected static DaoSession daoSession;
    /**
     * 初始化OpenHelper
     * @param context
     */
    public static void initOpenHelper(@NotNull Context context) {
        mHelper = new DbOpenHelper(context, DEFAULT_DATABASE_NAME, null);
        daoSession = new DaoMaster(getWritableDatabase()).newSession();
    }



    private static SQLiteDatabase getWritableDatabase() {
        try {
            return mHelper.getWritableDatabase();
        } catch(Exception e) {
            Log.e(TAG,"",e);
        }
        return null;
    }

    private static SQLiteDatabase getReadableDatabase() {
        return mHelper.getReadableDatabase();
    }



    /**
     * 关闭
     */
    public static void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public void clearDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }


    //---插入---------

    @Override
    public synchronized boolean insert(@NotNull T t) {
        try {
            if (t == null)
                return false;
            getAbstractDao().insert(t);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean insertOrReplace(@NotNull T t) {
        try {
            if (t == null)
                return false;
            getAbstractDao().insertOrReplace(t);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean insertList(@NotNull List<T> list) {
        try {
            if (list == null || list.size() == 0)
                return false;
            getAbstractDao().insertInTx(list);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }

    /**
     * @param list
     * @return
     */
    @Override
    public synchronized boolean insertOrReplaceList(@NotNull List<T> list) {
        try {
            if (list == null || list.size() == 0)
                return false;
            getAbstractDao().insertOrReplaceInTx(list);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }


    //--删除------------------

    @Override
    public synchronized boolean delete(@NotNull T t) {
        try {
            if (t == null)
                return false;
            getAbstractDao().delete(t);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean deleteByKey(K key) {
        try {
            if (TextUtils.isEmpty(key.toString()))
                return false;
            getAbstractDao().deleteByKey(key);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }


    @Override
    public synchronized boolean deleteList(List<T> tList) {
        try {
            if (tList == null || tList.size() == 0)
                return false;
            getAbstractDao().deleteInTx(tList);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean deleteAll() {
        try {
            getAbstractDao().deleteAll();
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }


    //--查找------------------------

    @Override
    public synchronized T queryByPrimaryKey(@NotNull K key) {
        try {
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return null;
        }
    }

    @Override
    public synchronized List<T> queryAll() {
        return getAbstractDao().loadAll();
    }

    @Override
    public QueryBuilder<T> getQueryBuilder() {
        return getAbstractDao().queryBuilder();
    }




    //--更新--------------------

    @Override
    public boolean update(@NotNull T t) {
        try {
            if (t == null)
                return false;
            getAbstractDao().update(t);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }


    @Override
    public boolean updateList(List<T> tList) {
        try {
            if (tList == null || tList.size() == 0)
                return false;
            getAbstractDao().updateInTx(tList);
        } catch (SQLiteException e) {
            Log.e(TAG,"",e);
            return false;
        }
        return true;
    }

    @Override
    public void execSql(String sql, Object[]bindArgs) {
        daoSession.getDatabase().execSQL(sql,bindArgs);
    }


    /**
     * 获取Dao
     * @return
     */
    protected abstract org.greenrobot.greendao.AbstractDao getAbstractDao();


}