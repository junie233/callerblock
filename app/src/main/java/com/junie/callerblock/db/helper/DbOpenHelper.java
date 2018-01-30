package com.junie.callerblock.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.junie.callerblock.gen.DaoMaster;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niejun on 2017/10/30.
 */

public class DbOpenHelper extends DaoMaster.OpenHelper{

    private final static String TAG = DbOpenHelper.class.getSimpleName();

    private Context mContext;

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.mContext = context;
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        try {
            DbMergeHelper mergeHelper = new DbMergeHelper(db);
            //更新处理
            List<Class<? extends AbstractDao<?, ?>>> daoList = new ArrayList<>();

            mergeHelper.updateDb(daoList);
            //更新新表（if not exit)
            DaoMaster.createAllTables(db,true);

        } catch (ClassCastException e) {
            Log.e(TAG,"",e);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
