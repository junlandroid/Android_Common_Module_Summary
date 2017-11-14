package com.hy.junl.greendaotest.help;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述:该类主要用于基于GreenDao框架自定义数据库路径 ，主要用来给数据库文件创建存储路径，以方便查找
 * 创建日期：2017/10/25
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class GreenDaoContext extends ContextWrapper {


    private static final String TAG = GreenDaoContext.class.getSimpleName();

    private Context context;

    public GreenDaoContext(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     * <p>
     * mkdir（）是只能创建一级目录（文件夹），如果这一级目录的上面还有没有创建的目录（文件夹），那么程序会报错。
     * mkdirs（）是可以创建多级目录（文件夹），它是把所有没有的目录（文件夹）都给创建出来。
     * 所以开发中常用mkdirs（）来创建目录（文件夹）。
     *
     * File类的理解：http://blog.csdn.net/qiaqia609/article/details/11048463
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
//        //数据库文件存储路径
//        String path = Environment.getExternalStorageDirectory() + "/" + getResources().getString(R.string.app_name)+".db";
//        //好像只能在外部存储根路径创建数据库文件，不能在指定的文件夹中创建.db文件
//        File file = new File(path);
//        //如果目标文件所在的目录不存在，则创建父目录
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        //创建目标文件
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
        File baseFile = Environment.getExternalStorageDirectory();
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseFile.getPath());
        buffer.append(File.separator);
        buffer.append(dbName);
        return new File(buffer.toString());
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
     * android.database.sqlite.SQLiteDatabase.CursorFactory,
     * android.database.DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }


}
