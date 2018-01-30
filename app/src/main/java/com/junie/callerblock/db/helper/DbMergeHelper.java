package com.junie.callerblock.db.helper;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by niejun on 2017/11/5.
 */

public class DbMergeHelper {

    private static final String TAG = "DbMergeHelper";
    private Database db;

    public DbMergeHelper(Database db) {
        this.db = db;
    }

    /**
     * 数据库更新操作
     * @param daoList
     */
    public void updateDb(List<Class<? extends AbstractDao<?, ?>>> daoList) {
        if (daoList == null) {
            return;
        }
        for (int i = 0; i < daoList.size(); i++) {
            DaoConfig daoConfig = new DaoConfig(db, daoList.get(i));
            String tableName = daoConfig.tablename;
            try {
                if (tableIsExist(tableName)) {
                    //旧表的字段集合
                    List<String> dbFildsList = getColumns(db, tableName);
                    //新表字段集合
                    Property[] properties = daoConfig.properties;

                    for (int j = 0; j < properties.length; j++) {
                        String fildName = properties[j].columnName;
                        Class<?> fildType = properties[j].type;
                        if (!dbFildsList.contains(fildName)) {
                            String type = getTypeByClass(fildType);
                            if(TextUtils.isEmpty(type)) {
                                Log.e(TAG,"获取类型异常");
                                return;
                            }
                            execNonQuery("ALTER TABLE " + tableName + " ADD COLUMN " + fildName + " "+ type);
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "", e);
            }
        }
    }

    /**
     * 获取字段类型
     * @param type 类型类
     * @return
     * @throws Exception 获取失败
     */
    public String getTypeByClass(Class<?> type) throws Exception {
        Log.d(TAG, "typeClass ：" + type.getSimpleName());
        if (type.equals(String.class)) {
            return "TEXT";
        }
        if (type.equals(Long.class) || type.equals(Integer.class) || type.equals(Boolean.class)
                || type.equals(int.class) || type.equals(long.class) || type.equals(boolean.class)) {
            return "INTEGER";
        }
        Log.e(TAG, "get type fail");
        return null;
    }

    /**
     * 获取所有列
     * @param db
     * @param tableName
     * @return
     */
    private List<String> getColumns(Database db, String tableName) {
        List<String> columns = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1", null);
            if (cursor != null) {
                columns = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return columns;
    }


    /**
     * 数据表是否存在
     * @param tableName 数据表名称
     * @return
     */
    private boolean tableIsExist(String tableName) {
        Cursor cursor = execQuery("SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
        if (cursor != null) {
            try {
                if (cursor.moveToNext()) {
                    int count = cursor.getInt(0);
                    if (count > 0) {
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "", e);
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * 查询操作
     * @param sql
     * @return
     */
    private Cursor execQuery(String sql) {
        try {
            return db.rawQuery(sql, null);
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
        return null;
    }

    /**
     *  非查询操作
     * @param sql
     */
    public void execNonQuery(String sql) {
        try {
            Log.d(TAG,"sql: " + sql);
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d(TAG, "", e);
        }
    }
}