package com.hy.junl.greendaotest.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.hy.junl.greendaotest.bean.dao.DaoMaster;
import com.hy.junl.greendaotest.bean.dao.DaoSession;
import com.hy.junl.greendaotest.help.GreenDaoContext;

import kr.co.namee.permissiongen.PermissionGen;


/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述：
 * <p>
 * 创建日期：2017/10/26
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 * <p>
 * <p>
 * 注意点：
 * 1、数据库名以 .db结尾
 * 2、manifest文件中添加权限
 * <uses-permission android:name="android.permission.INTERNET" />
 * <!--在sdcard中创建/删除文件的权限 -->
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
 */

public class BaseApplication extends Application {

    private static DaoSession daoSession;
    private static final String DB_NAME = "Green_Dao_Test.db";//记得数据库名-----> .db结尾

    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContext(getApplicationContext()), DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
