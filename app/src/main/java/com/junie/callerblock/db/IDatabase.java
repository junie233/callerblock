package com.junie.callerblock.db;

import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by niejun on 2017/11/2.
 */

public interface IDatabase<T, K> {

    boolean insert(T t);

    boolean insertList(List<T> tList);

    boolean insertOrReplace(@NotNull T t);

    boolean insertOrReplaceList(List<T> tList);


    boolean delete(T t);

    boolean deleteByKey(K key);

    boolean deleteList(List<T> tList);

    boolean deleteAll();


    T queryByPrimaryKey(K key);

    QueryBuilder<T> getQueryBuilder();

    List<T> queryAll();



    boolean update(T t);

    boolean updateList(List<T> tList);


    void execSql(String sql, Object[] bindArgs);


    void clearDaoSession();

}